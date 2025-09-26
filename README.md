# Property CRUD System - Sistema de Gestión de Propiedades

Una aplicación web CRUD completa construida con Spring Boot y frontend básico, con base de datos MySQL y API REST versionada.

![Java](https://img.shields.io/badge/Java-21+-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-green.svg)
![MySQL](https://img.shields.io/badge/MySQL-9.4-blue.svg)
![License](https://img.shields.io/badge/License-MIT-green.svg)
![Build](https://img.shields.io/badge/Build-Passing-brightgreen.svg)

## Resumen del Proyecto

Este proyecto implementa un sistema completo de gestión de propiedades inmobiliarias usando Spring Boot 3.1.0 con MySQL y un frontend intencionalmente básico para propósitos educativos.
La aplicación permite comprender los fundamentos de APIs REST, operaciones CRUD, bases de datos relacionales y desarrollo web full-stack.

---

## Características

**Backend Profesional:**

- API REST Versionada: Endpoints `/api/v1/` con mejores prácticas
- Operaciones CRUD Completas: Create, Read, Update, Delete
- Validación del Servidor: Validación robusta de datos de entrada
- Códigos de Estado HTTP: Respuestas apropiadas (200, 201, 204, 400, 404, 500)
- Manejo de Errores: Try-catch con mensajes descriptivos
- Base de Datos Automática: Creación automática de BD y tablas

**Frontend Educativo:**

- HTML Básico: Sin frameworks, formularios simples
- CSS Simple: Colores básicos, sin hover effects
- JavaScript Vanilla: ES5, sin librerías externas
- Interfaz Minimalista: Diseño años 90 para aprendizaje

**Características Técnicas:**

- JPA/Hibernate: Mapeo objeto-relacional automático
- MySQL Connector: Conexión nativa a base de datos
- Maven: Gestión de dependencias y build
- Tomcat Embebido: Servidor web integrado en puerto 8080

---

## Arquitectura del Sistema

```text
┌─────────────────────┐    ┌─────────────────┐    ┌──────────────────┐
│   Frontend Web      │───▶│  Spring Boot    │───▶│     MySQL        │
│ (HTML/CSS/JS)       │    │   REST API      │    │    Database      │
│   Port: 8080        │    │  /api/v1/*      │    │  property_db     │
└─────────────────────┘    └─────────────────┘    └──────────────────┘

        ▼
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│ PropertyController │───▶│ PropertyRepository │───▶│ Property Entity │
│  (REST Endpoints)  │    │  (JPA Interface)   │    │ (JPA @Entity)   │
└─────────────────┘    └──────────────────┘    └─────────────────┘

        ▼
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│   Web Browser   │───▶│   Fetch API      │───▶│  JSON Response  │
│   (Cliente)     │    │ (AJAX Calls)     │    │  (API Data)     │
└─────────────────┘    └──────────────────┘    └─────────────────┘
```

---

## Inicio Rápido

### Prerrequisitos

- [Java 21+](https://adoptium.net/)
- [Maven 3.6+](https://maven.apache.org/)
- [MySQL 8.0+](https://dev.mysql.com/downloads/mysql/) (o MySQL 9.4)

### Configuración de Base de Datos

```sql
# La base de datos se crea automáticamente, solo asegúrate de que MySQL esté ejecutándose con:
# Usuario: root
# Contraseña: root

# Opcional: Crear manualmente la base de datos
CREATE DATABASE IF NOT EXISTS property_db;

# Opcional: Ejecutar script con datos de ejemplo
mysql -u root -p < database-setup.sql
```

### Ejecución Local

```bash
# Clonar el repositorio
git clone <tu-repositorio-url>
cd property-crud-system

# Compilar el proyecto
mvn clean compile

# Ejecutar la aplicación
mvn spring-boot:run
```

**Accede en:**
- **Interfaz Web**: [http://localhost:8080](http://localhost:8080)
- **API REST**: [http://localhost:8080/api/v1/properties](http://localhost:8080/api/v1/properties)

### Ejecución con JAR

```bash
# Crear JAR ejecutable
mvn clean package

# Ejecutar JAR
java -jar target/property-crud-system-0.0.1-SNAPSHOT.jar
```

### Ejecución en segundo plano

```bash
# Ejecutar en background (Linux/Mac)
nohup mvn spring-boot:run &

# Ver logs
tail -f nohup.out

# Detener aplicación
pkill -f "spring-boot:run"
```

---

## API Endpoints

| Método | Endpoint                  | Parámetros           | Descripción                   | Status Codes       |
| ------ | ------------------------- | -------------------- | ----------------------------- | ------------------ |
| GET    | `/api/v1/properties`      | Ninguno              | Obtener todas las propiedades | 200, 500           |
| GET    | `/api/v1/properties/{id}` | id (path)            | Obtener propiedad por ID      | 200, 404, 500      |
| POST   | `/api/v1/properties`      | JSON body            | Crear nueva propiedad         | 201, 400, 500      |
| PUT    | `/api/v1/properties/{id}` | id (path), JSON body | Actualizar propiedad          | 200, 400, 404, 500 |
| DELETE | `/api/v1/properties/{id}` | id (path)            | Eliminar propiedad            | 204, 404, 500      |

### Ejemplos de uso:

```bash
# Obtener todas las propiedades
curl http://localhost:8080/api/v1/properties

# Crear nueva propiedad
curl -X POST http://localhost:8080/api/v1/properties \
  -H "Content-Type: application/json" \
  -d '{"address":"123 Main St","price":250000,"size":100,"description":"Casa bonita"}'

# Actualizar propiedad
curl -X PUT http://localhost:8080/api/v1/properties/1 \
  -H "Content-Type: application/json" \
  -d '{"address":"123 Main St","price":275000,"size":100,"description":"Casa actualizada"}'

# Eliminar propiedad
curl -X DELETE http://localhost:8080/api/v1/properties/1
```

**Acceso a la aplicación:**
- **Interfaz Web**: [http://localhost:8080](http://localhost:8080)
- **API REST**: [http://localhost:8080/api/v1/properties](http://localhost:8080/api/v1/properties)

---

## Configuración de Base de Datos

La aplicación utiliza MySQL con configuración automática:

```properties
# Configuración automática en application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/property_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**Personalización de credenciales:**

```bash
# Cambiar usuario/contraseña en application.properties
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

# O usar variables de entorno
export DB_USERNAME=tu_usuario
export DB_PASSWORD=tu_contraseña
java -jar target/property-crud-system-0.0.1-SNAPSHOT.jar
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

## Comandos de Desarrollo

```bash
# Compilar proyecto
mvn clean compile

# Ejecutar tests
mvn test

# Crear JAR ejecutable
mvn clean package

# Ejecutar aplicación
mvn spring-boot:run

# Limpiar proyecto
mvn clean

# Ver dependencias
mvn dependency:tree

# Verificar formato de código
mvn checkstyle:check
```

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

- **address**: Requerido, no puede estar vacío
- **price**: Requerido, debe ser mayor que 0
- **size**: Requerido, debe ser mayor que 0
- **description**: Opcional, puede ser nulo

---

## Solución de Problemas

### Problemas comunes:

```bash
# Error de conexión MySQL
# Solución: Verificar que MySQL esté ejecutándose
sudo systemctl start mysql  # Linux
brew services start mysql   # Mac

# Puerto 8080 ocupado
# Solución: Cambiar puerto en application.properties
server.port=8081

# Error de autenticación MySQL
# Solución: Verificar credenciales en application.properties
spring.datasource.username=root
spring.datasource.password=tu_contraseña_real
```

---

## Licencia

Este proyecto está licenciado bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## Autor

**Cristian David Polo Garrido** - Desarrollador Full Stack

- GitHub: [https://github.com/Cristian5124](https://github.com/Cristian5124)

---
