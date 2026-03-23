# 🎓 SpringEduManager

**Plataforma Integral de Gestión Educativa** desarrollada con Spring Boot 4.0.4 y Java 21. Sistema completo para administrar estudiantes y cursos con autenticación JWT segura, base de datos en memoria H2 y API REST.

---

## ✨ Características Principales

- ✅ **Autenticación JWT**: Tokens seguros con localStorage, sin sesiones stateful
- ✅ **CRUD Completo**: Crear, leer, actualizar y eliminar para estudiantes y cursos
- ✅ **Control de Roles**: RBAC (Role-Based Access Control) - USER y ADMIN
- ✅ **Dual Interface**: MVC (Thymeleaf) + API REST (JSON)
- ✅ **Interfaz Responsive**: Bootstrap 5.3.0 con diseño adaptativo
- ✅ **Base de Datos Persistente**: H2 en memoria con datos pre-cargados (data.sql)
- ✅ **Validación Completa**: Campos requeridos, email válido, roles por endpoint
- ✅ **Seguridad Multinivel**: Spring Security, JWT, BCrypt, CORS habilitado

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

---

## 📦 Build y Empaquetado

### Compilar sin ejecutar

```bash
mvn clean compile
```

### Generar JAR Ejecutable

```bash
mvn clean package -DskipTests
# Resultado: target/SpringEduManager-0.0.1-SNAPSHOT.jar
```

### Generar WAR para Tomcat

```bash
mvn clean package -DskipTests
# Resultado: target/SpringEduManager-0.0.1-SNAPSHOT.war
# Copiar a: $CATALINA_HOME/webapps/SpringEduManager.war
```

---

## 🖥️ Acceso a la Aplicación

### Interfaces Disponibles

**Home Pública:**
```
http://localhost:8080/
```

**Login:**
```
http://localhost:8080/login
```

**Dashboard (después de login):**
```
http://localhost:8080/dashboard
```

**Gestión de Estudiantes:**
```
http://localhost:8080/estudiantes
```

**Gestión de Cursos:**
```
http://localhost:8080/cursos
```

**H2 Database Console:**
```
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:edudb
User: sa
Password: (vacío)
```

---

## 4️⃣ Acceder a la Aplicación

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

### Vistas HTML (MVC) - Acceso Público (sin autenticación requerida)

| Ruta | Método | Descripción |
|------|--------|-------------|
| `/` | GET | Página de inicio pública |
| `/login` | GET | Formulario de login |
| `/dashboard` | GET | Dashboard (home autenticado) |
| `/estudiantes` | GET | Listar todos los estudiantes |
| `/estudiantes/nuevo` | GET | Crear nuevo estudiante (form) |
| `/estudiantes/{id}` | GET | Editar estudiante (form) |
| `/estudiantes/eliminar/{id}` | GET | Eliminar estudiante |
| `/cursos` | GET | Listar todos los cursos |
| `/cursos/nuevo` | GET | Crear nuevo curso (form) |
| `/cursos/{id}` | GET | Editar curso (form) |
| `/cursos/eliminar/{id}` | GET | Eliminar curso |

**Nota**: Las operaciones POST (guardar) ocurren sin @PreAuthorize en MVC. La validación de rol se realiza en la interfaz HTML mediante localStorage.

### API REST (JSON) - Requiere JWT

