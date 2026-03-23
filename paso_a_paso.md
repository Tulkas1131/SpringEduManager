# Paso a Paso: SpringEduManager

Este documento describe paso a paso cómo se ha construido la aplicación web **SpringEduManager** desde cero. Cada etapa cumple con los lineamientos indicados en el Módulo 6 sobre "Desarrollo de aplicaciones JEE con Spring framework".

## Lección 1: El gestor de proyectos
### 1. Inicializar el Proyecto Spring Boot
El sistema se generó mediante el uso de Maven, importando la estructura clásica de Spring Boot (versión 3.2.x):
- **Tipo de Proyecto**: Maven Project
- **Lenguaje**: Java 17
- **Group ID**: `com.bootcamp`
- **Artifact ID**: `SpringEduManager`
- **Dependencias**: 
  - `spring-boot-starter-web` (Web + REST)
  - `spring-boot-starter-data-jpa` (Persistencia)
  - `spring-boot-starter-security` (Seguridad)
  - `spring-boot-starter-thymeleaf` y `thymeleaf-extras-springsecurity6` (Vistas)
  - `h2` (Base de Datos embebida)

### 2. Validar Ciclo de Vida
Una vez descargada la carpeta inicial, validamos la instalación ejecutando los ciclos básicos de compilación a través del script de Maven:
```bash
mvnw clean install package
```

---

## Lección 2: El Framework Spring MVC
### 1. Estructura de Capas
Creamos la división en paquetes sobre `src/main/java/com/bootcamp/SpringEduManager`:
- `model`: Modelado de Base de Datos.
- `repository`: Inserción de JPA.
- `service`: Lógica de negocios.
- `controller`: Exposición de vistas y APIs.
- `security`: Parámetros de Seguridad de la App.

### 2. Entidades Principales
Creación de clases simples (POJOs) anotadas bajo el ecosistema de Jakarta (`@Entity`):
- `Estudiante.java`: Parámetros id (Anotaciones `@Id` y `@GeneratedValue`), nombre y email.
- `Curso.java`: Parámetros id, nombre y descripción.

### 3. Vistas Interactivas
Se han agregado plantillas HTML vinculadas bajo el motor **Thymeleaf** (dentro de `src/main/resources/templates/`):
- `index.html` (Landing Page Principal).
- `login.html` (Desarrollo visual del ingreso de credenciales).
- `estudiantes/list.html` y `estudiantes/form.html` (Grilla y Formulario de altas).
- `cursos/list.html` y `cursos/form.html` (Grilla y Formulario de altas).

---

## Lección 3: Acceso a Datos en Spring Framework
### 1. Configurar H2 Database
Dentro del archivo `src/main/resources/application.properties`, se han definido las credenciales iniciales de nuestra base de persistencia:
```properties
spring.datasource.url=jdbc:h2:mem:edudb
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```

### 2. Repositorios JPA y Capa de Dominio
Declaramos interfaces de manipulación a base de datos (Data Access Objects) que extienden `JpaRepository<Entidad, ID>`:
- `EstudianteRepository.java`
- `CursoRepository.java`

Para gestionar toda la inyección de estas dependencias (`@Autowired`), encapsulamos métodos en la capa `Servicio`. 
Estando listos se vincularon a `EstudianteController` mediante un `@GetMapping("/estudiantes")` inyectando colecciones dinámicas mediante la interfaz de Spring MVC `Model`.

---

## Lección 4: Control de acceso mediante Spring Security
### 1. Interceptar Exposición Http
Se agregó la configuración sobre escribiendo funciones al contexto con la clase `SecurityConfig.java` (anotada con `@EnableWebSecurity`). 
Se habilitó que rutas críticas como los cruds de Estudiantes, estén protegidos pero ciertas rutas esten liberadas bajo el path `permitAll()` como el index y la consola h2.

### 2. Autenticación Local y Autorización por Roles
1. Se generó un Bean `InMemoryUserDetailsManager` que codificó un Usuario y un Administrador como usuarios locales.
  - Usuario: `user`
  - Administrador: `admin`
2. Se protegió con la anotación `@PreAuthorize("hasRole('ADMIN')")` las clases/rutas como el alta de cursos (Post) y su vista Formulario (Get). 
3. Usando el dialecto Extra de Thymeleaf `sec:authorize="hasRole('ADMIN')"` se ocultó dinámicamente el botón "Nuevo Curso" de los usuarios que solo pertenecen a la categoría 'USER'.

---

## Lección 5: La Interoperabilidad entre los sistemas
### 1. Exponer la API Restful
Mapeamos en controladores adicionales peticiones exclusivas para ser leídas por otros clientes implementando `@RestController` (no devuelven plantillas Vistas, sino contenido de sistema JSON nativo) vinculados al path predeterminado global `/api/`.
- `EstudianteRestController.java`
- `CursoRestController.java`

### 2. Uso con Postman 
El sistema soporta por naturaleza desde el minuto cero que un cliente como Postman interactúe contra métodos como:
- **GET** `http://localhost:8080/api/estudiantes`
- **POST** a la misma URL para dar de alta información enviando un body crudo y mapeado con un Header de Json. 

Para habilitar la depuración sin dolores de cabeza, estas URL fueron exceptuadas del control de la capa de Sesión en el config inicial.
