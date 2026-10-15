# Pruebas

Este documento describe la estrategia de pruebas de SegundUM: las pruebas unitarias de cada microservicio y las pruebas de backend automatizadas con la colección de Postman incluida en el repositorio.

## Estrategia de pruebas

Se define en dos niveles:

- **Pruebas unitarias por servicio**, que verifican la lógica de negocio de cada microservicio de forma aislada (sin bases de datos ni bus reales). Se ejecutan en cada *push* y *pull request* por la integración continua (ver [`04-ci-despliegue.md`](04-ci-despliegue.md)) y su cobertura se genera como informe.
- **Pruebas de backend con Postman**, que recorren el sistema completo por su API real (a través del gateway), de punta a punta: registro, login, catálogo, ventas y valoraciones.

## Pruebas unitarias

### Servicios Java

Los cuatro servicios Java usan **JUnit 5** (Jupiter). `user-service` usa JUnit 5 directamente (5.9.3); `product-service`, `sale-service` y `api-gateway` usan el starter de test de Spring Boot, que incorpora JUnit 5 junto con Mockito y las utilidades de test de Spring.

Se ejecutan con Maven desde la carpeta del servicio (repite el comando con `product-service`, `sale-service` o `api-gateway` según el que quieras probar), y también genera el informe de cobertura con **JaCoCo**:

```bash
cd apps/user-service
mvn -B clean package
```

Los tests se apoyan en la arquitectura hexagonal del sistema: en lugar de tocar bases de datos o el bus real, se inyectan **dobles en memoria (fakes)** del repositorio, de las consultas, de la publicación de eventos y de las llamadas a otros servicios, de modo que los casos de uso se prueban aislados de la infraestructura.

### rating-service (.NET)

`rating-service` usa **xUnit** con el mismo enfoque de fakes en memoria para sus puertos (publicación de eventos, lectura de las partes de la venta, persistencia), y **coverlet** para la cobertura:

```bash
cd apps/rating-service
dotnet test RatingService.sln
```

### Cobertura

Cada servicio genera su informe de cobertura (JaCoCo en Java, coverlet en .NET) y la integración continua lo sube como artefacto de la ejecución, aunque los tests fallen (ver [`04-ci-despliegue.md`](04-ci-despliegue.md)).

## Pruebas backend con Postman

El repositorio incluye una colección de Postman que automatiza el **flujo completo de la aplicación**: registro de usuarios, login, publicación y búsqueda de productos, propuesta y ciclo de vida de las ventas, y valoraciones.

- **Colección:** [`postman/collections/SegundUM.postman_collection.json`](../postman/collections/SegundUM.postman_collection.json)
- **Entorno:** [`postman/environments/SegundUM-local.postman_environment.json`](../postman/environments/SegundUM-local.postman_environment.json) (apunta a `http://localhost:8090`, el gateway)

### Cómo ejecutarlas

1. Importa la colección y el entorno en Postman.
2. Selecciona el entorno `SegundUM-local`.
3. Ejecuta las carpetas en orden: `users` → `products` → `sales` → `ratings`.

### Contenido por carpeta

| Carpeta | Peticiones | Qué cubre |
| --- | --- | --- |
| `users` | 13 | Registro de vendedor, comprador y administrador; login de cada uno; perfil, nombre, estadísticas, valoraciones, lista de usuarios y desactivación |
| `products` | 19 | Categorías, creación y edición de productos, asignación de punto de recogida, publicación, retirada, descarte, vistas, búsqueda y productos por vendedor |
| `sales` | 14 | Propuesta de venta, reserva, rechazo, cancelación por cada parte y finalización |
| `ratings` | 6 | Valoración del vendedor y del comprador de una venta completada, y consulta de valoraciones |

> **Nota:** el sistema debe estar arrancado con Docker Compose antes de ejecutar la colección. La ejecución de las carpetas en orden depende de los datos creados por las peticiones anteriores (p. ej. las ventas usan los productos creados en la carpeta `products`).
