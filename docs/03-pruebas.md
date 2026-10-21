# Pruebas

Este documento describe la estrategia de pruebas de SegundUM: las pruebas unitarias de cada microservicio y las pruebas de backend con Postman. Los comandos y pasos para ejecutarlas están en el [README](../README.md#pruebas).

## Estrategia de pruebas

Se define en dos niveles:

- **Pruebas unitarias por microservicio**, que verifican la lógica de negocio de cada uno de forma aislada (sin bases de datos ni bus reales). Se ejecutan en cada *push* y *pull request* por la integración continua.
- **Pruebas de backend con Postman**, que recorren el sistema completo por su API real (a través del gateway), de punta a punta: registro, login, catálogo, ventas y valoraciones. Se ejecutan en local con el Collection Runner.

## Pruebas unitarias

### Herramientas

| Microservicio | Framework | Cobertura |
| --- | --- | --- |
| `user-service` | JUnit 5 (5.9.3) | JaCoCo |
| `product-service`, `sale-service`, `api-gateway` | JUnit 5 + Mockito (starter de test de Spring Boot) | JaCoCo |
| `rating-service` | xUnit | coverlet |

### Enfoque

Los tests se apoyan en los principios de la arquitectura hexagonal: se inyectan **dobles en memoria (fakes)** de los puertos (repositorio, consultas, publicación de eventos y llamadas a otros microservicios), de modo que los casos de uso se prueban aislados de la infraestructura. `rating-service` sigue el mismo enfoque con sus puertos (publicación de eventos, lectura de las partes de la venta y persistencia).

### Cobertura

La integración continua genera el informe de cobertura de cada microservicio (JaCoCo en Java, coverlet en .NET) y lo sube como artefacto de la ejecución.

## Pruebas de backend

El repositorio incluye una colección de Postman que automatiza el **flujo completo de la aplicación**: registro de usuarios, login, publicación y búsqueda de productos, propuesta y ciclo de vida de las ventas, y valoraciones.

- **Colección:** [`postman/collections/SegundUM.postman_collection.json`](../postman/collections/SegundUM.postman_collection.json)
- **Entorno:** [`postman/environments/SegundUM.postman_environment.json`](../postman/environments/SegundUM.postman_environment.json) (apunta al gateway, `http://localhost:8090`)

### Contenido por carpeta

| Carpeta | Peticiones | Qué cubre |
| --- | --- | --- |
| `users` | 13 | Registro y login (vendedor, comprador y administrador), perfil, nombre, estadísticas, valoraciones, lista de usuarios y desactivación |
| `products` | 19 | Categorías, creación y edición de productos, asignación de punto de recogida, publicación, retirada, descarte, vistas, búsqueda y productos por vendedor |
| `sales` | 14 | Propuesta de venta, reserva, rechazo, cancelación por cada parte y finalización |
| `ratings` | 6 | Valoración del vendedor y del comprador de una venta completada, y consulta de valoraciones |

La ejecución es **secuencial por diseño**: cada carpeta usa los datos creados por la anterior (por ejemplo, las ventas usan los productos creados en `products`), y el backend debe estar arrancado.

### Por qué no está en la integración continua

Ninguno de los workflows ejecuta la colección. Habría que levantar el backend completo en el runner de GitHub Actions (los 5 microservicios, MySQL, MongoDB y RabbitMQ: 8 contenedores) y esperar a que todos respondan antes de lanzar la colección apuntando al gateway. Supone un coste de tiempo de ejecución y de mantenimiento muy superior al de la compilación y los tests unitarios.

Además, al ser secuencial por diseño, la colección no admite el reparto por microservicio que sí tiene la integración continua.

La decisión es dejar estas pruebas como verificación de punta a punta **en local**, antes de cada entrega, y reservar la integración continua para la verificación rápida y barata de cada microservicio por separado.

Como consecuencia, se asume que los posibles fallos de integración no se detectan automáticamente en cada cambio, sino solo cuando se ejecuta la colección antes de cada entrega.
