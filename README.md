# SegundUM

> Evolución de un marketplace de segunda mano: consulta productos, publica y gestiona los tuyos, propón ventas y valora a las partes.

![Java](https://img.shields.io/badge/Java-11-orange?style=flat&logo=openjdk&logoColor=white)
![JAX-RS](https://img.shields.io/badge/JAX--RS-Jersey%202.41-007396?style=flat)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.2.10.RELEASE%20y%202.6.1-6DB33F?style=flat&logo=springboot&logoColor=white)
![.NET](https://img.shields.io/badge/.NET-8.0-512BD4?style=flat&logo=dotnet&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=flat&logo=mysql&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-8.0-47A248?style=flat&logo=mongodb&logoColor=white)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-3.13-FF6600?style=flat&logo=rabbitmq&logoColor=white)
![Docker](https://img.shields.io/badge/Docker%20Compose-2496ED?style=flat&logo=docker&logoColor=white)
![University of Murcia](https://img.shields.io/badge/University%20of%20Murcia-E03B23?style=flat&logo=graduation-cap&logoColor=white)

## Descripción general

SegundUM es una aplicación de compraventa de productos segunda mano en el que los usuarios se registran con email y contraseña y actúan como compradores y/o vendedores: estos pueden publicar productos que siguen su propio ciclo de vida, proponer ventas que atraviesan un determinado flujo de estados y valorar a la parte implicada en la venta una vez se encuentra completada.

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

Para desplegar el sistema:

- **Docker** y **Docker Compose**: comprueba con `docker --version` y `docker compose version`

Opcionalmente para desarrollo, compilación y pruebas unitarias en local:

- **Java 11 o superior** (`user-service`, `product-service`, `sale-service`, `api-gateway`): comprueba con `java --version`
- **Maven 3.8 o superior** (`user-service`, `product-service`, `sale-service`, `api-gateway`): comprueba con `mvn --version`
- **.NET 8 SDK** (solo `rating-service`): comprueba con `dotnet --version`

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

> **Nota:** Tanto las bases de datos (MySQL, MongoDB) como el bróker de mensajería (RabbitMQ) no se publican en el host, sino que solo son accesibles desde los contenedores. En desarrollo sí se exponen las interfaces de gestión (phpMyAdmin, Mongo Express y el panel de RabbitMQ).

## Compilación y despliegue

### Backend

**Despliegue en producción** (MySQL + MongoDB + RabbitMQ + los 5 microservicios):

```bash
docker compose up -d --build
```

**Despliegue en desarrollo** (instancias de producción + Swagger UI + phpMyAdmin + Mongo Express + RabbitMQ Management):

```bash
docker compose -f compose.yaml -f compose.dev.yaml up -d --build
```

Para detener los contenedores:

```bash
docker compose down
```

Añade `-v` si además quieres borrar los volúmenes de datos (`mysql_data`, `mongodb_data`, `rabbitmq_data`).

> **Nota:** En el primer arranque MySQL ejecuta los scripts de inicialización incluidos en [db](db) y los *healthcheck* de MySQL, MongoDB y RabbitMQ permiten hasta 4 minutos de margen, así que los servicios pueden tardar en quedar `healthy`.

## Accesos web navegables

| Recurso | URL | Disponibilidad |
| --- | --- | --- |
| Swagger UI de `api-gateway` | http://localhost:8090/api/swagger-ui | Desarrollo |
| Swagger UI de `user-service` | http://localhost:8080/api/swagger-ui | Desarrollo |
| Swagger UI de `product-service` | http://localhost:8081/api/swagger-ui | Desarrollo |
| Swagger UI de `sale-service` | http://localhost:8082/api/swagger-ui | Desarrollo |
| Swagger UI de `rating-service` | http://localhost:8083/api/swagger-ui | Desarrollo |
| phpMyAdmin | http://localhost:8089 | Desarrollo |
| Mongo Express | http://localhost:8088 | Desarrollo |
| Panel de RabbitMQ | http://localhost:15672 | Desarrollo |

En producción, la única puerta de entrada al backend es el gateway: la API queda disponible en http://*host*:8090/api, siendo *host* la IP o el dominio en el que se encuentra desplegado. En desarrollo, los microservicios solo se publican en `127.0.0.1`, por lo que no quedan accesibles desde otras máquinas de la red.

## Documentación

- [Backend](docs/01-backend.md): plataformas y frameworks, microservicios, principios de diseño y arquitectura, comunicación entre microservicios, API Gateway, OpenAPI y JWT.
- [Datos](docs/02-datos.md): entidades y relaciones, patrones de acceso, esquema conceptual y elección de la arquitectura de datos.
- [Pruebas](docs/03-pruebas.md): estrategia de pruebas, pruebas unitarias y pruebas de backend.
- [Integración continua y despliegue](docs/04-ci-despliegue.md): pipeline de CI y configuración del despliegue.

## Contexto académico

- **Asignatura:** Arquitectura del Software
- **Grado:** Grado en Ingeniería Informática
- **Universidad:** Universidad de Murcia
- **Curso:** 2025-2026

## Autores

- **Ibrahim Cherif Barry** - [ibracb](https://github.com/ibracb)
