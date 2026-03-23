# 🎓 Spring EduManager

**Plataforma de Gestión Educativa** desarrollada con Spring Boot 4.0.4 y Java 21. Permite administrar estudiantes y cursos con autenticación JWT y base de datos en memoria H2.

---

## ✨ Características Principales

- ✅ **Autenticación JWT**: Sistema de tokens seguros para acceso sin sesión
- ✅ **CRUD Completo**: Crear, leer, actualizar y eliminar estudiantes y cursos
- ✅ **Control de Roles**: Restricciones por rol (USER, ADMIN)
- ✅ **API REST**: Endpoints JSON para integración con otros sistemas
- ✅ **Interfaz Web**: Vistas HTML con Thymeleaf y Bootstrap 5
- ✅ **Base de Datos In-Memory**: H2 con datos pre-cargados
- ✅ **Validación de Datos**: Email y campos requeridos
- ✅ **Seguridad**: Spring Security con autenticación BCrypt

---

## 🛠️ Stack Tecnológico

| Componente | Versión | Propósito |
|-----------|---------|----------|
| **Java** | 21 | Lenguaje de programación |
| **Spring Boot** | 4.0.4 | Framework principal |
| **Spring Security** | (integrado) | Autenticación y autorización |
| **JPA/Hibernate** | (integrado) | Persistencia de datos |
| **H2 Database** | (integrado) | Base de datos en memoria |
| **Thymeleaf** | (integrado) | Template engine |
| **Bootstrap** | 5.3.0 | Estilos CSS |
| **JWT (java-jwt)** | 4.4.0 | Tokens JWT |
| **Maven** | (incluido mvnw) | Build tool |

---

## 📋 Requisitos Previos

- **Java 21** o superior
- **Maven 3.6+** (o usar `mvnw` incluido)
- **Navegador moderno** (Chrome, Firefox, Edge)
- **Git** (opcional, para clonar el repositorio)

### Verificar Java

```bash
java -version
# Deberá mostrar: openjdk version "21" ...
```

---

## 🚀 Instalación y Ejecución

### 1️⃣ Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/SpringEduManager.git
cd SpringEduManager
```

### 2️⃣ Compilar el Proyecto

**Con Maven instalado:**
```bash
mvn clean compile
```

**Alternativamente (Windows):**
```bash
mvnw.cmd clean compile
```

**O (Linux/Mac):**
```bash
./mvnw clean compile
```

### 3️⃣ Iniciar la Aplicación

```bash
mvn spring-boot:run
```

**Salida esperada:**
```
...
o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http)
o.s.b.SpringApplication                   : Started SpringEduManagerApplication in X.XXX seconds
```

### 4️⃣ Acceder a la Aplicación

Abre tu navegador y ve a:
```
http://localhost:8080/login
```

---

## 👤 Credenciales de Prueba

| Usuario | Contraseña | Rol | Permisos |
|---------|-----------|-----|----------|
| `user` | `user` | USER | Ver/editar estudiantes |
| `admin` | `admin` | ADMIN | Todo (incluyendo cursos) |

---

## 📱 Flujo de la Aplicación

```
┌─────────────────┐
│    Homepage     │ http://localhost:8080/
└────────┬────────┘
         │
         ├─→ [Sin autenticación] → Info pública
         │
         └─→ [Autenticado] → Dashboard
                    │
                    ├─→ Estudiantes (CRUD)
                    └─→ Cursos (Solo ADMIN)
```

---

## 🌐 Rutas Principales

### Vistas HTML (MVC)

| Ruta | Método | Descripción |
|------|--------|-------------|
| `/` | GET | Página de inicio pública |
| `/login` | GET | Formulario de login |
| `/dashboard` | GET | Página de bienvenida autenticada |
| `/estudiantes` | GET | Listar estudiantes |
| `/estudiantes/nuevo` | GET | Formulario crear estudiante |
| `/estudiantes/{id}` | GET | Formulario editar estudiante |
| `/cursos` | GET | Listar cursos (público) |
| `/cursos/nuevo` | GET | Crear curso (solo ADMIN) |
| `/cursos/{id}` | GET | Editar curso (solo ADMIN) |

### API REST (JSON)

| Endpoint | Método | Descripción | Auth |
|----------|--------|-------------|------|
| `/api/auth/login` | POST | Obtener token JWT | ❌ |
| `/api/estudiantes` | GET | Listar estudiantes | ✅ |
| `/api/estudiantes` | POST | Crear estudiante | ✅ |
| `/api/estudiantes/{id}` | GET | Obtener estudiante | ✅ |
| `/api/estudiantes/{id}` | PUT | Actualizar estudiante | ✅ |
| `/api/estudiantes/{id}` | DELETE | Eliminar estudiante | ✅ |
| `/api/cursos` | GET | Listar cursos | ✅ |
| `/api/cursos` | POST | Crear curso | ✅ ADMIN |
| `/api/cursos/{id}` | GET | Obtener curso | ✅ |
| `/api/cursos/{id}` | PUT | Actualizar curso | ✅ ADMIN |
| `/api/cursos/{id}` | DELETE | Eliminar curso | ✅ ADMIN |

---

## 🔐 Autenticación con JWT

### Obtener Token

**Request (curl):**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "user",
    "password": "user"
  }'
```

