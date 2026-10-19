# Integración continua y despliegue

Este documento describe el pipeline de integración continua montado con GitHub Actions y cómo se realiza el despliegue del sistema, que en este proyecto es **local**, mediante Docker Compose.

## Integración continua

El proyecto tiene **cinco workflows de GitHub Actions**, uno por cada microservicio (`.github/workflows/`), de forma que cada uno se verifica de forma independiente.

### Disparadores

Cada workflow se ejecuta en los eventos `push` y `pull request` sobre la rama `main`, y aplica **filtros por rutas**: solo se activa cuando cambia el código de su servicio (o el propio fichero del workflow), de modo que una modificación en un servicio no dispara la verificación de los demás.

### Pasos de un workflow Java

Los cuatro workflows Java (`user-service`, `product-service`, `sale-service` y `api-gateway`) siguen los mismos pasos sobre un ejecutor `ubuntu-24.04`:

1. **Checkout** del repositorio.
2. **Configuración de JDK 11** (distribución Temurin).
3. **Caché de dependencias Maven** (`~/.m2`, clave por el hash de los `pom.xml`).
4. **Compilación y tests** con `mvn -B clean package` en la carpeta del servicio (ejecuta los tests unitarios y genera el informe de cobertura JaCoCo).
5. **Subida del informe de cobertura** como artefacto de la ejecución, incluso si los tests fallan (`if: always()`).
6. **Verificación del artefacto empaquetado**: el WAR en `user-service`, el JAR en el resto.

### Pasos del workflow .NET

El workflow de `rating-service` ejecuta sobre el mismo tipo de ejecutor:

1. **Checkout** del repositorio.
2. **Configuración de .NET 8 SDK**.
3. **Caché de dependencias NuGet** (`~/.nuget/packages`).
4. **Tests con cobertura**: `dotnet test` en modo Release con la colección de cobertura XPlat (coverlet), compila la solución antes de ejecutarlos.
5. **Subida del informe de cobertura** como artefacto, incluso si los tests fallan.
6. **Publicación del servicio** (`dotnet publish`) y verificación de la salida.

### Resumen

| Workflow | Verifica | Cobertura | Artefacto |
| --- | --- | --- | --- |
| `user-service` | Compilación, tests unitarios | JaCoCo | WAR |
| `product-service` | Compilación, tests unitarios | JaCoCo | JAR |
| `sale-service` | Compilación, tests unitarios | JaCoCo | JAR |
| `api-gateway` | Compilación, tests unitarios | JaCoCo | JAR |
| `rating-service` | Compilación, tests unitarios | coverlet (XPlat) | Publicación .NET |

> Nota: los filtros por rutas solo contemplan el código del servicio y el propio fichero del workflow. Los cambios que solo afectan a documentación no disparan la verificación de ningún servicio.

> Nota: la integración continua cubre solo la verificación individual de cada microservicio. Las pruebas de punta a punta con la colección de Postman no se ejecutan en CI porque exigirían levantar todo el backend, ver [`03-pruebas.md`](03-pruebas.md).

## Despliegue local

En SegundUM no hay despliegue remoto: el despliegue es **local** y se ejecuta manualmente con Docker Compose, que levanta todo el sistema en el propio equipo. Las imágenes de los cinco microservicios se **construyen desde el código fuente** con su Dockerfile respectivo.

### Modos de despliegue

| Modo | Qué levanta |
| --- | --- |
| Producción | MySQL, MongoDB, RabbitMQ y los 5 microservicios (gateway en el puerto 8090) |
| Desarrollo | Todo lo relacionado al modo producción, más: exposición de cada microservicio en localhost, Swagger activo y los paneles de gestión (phpMyAdmin, Mongo Express y el panel de RabbitMQ) |

La configuración de todo el despliegue (credenciales, rutas internas, secreto JWT) se inyecta por variables de entorno desde un fichero `.env` (ver [`README.md`](../README.md)).

### Orquestación

- **Healthchecks:** los tres contenedores de infraestructura y los cinco microservicios declaran healthcheck, y todo `depends_on` espera a que el dependido esté `healthy` antes de arrancar. Los de infraestructura usan su comprobación nativa y admiten hasta 4 minutos de margen, pensado para el primer arranque, en el que MySQL ejecuta los scripts de inicialización incluidos en el repositorio. Los microservicios comprueban que su puerto responde y admiten 30 segundos.
- **Volúmenes:** los datos persisten en volúmenes nombrados (`mysql_data`, `mongodb_data`, `rabbitmq_data`). `docker compose down -v` los elimina (pérdida total de datos).
- **Red:** los servicios y las bases de datos comparten red interna. En producción solo se publica en el host el gateway. En desarrollo se publican además los cuatro servicios, restringidos a localhost, y los paneles. Las bases de datos no son accesibles desde fuera de la red de contenedores.

Los tres contenedores de infraestructura no esperan a nada, así que arrancan primero. Después lo hacen los cinco microservicios, cada uno cuando sus dependencias están `healthy`:

| Contenedor | Espera a que esté `healthy` |
| --- | --- |
| `user-service` | `mysql`, `rabbitmq` |
| `product-service` | `mysql`, `mongodb`, `rabbitmq` |
| `sale-service` | `mysql`, `mongodb`, `rabbitmq`, `user-service`, `product-service` |
| `rating-service` | `mongodb`, `rabbitmq`, `sale-service` |
| `api-gateway` | `user-service`, `product-service`, `sale-service`, `rating-service` |

Solo con `compose.dev.yaml` se añaden a la misma red los paneles de gestión: `phpmyadmin` espera a `mysql` y `mongo-express` espera a `mongodb`.

> La integración continua y el despliegue local se pueden verificar en conjunto: cada `push` a `main` valida los servicios con los mismos `mvn` / `dotnet test` que se ejecutarían en local, y Docker Compose construye exactamente las mismas piezas que despliega.
