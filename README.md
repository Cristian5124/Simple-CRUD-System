# Property CRUD System - Sistema de Gestión de Propiedades

Una aplicación web CRUD completa construida con Spring Boot y frontend básico, con base de datos MySQL y API REST versionada.

![Java](https://img.shields.io/badge/Java-21+-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-green.svg)
![MySQL](https://img.shields.io/badge/MySQL-9.4-blue.svg)
![License](https://img.shields.io/badge/License-MIT-green.svg)
![Build](https://img.shields.io/badge/Build-Passing-brightgreen.svg)

## Resumen

Este proyecto implementa un sistema completo de gestión de propiedades inmobiliarias usando Spring Boot 3.1.0 con MySQL y un frontend intencionalmente básico para propósitos educativos.
La aplicación permite comprender los fundamentos de APIs REST, operaciones CRUD, bases de datos relacionales y desarrollo web full-stack.

---

## Características

**Backend:**

API REST Versionada: Endpoints `/api/v1/` 

Operaciones CRUD Completas: Create, Read, Update, Delete

Validación del Servidor: Validación de datos de entrada

Códigos de Estado HTTP: Respuestas apropiadas (200, 201, 204, 400, 404, 500)

Manejo de Errores: Try-catch con mensajes descriptivos

Base de Datos Automática: Creación automática de BD y tablas

**Frontend:**

HTML: Sin frameworks, formularios simples

CSS: Colores

JavaScript: ES5, sin librerías externas

Interfaz: Diseño simple para aprendizaje

**Características:**

JPA/Hibernate: Mapeo objeto-relacional automático

MySQL Connector: Conexión nativa a base de datos

Maven: Gestión de dependencias y build

Tomcat Embebido: Servidor web integrado en puerto 8080

---

## Arquitectura

### Desarrollo Local
```text
┌─────────────────────┐    ┌─────────────────┐    ┌──────────────────┐
│       Frontend      │    │     Backend     │    │       MySQL      │
│    (HTML/CSS/JS)    │    │     REST API    │    │      Database    │
│      Port: 8080     │    │     /api/v1/*   │    │    property_db   │
│      localhost      │    │   Spring Boot   │    │  localhost:3306  │
└─────────────────────┘    └─────────────────┘    └──────────────────┘
```

### Producción AWS
```text
┌─────────────────────────────────────────────────────────────────┐
│                          AWS CLOUD                              │
│                                                                 │
│  ┌─────────────────────┐    ┌─────────────────────────────────┐ │
│  │      EC2 Instance   │    │            MySQL RDS            │ │
│  │   Frontend + API    │◄──►│     propertydb.c50eg84k6t6e.    │ │
│  │   Port: 8080        │    │   us-east-1.rds.amazonaws.com   │ │
│  │   54.224.76.219     │    │                                 │ │
│  └─────────────────────┘    └─────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────────┘
```

### Flujo de Datos
```text
┌──────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│PropertyController│──▶| PropertyRepository│──▶│ Property Entity │
│ (REST Endpoints) │    │  (JPA Interface) │    │  (JPA @Entity)  │
└──────────────────┘    └──────────────────┘    └─────────────────┘
        ▼
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│   Web Browser   │──▶│     Fetch API    │──▶ │   JSON Response │
│   (Cliente)     │    │   (AJAX Calls)   │    │    (API Data)   │
└─────────────────┘    └──────────────────┘    └─────────────────┘
```

---

## Implementación

### Prerrequisitos

- [Java 21+](https://adoptium.net/)
- [Maven 3.6+](https://maven.apache.org/)
- [MySQL 8.0+](https://dev.mysql.com/downloads/mysql/)

### Configuración de Base de Datos

La base de datos se crea automáticamente, se debe ejecutar con:<br>
Usuario: root <br>
Contraseña: root

Opcional: Crear manualmente la base de datos

```sql
CREATE DATABASE IF NOT EXISTS property_db;
```

### Ejecución Local

Clonar el repositorio
```bash
git clone https://github.com/Cristian5124/Simple-CRUD-System.git
```
Ir a la carpeta descargada:
```bash
cd property-crud-system
```
Compilar el proyecto
```bash
mvn clean compile
```
Ejecutar la aplicación
```bash
mvn spring-boot:run
```

**Acceder en:**

**Interfaz Web**: [http://localhost:8080](http://localhost:8080)



**API REST**: [http://localhost:8080/api/v1/properties](http://localhost:8080/api/v1/properties)


---

## API Endpoints

| Método | Endpoint                  | Parámetros           | Descripción                   | Status Codes       |
| ------ | ------------------------- | -------------------- | ----------------------------- | ------------------ |
| GET    | `/api/v1/properties`      | Ninguno              | Obtener todas las propiedades | 200, 500           |
| GET    | `/api/v1/properties/{id}` | id (path)            | Obtener propiedad por ID      | 200, 404, 500      |
| POST   | `/api/v1/properties`      | JSON body            | Crear nueva propiedad         | 201, 400, 500      |
| PUT    | `/api/v1/properties/{id}` | id (path), JSON body | Actualizar propiedad          | 200, 400, 404, 500 |
| DELETE | `/api/v1/properties/{id}` | id (path)            | Eliminar propiedad            | 204, 404, 500      |

---

## Despliegue en AWS

### Demostración en Vivo

**Aplicación Web**: [http://ec2-54-224-76-219.compute-1.amazonaws.com:8080](http://ec2-54-224-76-219.compute-1.amazonaws.com:8080)

**API REST**: [http://ec2-54-224-76-219.compute-1.amazonaws.com:8080/api/v1/properties](http://ec2-54-224-76-219.compute-1.amazonaws.com:8080/api/v1/properties)

### Arquitectura AWS

```text
┌─────────────────────────────────────────────────────────────────┐
│                         AWS CLOUD                               │
│                                                                 │
│  ┌─────────────────────┐    ┌─────────────────────────────────┐ │
│  │      EC2 Instance   │    │         MySQL RDS               │ │
│  │   t3.micro (1GB)    │◄──►│  db.t3.micro                    │ │
│  │                     │    │                                 │ │
│  │  • Spring Boot App  │    │  • Database: propertydb         │ │
│  │  • Port: 8080       │    │  • Engine: MySQL 8.0            │ │
│  │  • Java 21          │    │  • Multi-AZ: No                 │ │
│  │  • Ubuntu 22.04     │    │  • Storage: 20GB SSD            │ │
│  │                     │    │                                 │ │
│  │  Public IP:         │    │  Endpoint:                      │ │
│  │  54.224.76.219      │    │  propertydb.c50eg84k6t6e.       │ │
│  │                     │    │  us-east-1.rds.amazonaws.com    │ │
│  └─────────────────────┘    └─────────────────────────────────┘ │
│           │                                                     │
│           ▼                                                     │
│  ┌─────────────────────┐                                        │
│  │   Security Groups   │                                        │
│  │                     │                                        │
│  │  • HTTP: 8080       │                                        │
│  │  • SSH: 22          │                                        │
│  │  • MySQL: 3306      │                                        │
│  └─────────────────────┘                                        │
└─────────────────────────────────────────────────────────────────┘
```

### Configuración de Producción

**Instancia EC2:**

**Tipo de instancia**: t3.micro (1 vCPU, 1 GB de RAM) <br>
**AMI**: Ubuntu Server 22.04 LTS<br>
**IP pública**: 54.224.76.219<br>
**DNS**: ec2-54-224-76-219.compute-1.amazonaws.com<br>
**Región**: us-east-1 (Virginia)
<br><br>
**MySQL RDS:**

**Motor**: MySQL 8.0.39<br>
**Clase de instancia**: db.t3.micro<br>
**Almacenamiento asignado**: 20 GB (SSD)<br>
**Nombre de la base de datos**: propertydb<br>
**Punto final**: propertydb.c50eg84k6t6e.us-east-1.rds.amazonaws.com:3306<br>
**Multi-AZ**: Desactivado (implementación Single-AZ)

### Pasos de Despliegue

#### 1. Configuración de MySQL RDS

```bash
# Crear instancia RDS desde AWS Console
Database engine: MySQL
Template: Free tier
DB instance identifier: propertydb
Master username: root
Master password: [secured]
Instance class: db.t3.micro
Storage: 20 GB SSD
Connectivity: Default VPC
Public access: Yes
VPC security group: default + custom (port 3306)
```

#### 2. Configuración de EC2

```bash
# Lanzar instancia EC2
AMI: Ubuntu Server 22.04 LTS
Instance type: t3.micro
Security group: Allow ports 22 (SSH), 8080 (HTTP), 3306 (MySQL)
Key pair: property-crud-keypair.pem

# Conectar por SSH
ssh -i "property-crud-keypair.pem" ubuntu@ec2-54-224-76-219.compute-1.amazonaws.com
```

#### 3. Instalación en EC2

```bash
# Actualizar sistema
sudo apt update && sudo apt upgrade -y

# Instalar Java 21
sudo apt install openjdk-21-jdk -y
java -version

# Instalar Maven
sudo apt install maven -y
mvn -version

# Instalar Git
sudo apt install git -y

# Clonar repositorio
git clone https://github.com/Cristian5124/Simple-CRUD-System.git
cd Simple-CRUD-System
```

#### 4. Configuración de Base de Datos

```properties
# application.properties para producción
spring.datasource.url=jdbc:mysql://propertydb.c50eg84k6t6e.us-east-1.rds.amazonaws.com:3306/propertydb?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=[secured]
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
server.port=8080
```

#### 5. Construcción y Despliegue

```bash
# Compilar aplicación
mvn clean package

# Ejecutar en producción
nohup java -jar target/property-crud-system-0.0.1-SNAPSHOT.jar > app.log 2>&1 &

# Verificar que esté ejecutándose
curl http://localhost:8080/api/v1/properties

# Ver logs
tail -f app.log
```
---

## Configuración de Base de Datos

### Desarrollo Local - MySQL

```properties
# Configuración para desarrollo local en application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/property_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
```

### Producción AWS - MySQL RDS

```properties
# Configuración para AWS RDS en application.properties
spring.datasource.url=jdbc:mysql://propertydb.c50eg84k6t6e.us-east-1.rds.amazonaws.com:3306/propertydb?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=[secured]
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
server.port=8080
```

### Configuración en application.properties

```properties
# Usar variables de entorno con valores por defecto
spring.datasource.url=jdbc:mysql://${DB_HOST:localhost}:${DB_PORT:3306}/${DB_NAME:property_db}?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:root}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=${SHOW_SQL:true}
server.port=${SERVER_PORT:8080}
```

---

## Estructura del Proyecto

```text
property-crud-system/
├── src/main/java/edu/escuelaing/app/
│   ├── PropertyCrudSystemApplication.java
│   ├── Property.java
│   ├── PropertyRepository.java
│   └── PropertyController.java
├── src/main/resources/
│   ├── static/
│   │   ├── index.html
│   │   ├── styles.css
│   │   └── script.js
│   └── application.properties
├── database-setup.sql
├── LICENSE
├── .gitignore
├── pom.xml
└── README.md
```

---

---

## Modelo de Datos

### Tabla Properties

```sql
CREATE TABLE properties (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    address VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    size DOUBLE NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

### Validaciones

**address**: Requerido, no puede estar vacío <br>
**price**: Requerido, debe ser mayor que 0<br>
**size**: Requerido, debe ser mayor que 0<br>
**description**: Opcional, puede ser nulo<br>

---

## Licencia

Este proyecto está licenciado bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

---

## Autor

**Cristian David Polo Garrido** - Desarrollador Full Stack

GitHub: [https://github.com/Cristian5124](https://github.com/Cristian5124)

---