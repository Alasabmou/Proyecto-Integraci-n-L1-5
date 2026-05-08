# Proyecto de Integración: Arquitectura e Integración de Sistemas Software

## Miembros del grupo L1-5

1. Márquez Molina, Álvaro
2. Martínez de Eulate Barbarin, Gonzalo
3. Sabbar Moukhlissi, Alaa
4. Sánchez Gago, Carla

## Descripción del Proyecto
Este proyecto contiene un sistema distribuido diseñado para la extracción, transformación y carga (ETL) de datos provenientes de diversas plataformas de vídeo. El sistema está compuesto por tres microservicios independientes:

1. PeerTubeMiner: procesa la información de la API REST de PeerTube y la envía a VideoMiner.
2. DailyMotionMiner: lee los datos desde la API REST de DailyMotion y los envía a VideoMiner.
3. VideoMiner: Implementa una API REST para almacenar e integrar canales, vídeos, usuarios, comentarios y subtítulos.


## Tecnologías y Requisitos Previos
- Lenguaje: Java 17 para `VideoMiner` y Java 21 para `DailyMotionMiner` y `PeerTubeMiner`
- Framework: Spring Boot
- Base de Datos: H2 en memoria 
- Herramientas de Construcción: Maven 

## Puertos asignados:

- VideoMiner: `http://localhost:8080`
- DailyMotionMiner: `http://localhost:8081`
- PeerTubeMiner: `http://localhost:8082`

## Arquitectura de la API y Endpoints Principales

### 1. VideoMiner (Gestor Central)
Expone la información almacenada en base de datos.
- Canales (`/api/videominer/channels`):
  - `GET /` - Listar todos los canales.
  - `GET /{id}` - Obtener información de un canal específico.
  - `POST /` - Crear un canal.
  - `POST /{id}/videos` - Añadir un vídeo a un canal existente.
  - `DELETE /{id}` - Eliminar un canal.
- Vídeos (`/api/videominer/videos`):
  - `GET /` - Listar todos los vídeos.
  - `GET /{id}` - Obtener un vídeo por su ID.
  - `GET /{id}/comments` - Listar comentarios asociados a un vídeo.
  - `GET /{id}/captions` - Listar subtítulos asociados a un vídeo.
  - `DELETE /{id}` - Eliminar un vídeo.
- Comentarios (`/api/videominer/comments`):
  -  `GET /` - Listar todos los comentarios.
  - `GET /{id}` - Obtener un comentario por su ID.
  - `DELETE /{id}` - Eliminar un comentario.
- Subtítulos (`/api/videominer/captions`):
  -  `GET /` - Listar todos los subtitulos.
  - `GET /{id}` - Obtener un subtitulo por su ID.
  - `DELETE /{id}` - Eliminar un subtitulo.

### 2. DailyMotionMiner
Se encarga del flujo ETL desde Dailymotion.
- `GET /api/dailymotion/{id}` - Extrae y devuelve la información del canal formateada. Admite parámetros `maxVideos` y `maxPages`.
- `POST /api/dailymotion/{id}` - Realiza la extracción y hace un envío automático (POST) al microservicio VideoMiner.

### 3. PeerTubeMiner
Se encarga del flujo ETL desde PeerTube (Framatube).
- `GET /api/peertube/{channelId}` - Extrae y formatea la información del canal. Admite parámetros `maxVideos` y `maxComments`.
- `POST /api/peertube/{channelId}` - Extrae los datos y los envía automáticamente a VideoMiner.
