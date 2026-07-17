# Employee Dashboard Backend

## Descripción

Employee Dashboard es una aplicación backend desarrollada con **Spring Boot** para la administración de empleados y sus beneficios.

La aplicación expone una API REST que permite realizar operaciones CRUD sobre empleados y beneficios. Además, integra la API pública de **OpenStreetMap Nominatim** para obtener información geográfica de la ciudad asociada a cada empleado.

El proyecto fue desarrollado siguiendo principios de **Clean Code**, una arquitectura por capas y acompañado de pruebas unitarias utilizando **JUnit 5** y **Mockito**.

---

# Tecnologías Utilizadas

- Java 17
- Spring Boot 4.1.0
- Spring Data JPA
- Hibernate ORM
- PostgreSQL (Neon)
- Maven
- Lombok
- JUnit 5
- Mockito
- OpenStreetMap Nominatim API
- XML Parsing (DOM)

---

# Arquitectura

El proyecto sigue una arquitectura por capas (Layered Architecture).

```
src/main/java
│
├── benefit
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
│
├── employee
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
│
├── location
│   ├── client
│   ├── dto
│   ├── parser
│   └── service
│
├── exception
│
└── EmployeeDashboardApplication
```

Cada capa tiene una responsabilidad específica:

- **Controller:** recibe las peticiones HTTP.
- **Service:** contiene la lógica de negocio.
- **Repository:** acceso a la base de datos mediante Spring Data JPA.
- **Entity:** representa las tablas de la base de datos.
- **DTO:** objetos utilizados para las solicitudes y respuestas de la API.
- **Client:** integración con servicios externos.
- **Parser:** procesamiento de respuestas XML.

---

# Modelo de Datos

## Employee

| Campo | Tipo |
|--------|------|
| id | Long |
| firstName | String |
| lastName | String |
| email | String |
| city | String |

## Benefit

| Campo | Tipo |
|--------|------|
| id | Long |
| benefitName | String |
| amount | BigDecimal |

### Relación

```
Employee (1)
      │
      │
      └───────────────< Benefit (N)
```

---

# Funcionalidades

## Employees

- Crear empleado
- Obtener todos los empleados
- Obtener empleado por ID
- Actualizar empleado
- Eliminar empleado

## Benefits

- Crear beneficio
- Consultar beneficios por empleado
- Actualizar beneficio
- Eliminar beneficio

## Employee Details

Obtiene un resumen completo del empleado incluyendo:

- Información del empleado
- Beneficios asociados
- Ubicación geográfica obtenida desde OpenStreetMap

---

# Endpoints REST

## Employees

| Método | Endpoint | Descripción |
|---------|----------|-------------|
| GET | /employees | Lista todos los empleados |
| GET | /employees/{id} | Consulta un empleado |
| GET | /employees/{id}/details | Información completa del empleado |
| POST | /employees | Crear empleado |
| PUT | /employees/{id} | Actualizar empleado |
| DELETE | /employees/{id} | Eliminar empleado |

---

## Benefits

| Método | Endpoint | Descripción |
|---------|----------|-------------|
| GET | /benefits/employee/{employeeId} | Beneficios de un empleado |
| POST | /benefits | Crear beneficio |
| PUT | /benefits/{id} | Actualizar beneficio |
| DELETE | /benefits/{id} | Eliminar beneficio |

---

# Configuración

Editar el archivo:

```
src/main/resources/application.properties
```

Ejemplo:

```properties
spring.application.name=employee-dashboard

spring.datasource.url=jdbc:postgresql://YOUR_HOST/neondb?sslmode=require

spring.datasource.username=YOUR_USERNAME

spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
```

---

# Instalación

## 1. Clonar el proyecto

```bash
git clone <repository-url>
```

## 2. Entrar al proyecto

```bash
cd employee-dashboard
```

## 3. Compilar

```bash
mvn clean install
```

## 4. Ejecutar

```bash
mvn spring-boot:run
```

La aplicación quedará disponible en:

```
http://localhost:8080
```

---

# Ejecutar las Pruebas

Para ejecutar todas las pruebas unitarias:

```bash
mvn test
```

Resultado esperado:

```
Tests run: 14
Failures: 0
Errors: 0
```

---

# Ejemplos de Respuesta

## GET /employees

```json
[
  {
    "id": 1,
    "firstName": "Elkin",
    "lastName": "Nocua",
    "email": "elkin@example.com",
    "city": "Bogotá"
  }
]
```

---

## GET /employees/1/details

```json
{
  "id": 1,
  "firstName": "Elkin",
  "lastName": "Nocua",
  "email": "elkin@example.com",
  "city": "Bogotá",
  "location": {
    "displayName": "Bogotá, Colombia",
    "latitude": 4.6533817,
    "longitude": -74.0836331
  },
  "benefits": [
    {
      "id": 1,
      "benefitName": "Health Insurance",
      "amount": 500000.00,
      "employeeId": 1
    }
  ]
}
```

---

# Manejo de Excepciones

La aplicación implementa un manejo centralizado de errores mediante `@RestControllerAdvice`.

Excepciones personalizadas:

- EmployeeNotFoundException
- BenefitNotFoundException

Ejemplo de respuesta:

```json
{
  "timestamp": "2026-07-17T00:02:30.3658714",
  "status": 404,
  "message": "Employee with id 999 not found"
}
```

---

# Pruebas Unitarias

Se implementaron pruebas unitarias utilizando:

- JUnit 5
- Mockito

Cobertura implementada:

- EmployeeService
- BenefitService
- LocationService

Resultado actual:

- **14 pruebas unitarias exitosas**

---

# Decisiones de Diseño

Durante el desarrollo se aplicaron las siguientes prácticas:

- Arquitectura por capas (Layered Architecture)
- Principio de responsabilidad única (SRP)
- Inversión de dependencias mediante Spring Dependency Injection
- Patrón Repository con Spring Data JPA
- Uso de DTOs para desacoplar las entidades de la API
- Manejo centralizado de excepciones
- Integración con servicios externos encapsulada en una capa Client
- Separación entre lógica de negocio y acceso a datos
- Pruebas unitarias con Mockito para aislar dependencias

---

# Autor

**Elkin Nocua**