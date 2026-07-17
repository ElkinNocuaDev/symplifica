# Employee Dashboard

Prueba técnica desarrollada para **Symplifica**.

El proyecto consiste en una aplicación Full Stack para la administración de empleados y beneficios, compuesta por un backend desarrollado con **Spring Boot** y un frontend desarrollado con **Vue 3**.

---

# Arquitectura del proyecto

```
symplifica
│
├── employee-dashboard        # Backend Spring Boot
│
└── employee-dashboard-ui     # Frontend Vue 3 + Vite
```

---

# Tecnologías utilizadas

## Backend

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok
- JUnit 5
- Mockito

## Frontend

- Vue 3
- Vite
- Axios

## API Externa

- OpenStreetMap Nominatim API

---

# Funcionalidades

## Gestión de empleados

- Crear empleado
- Consultar empleados
- Consultar empleado por ID
- Actualizar empleado
- Eliminar empleado

## Gestión de beneficios

- Crear beneficio
- Consultar beneficios por empleado
- Actualizar beneficio
- Eliminar beneficio

## Información geográfica

Consulta automática de:

- Ciudad
- Latitud
- Longitud

mediante la API pública de OpenStreetMap Nominatim.

---

# Características implementadas

- Arquitectura por capas
- DTO Pattern
- Repository Pattern
- Manejo global de excepciones
- Excepciones personalizadas
- Validaciones
- Integración con API externa
- Comunicación Frontend ↔ Backend mediante REST
- Pruebas unitarias
- Clean Code

---

# Estructura del Backend

```
employee-dashboard

src
 ├── employee
 ├── benefit
 ├── location
 ├── exception
 └── ...
```

---

# Estructura del Frontend

```
employee-dashboard-ui

src
 ├── api
 ├── components
 ├── views
 └── ...
```

---

# Ejecución del Backend

Entrar a:

```
employee-dashboard
```

Ejecutar:

```bash
mvn spring-boot:run
```

El backend iniciará por defecto en:

```
http://localhost:8080
```

---

# Ejecución del Frontend

Entrar a:

```
employee-dashboard-ui
```

Instalar dependencias:

```bash
npm install
```

Ejecutar:

```bash
npm run dev
```

El frontend iniciará por defecto en:

```
http://localhost:5173
```

---

# Pruebas

Backend:

```bash
mvn test
```

---

# Funcionalidad principal

La aplicación permite:

- administrar empleados
- administrar beneficios
- consultar la ubicación geográfica de la ciudad del empleado
- visualizar toda la información desde un Dashboard desarrollado en Vue 3

---

# Autor

**Elkin Nocua**

GitHub:

https://github.com/ElkinNocuaDev