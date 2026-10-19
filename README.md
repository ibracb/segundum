# SegundUM

> Evolución de un marketplace de segunda mano: consulta productos, publica y gestiona los tuyos, propón ventas y valora a las partes.

![Java](https://img.shields.io/badge/Java-11-orange?style=flat&logo=openjdk&logoColor=white)
![JAX-RS](https://img.shields.io/badge/JAX--RS-Jersey%202.41-007396?style=flat)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.2%20y%202.6-6DB33F?style=flat&logo=springboot&logoColor=white)
![.NET](https://img.shields.io/badge/.NET-8.0-512BD4?style=flat&logo=dotnet&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=flat&logo=mysql&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-8.0-47A248?style=flat&logo=mongodb&logoColor=white)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-3.13-FF6600?style=flat&logo=rabbitmq&logoColor=white)
![Docker](https://img.shields.io/badge/Docker%20Compose-2496ED?style=flat&logo=docker&logoColor=white)
![University of Murcia](https://img.shields.io/badge/University%20of%20Murcia-E03B23?style=flat&logo=graduation-cap&logoColor=white)

## Descripción general

SegundUM es un sistema de compraventa de segunda mano construido como una arquitectura de microservicios que, según el caso, se pueden comunicar por REST o mediante eventos. Los usuarios se registran con email y contraseña y actúan como compradores y/o vendedores: publican productos que siguen su propio ciclo de vida, proponen ventas que atraviesan una máquina de estados y valoran a las partes cuando la venta se completa.

## Estado del proyecto

> [!NOTE]
> Esta es la **versión completa y evolutiva** de SegundUM. La versión inicial del proyecto está en [ibracb/segundum-preview](https://github.com/ibracb/segundum-preview).

## Estructura del proyecto

```
segundum/
├── .github/
│   └── workflows/                 # CI de cada microservicio (GitHub Actions)
├── apps/
│   ├── api-gateway/               # Spring Boot + Zuul: puerta de entrada y JWT
│   ├── product-service/           # Spring Boot: catálogo y búsqueda
│   ├── sale-service/              # Spring Boot: ciclo de vida de las ventas
│   ├── user-service/              # JAX-RS/Jetty: usuarios y autenticación
│   └── rating-service/            # .NET: valoraciones
├── db/                            # Scripts SQL de inicialización
├── docs/                          # Documentación adicional
├── postman/
│   ├── collections/               # Colecciones de pruebas de Postman
│   └── environments/              # Entornos para Postman
├── .env.example                   # Variables de entorno de ejemplo
├── .gitignore                     # Ficheros ignorados por Git
├── compose.dev.yaml               # Docker Compose (desarrollo)
├── compose.yaml                   # Docker Compose (producción)
└── README.md                      # Esta documentación
```

## Requisitos

- **Docker** y **Docker Compose**: comprueba con `docker --version` y `docker compose version`

Para desarrollar un servicio en local (opcional, no hace falta para usar el sistema completo):

- **Java 11 o superior**: comprueba con `java --version`
- **Maven 3.8 o superior**: comprueba con `mvn --version`
- **.NET 8 SDK** (solo `rating-service`): comprueba con `dotnet --version`

Se usan para compilar cada microservicio y ejecutar sus tests unitarios en local (ver [Pruebas](docs/03-pruebas.md)).

## Instalación

Clona el repositorio:

```bash
git clone https://github.com/ibracb/segundum.git
cd segundum
```

## Configuración

Copia el [fichero de variables de entorno de ejemplo](.env.example) y ajusta los valores a tu gusto:

```bash
cp .env.example .env
```

Valores que **debes rellenar** antes de arrancar:

- **`JWT_SECRET`**: secreto con el que se firman y verifican los JWT, compartido por todos los servicios. Puedes generarlo con `openssl rand -base64 32` (debe ser una cadena Base64 válida de 32 bytes).
- **`MYSQL_ROOT_PASSWORD`**: obligatorio, dado que MySQL no arranca sin él.
- **`USER_SERVICE_DATASOURCE_USERNAME` / `..._PASSWORD`** (y los equivalentes de `product-service` y `sale-service`): credenciales con las que cada servicio se conecta a MySQL. En el ejemplo se usa el usuario `root` con la misma contraseña que `MYSQL_ROOT_PASSWORD`.
- **`RABBITMQ_DEFAULT_USER` / `RABBITMQ_DEFAULT_PASS`**: usuario y contraseña del bus de eventos.
- **`MONGO_EXPRESS_BASICAUTH_USERNAME` / `..._PASSWORD`** (solo desarrollo): acceso a Mongo Express.

> **Nota:** Tanto bases de datos (MySQL, MongoDB) como el bróker de mensajería (RabbitMQ) no se publican en el host, sino que solo son accesibles desde los contenedores. En desarrollo sí se exponen las interfaces de gestión (phpMyAdmin, Mongo Express y el panel de RabbitMQ).

## Compilación y ejecución

Todo se arranca con Docker Compose, por lo que no es necesario compilar nada. Los comandos siguientes se ejecutan desde la raíz del repositorio (`segundum/`), tras el `cd` de la instalación.

**Producción** (MySQL + MongoDB + RabbitMQ + los 5 microservicios, con el gateway en el puerto 8090):

```bash
docker compose up -d --build
```

**Desarrollo** (además expone cada microservicio en localhost, activa Swagger y añade phpMyAdmin, Mongo Express y el panel de RabbitMQ):

```bash
docker compose -f compose.yaml -f compose.dev.yaml up -d --build
```

Para detener los contenedores:

```bash
docker compose down
```

Añade `-v` si además quieres borrar los volúmenes de datos (`mysql_data`, `mongodb_data`, `rabbitmq_data`).

> **Nota:** En el primer arranque MySQL ejecuta los scripts de inicialización incluidos en el repositorio y el *healthcheck* de MongoDB permite hasta 4 minutos de margen, así que los servicios pueden tardar en quedar `healthy`.

## Accesos web navegables

| Recurso | URL | Disponibilidad |
| --- | --- | --- |
| Swagger UI de `api-gateway` | http://localhost:8090/api/swagger-ui | Modo desarrollo |
| Swagger UI de `user-service` | http://localhost:8080/api/swagger-ui | Modo desarrollo |
| Swagger UI de `product-service` | http://localhost:8081/api/swagger-ui | Modo desarrollo |
| Swagger UI de `sale-service` | http://localhost:8082/api/swagger-ui | Modo desarrollo |
| Swagger UI de `rating-service` | http://localhost:8083/api/swagger-ui | Modo desarrollo |
| phpMyAdmin | http://localhost:8089 | Modo desarrollo |
| Mongo Express | http://localhost:8088 | Modo desarrollo |
| Panel de RabbitMQ | http://localhost:15672 | Modo desarrollo |

En producción, la única puerta de entrada del sistema es el gateway: la API queda disponible en `http://localhost:8090/api`. En desarrollo, los servicios solo se publican en `127.0.0.1` (no quedan accesibles desde otras máquinas de la red local).

Swagger UI se activa solo en modo desarrollo (`SWAGGER_ENABLED=true`) y cada servicio expone la suya en su propio puerto.

## Documentación

- [Backend](docs/01-backend.md): plataformas y frameworks, microservicios, estilos de arquitectura (limpia, hexagonal, DDD, CQRS y event sourcing), comunicación síncrona y asíncrona, API Gateway, OpenAPI y JWT.
- [Datos](docs/02-datos.md): entidades y relaciones, patrones de acceso, esquema conceptual y elección de la arquitectura de datos.
- [Pruebas](docs/03-pruebas.md): estrategia de pruebas, pruebas unitarias y pruebas de backend con Postman.
- [Integración continua y despliegue](docs/04-ci-despliegue.md): pipeline de CI y despliegue local con Docker Compose.

## Contexto académico

- **Asignatura:** Arquitectura del Software
- **Grado:** Grado en Ingeniería Informática
- **Universidad:** Universidad de Murcia
- **Curso:** 2025-2026

## Autores

- **Ibrahim Cherif Barry** - [ibracb](https://github.com/ibracb)