**Response (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "username": "user",
  "role": "USER",
  "message": "Autenticación exitosa"
}
```

### Usar el Token

En todas las peticiones protegidas, incluir el header:

```bash
curl -X GET http://localhost:8080/api/estudiantes \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

---

## 📊 Estructura del Proyecto

```
SpringEduManager/
├── src/
│   ├── main/
│   │   ├── java/com/bootcamp/SpringEduManager/
│   │   │   ├── controller/              # Controladores MVC y REST
│   │   │   │   ├── HomeController.java
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── EstudianteController.java
│   │   │   │   ├── EstudianteRestController.java
│   │   │   │   ├── CursoController.java
│   │   │   │   └── CursoRestController.java
│   │   │   ├── model/                  # Entidades JPA
│   │   │   │   ├── Estudiante.java
│   │   │   │   └── Curso.java
│   │   │   ├── service/                # Lógica de negocio
│   │   │   │   ├── EstudianteService.java
│   │   │   │   └── CursoService.java
│   │   │   ├── repository/             # Data Access Objects
│   │   │   │   ├── EstudianteRepository.java
│   │   │   │   └── CursoRepository.java
│   │   │   ├── security/               # Configuración de seguridad
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   ├── JwtTokenProvider.java
│   │   │   │   ├── JwtAuthenticationFilter.java
│   │   │   │   └── JwtProperties.java
│   │   │   ├── config/                 # Configuración
│   │   │   │   └── JwtProperties.java
│   │   │   ├── dto/                    # Data Transfer Objects
│   │   │   │   ├── AuthRequest.java
│   │   │   │   └── AuthResponse.java
│   │   │   └── SpringEduManagerApplication.java
│   │   ├── resources/
│   │   │   ├── application.properties  # Configuración de la app
│   │   │   ├── data.sql               # Datos iniciales
│   │   │   ├── static/                # Archivos CSS, JS, imágenes
│   │   │   └── templates/             # Vistas Thymeleaf
│   │   │       ├── index.html
│   │   │       ├── login.html
│   │   │       ├── dashboard.html
│   │   │       ├── estudiantes/
│   │   │       │   ├── list.html
│   │   │       │   └── form.html
│   │   │       └── cursos/
│   │   │           ├── list.html
│   │   │           └── form.html
│   └── test/                           # Tests unitarios
│       └── java/com/bootcamp/SpringEduManager/
│           └── SpringEduManagerApplicationTests.java
├── pom.xml                             # Dependencias Maven
├── mvnw                                # Maven Wrapper (Linux/Mac)
├── mvnw.cmd                            # Maven Wrapper (Windows)
├── paso_a_paso.md                      # Documentación técnica paso a paso
├── JWT_GUIA.md                         # Guía de uso de JWT
└── README.md                           # Este archivo
```

---

## 🎯 Ejemplos de Uso

### 1. Login y Obtener Token

```bash
# Login
TOKEN=$(curl -s -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin"
  }' | jq -r '.token')

echo "Token: $TOKEN"
```

### 2. Crear un Estudiante (API)

```bash
curl -X POST http://localhost:8080/api/estudiantes \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Pérez",
    "email": "juan@example.com"
  }'
```

### 3. Listar Todos los Estudiantes

```bash
curl -X GET http://localhost:8080/api/estudiantes \
  -H "Authorization: Bearer $TOKEN"
```

### 4. Actualizar un Estudiante