| Endpoint | Método | Descripción | Rol |
|----------|--------|-------------|-----|
| `/api/auth/login` | POST | Obtener token JWT | ✖️ (Público) |
| `/api/estudiantes` | GET | Listar estudiantes | ✅ USER |
| `/api/estudiantes` | POST | Crear estudiante | ✅ USER |
| `/api/estudiantes/{id}` | GET | Obtener estudiante | ✅ USER |
| `/api/estudiantes/{id}` | PUT | Actualizar estudiante | ✅ USER |
| `/api/estudiantes/{id}` | DELETE | Eliminar estudiante | ✅ USER |
| `/api/cursos` | GET | Listar cursos | ✅ USER |
| `/api/cursos` | POST | Crear curso | ✅ ADMIN |
| `/api/cursos/{id}` | GET | Obtener curso | ✅ USER |
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
│   │   │   ├── SpringEduManagerApplication.java     # Entry point
│   │   │   │
│   │   │   ├── controller/
│   │   │   │   ├── HomeController.java              # Vistas públicas
│   │   │   │   ├── AuthController.java              # Login JWT
│   │   │   │   ├── EstudianteController.java        # CRUD MVC
│   │   │   │   ├── EstudianteRestController.java    # API REST
│   │   │   │   ├── CursoController.java             # CRUD MVC
│   │   │   │   └── CursoRestController.java         # API REST
│   │   │   │
│   │   │   ├── model/
│   │   │   │   ├── Estudiante.java                  # Entidad JPA
│   │   │   │   └── Curso.java                       # Entidad JPA
│   │   │   │
│   │   │   ├── repository/
│   │   │   │   ├── EstudianteRepository.java        # DAO Estudiante
│   │   │   │   └── CursoRepository.java             # DAO Curso
│   │   │   │
│   │   │   ├── service/
│   │   │   │   ├── EstudianteService.java           # Lógica negocio
│   │   │   │   └── CursoService.java                # Lógica negocio
│   │   │   │
│   │   │   ├── security/
│   │   │   │   ├── SecurityConfig.java              # Configuración Spring Security
│   │   │   │   ├── JwtTokenProvider.java            # Generación/Validación JWT
│   │   │   │   ├── JwtAuthenticationFilter.java     # Filtro JWT
│   │   │   │   └── JwtProperties.java               # Propiedades JWT
│   │   │   │
│   │   │   ├── dto/
│   │   │   │   ├── AuthRequest.java                 # {username, password}
│   │   │   │   └── AuthResponse.java                # {token, role, message}
│   │   │   │
│   │   │   └── config/
│   │   │       └── HttpSecurityConfig.java          # CORS y headers
│   │   │
│   │   └── resources/
│   │       ├── application.properties                # Config app (puerto, JWT, BD)
│   │       ├── data.sql                             # 5 estudiantes + 5 cursos
│   │       ├── static/                              # CSS, JS, imágenes
│   │       │   └── css/
│   │       │       └── bootstrap.css
│   │       │
│   │       └── templates/
│   │           ├── index.html                       # Home público
│   │           ├── login.html                       # Login (fetch JWT)
│   │           ├── dashboard.html                   # Home autenticado
│   │           │
│   │           ├── estudiantes/
│   │           │   ├── list.html                    # Tabla estudiantes
│   │           │   └── form.html                    # Crear/Editar
│   │           │
│   │           └── cursos/
│   │               ├── list.html                    # Tabla cursos
│   │               └── form.html                    # Crear/Editar
│   │
│   └── test/
│       └── java/com/bootcamp/SpringEduManager/
│           └── SpringEduManagerApplicationTests.java
│
├── target/
│   ├── SpringEduManager-0.0.1-SNAPSHOT.war         # Archivo WAR compilado
│   ├── SpringEduManager-0.0.1-SNAPSHOT.jar.original
│   └── classes/                                     # .class compilados
│
├── pom.xml                                          # Dependencias Maven
├── mvnw                                             # Maven Wrapper (Linux/Mac)
├── mvnw.cmd                                         # Maven Wrapper (Windows)
├── README.md                                        # Este archivo
├── JWT_GUIA.md                                      # Guía JWT avanzada
└── HELP.md                                          # Generado por Spring
```
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

### Generar Distributable

**JAR (Standalone):**
```bash
mvn clean package -DskipTests
java -jar target/SpringEduManager-0.0.1-SNAPSHOT.jar
```

**WAR (Servidor Tomcat):**
```bash
mvn clean package -DskipTests
# Copiar SpringEduManager-0.0.1-SNAPSHOT.war a $CATALINA_HOME/webapps/
```

### Docker

```dockerfile
# Dockerfile
FROM openjdk:21-slim
COPY target/SpringEduManager-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

```bash
# Construir imagen
docker build -t springedumanager:latest .

# Ejecutar contenedor
docker run -p 8080:8080 springedumanager:latest
```

### Variables de Entorno

```bash
java -Djwt.secret=TuClaveSegura \
     -Djwt.expiration=86400000 \
     -jar target/SpringEduManager-0.0.1-SNAPSHOT.jar
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

## 📞 Soporte y Community

Para reportar bugs, solicitar features o hacer preguntas:

- **Issues**: [GitHub Issues](https://github.com/Tulkas1131/SpringEduManager/issues)
- **Discussions**: [GitHub Discussions](https://github.com/Tulkas1131/SpringEduManager/discussions)
- **Email**: Contacto a través del perfil de GitHub

---

## 🔗 Enlaces Útiles

- **Repositorio**: https://github.com/Tulkas1131/SpringEduManager
- **Spring Boot Docs**: https://spring.io/projects/spring-boot
- **Spring Security**: https://spring.io/projects/spring-security
- **JWT Info**: https://jwt.io
- **H2 Database**: http://h2database.com

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