```bash
curl -X PUT http://localhost:8080/api/estudiantes/1 \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Carlos Pérez",
    "email": "juancarlos@example.com"
  }'
```

### 5. Eliminar un Estudiante

```bash
curl -X DELETE http://localhost:8080/api/estudiantes/1 \
  -H "Authorization: Bearer $TOKEN"
```

---

## ⚙️ Configuración

### application.properties

```properties
# Servidor
spring.application.name=SpringEduManager

# Base de datos H2
spring.datasource.url=jdbc:h2:mem:edudb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.defer-datasource-initialization=true
spring.jpa.show-sql=false

# JWT
jwt.secret=SpringEduManagerSecretKey2026
jwt.expiration=86400000  # 24 horas en milisegundos

# Logging
logging.level.org.springframework.security=WARN
```

### ⚠️ Seguridad en Producción

Antes de desplegar a producción:

1. **Cambiar jwt.secret** por una clave segura y única
2. **Usar HTTPS** en lugar de HTTP
3. **No pushear credenciales** en repositorios públicos
4. **Usar variables de entorno** para almacenar secrets
5. **Implementar CORS** según necesidad
6. **Habilitar CSRF** protection en formularios
7. **Configurar refresh tokens** para mayor seguridad

---

## 🧪 Testing

### Ejecutar Tests

```bash
mvn test
```

### Test con Maven y Coverage

```bash
mvn clean test jacoco:report
```

---

## 📊 Base de Datos

### Datos Iniciales (data.sql)

Al iniciar la aplicación, se crean automáticamente:

**Estudiantes (5 registros):**
- María García - maria@bootcamp.edu
- Carlos López - carlos@bootcamp.edu
- Ana Martínez - ana@bootcamp.edu
- David Rodríguez - david@bootcamp.edu
- Sofia Fernández - sofia@bootcamp.edu

**Cursos (5 registros):**
- Java Básico - Fundamentos de Java
- Spring Boot - Desarrollo con Spring Boot
- REST APIs - Construcción de APIs REST
- Bases de Datos - SQL y JPA
- Seguridad - Spring Security y JWT

### Acceso a H2 Console

URL: `http://localhost:8080/h2-console`

```
JDBC URL: jdbc:h2:mem:edudb
User Name: sa
Password: (dejar en blanco)
```

---

## 🐛 Solución de Problemas

### Puerto 8080 Ya en Uso

```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Linux/Mac
lsof -ti:8080 | xargs kill -9
```

### Error de Compilación

```bash
mvn clean compile
```

### H2 - Tabla No Encontrada

Verificar que `spring.jpa.defer-datasource-initialization=true` está en `application.properties`

---

## 📚 Documentación Adicional

- [paso_a_paso.md](paso_a_paso.md) - Construcción paso a paso del proyecto
- [JWT_GUIA.md](JWT_GUIA.md) - Guía completa de JWT
- [Spring Boot Official Docs](https://spring.io/projects/spring-boot)
- [Spring Security Docs](https://spring.io/projects/spring-security)

---

## 🚢 Despliegue

### Docker

```dockerfile
FROM openjdk:21
COPY target/SpringEduManager-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

### Maven Build

```bash
mvn clean package
java -jar target/SpringEduManager-0.0.1-SNAPSHOT.jar
```

---

## 👨‍💻 Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/nueva-caracteristica`)
3. Commit tus cambios (`git commit -m 'Añade nueva característica'`)
4. Push a la rama (`git push origin feature/nueva-caracteristica`)
5. Abre un Pull Request

---

## 📄 Licencia

Este proyecto está bajo licencia **MIT**. Consulta el archivo [LICENSE](LICENSE) para más detalles.

---

## 📞 Soporte

Para reportar bugs o solicitar features, abre un [Issue](https://github.com/tu-usuario/SpringEduManager/issues).

---

## 📈 Roadmap

- [ ] Implementar refresh tokens
- [ ] Agregar paginación a listados
- [ ] Export a PDF/Excel
- [ ] Dashboard administrativo
- [ ] Notificaciones por email
- [ ] Autenticación OAuth2
- [ ] Documentación con Swagger/OpenAPI

---

## 🎓 Créditos

Desarrollado como parte del **Bootcamp de Java y Spring Boot**.

Última actualización: **22 de marzo de 2026**

---

<div align="center">

**🌟 Si te fue útil, no olvides darle una ⭐ en GitHub 🌟**

</div>
