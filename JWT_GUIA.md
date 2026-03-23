# Guía de Uso - JWT en SpringEduManager

## 📝 Autenticación con JWT

Este proyecto implementa seguridad basada en **JWT (JSON Web Tokens)** para los endpoints REST.

## 🔑 Credenciales Disponibles

```
Usuario: user      | Contraseña: user   | Rol: USER
Usuario: admin     | Contraseña: admin  | Rol: ADMIN
```

## 🚀 Pasos para Usar JWT

### 1. Obtener el Token (Login)

**Endpoint:** `POST /api/auth/login`

**Ejemplo con curl:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "user",
    "password": "user"
  }'
```

**Respuesta exitosa (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "username": "user",
  "role": "USER",
  "message": "Autenticación exitosa"
}
```

### 2. Usar el Token en Peticiones

**Agregar el token en el encabezado `Authorization`:**

```bash
curl -X GET http://localhost:8080/api/estudiantes \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

**Ejemplo con Postman:**
1. Abre Postman
2. Ve a la pestaña "Authorization"
3. Selecciona "Bearer Token" en el dropdown
4. Pega el token en el campo de texto

### 3. Crear un Estudiante

**Endpoint:** `POST /api/estudiantes`

```bash
curl -X POST http://localhost:8080/api/estudiantes \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer TU_TOKEN_AQUI" \
  -d '{
    "nombre": "Juan Pérez",
    "email": "juan@bootcamp.com"
  }'
```

### 4. Listar Estudiantes

**Endpoint:** `GET /api/estudiantes`

```bash
curl -X GET http://localhost:8080/api/estudiantes \
  -H "Authorization: Bearer TU_TOKEN_AQUI"
```

## 🔒 Operaciones Disponibles

| Método | Endpoint | Rol Requerido | Descripción |
|--------|----------|---------------|-------------|
| POST | /api/auth/login | PÚBLICO | Obtener token JWT |
| GET | /api/auth/verify | Autenticado | Verificar que el token es válido |
| GET | /api/estudiantes | Autenticado | Listar todos los estudiantes |
| GET | /api/estudiantes/{id} | Autenticado | Obtener un estudiante por ID |
| POST | /api/estudiantes | Autenticado | Crear nuevo estudiante |
| PUT | /api/estudiantes/{id} | Autenticado | Actualizar estudiante |
| DELETE | /api/estudiantes/{id} | Autenticado | Eliminar estudiante |
| GET | /api/cursos | Autenticado | Listar todos los cursos |
| POST | /api/cursos | ADMIN | Crear nuevo curso |
| PUT | /api/cursos/{id} | ADMIN | Actualizar curso |
| DELETE | /api/cursos/{id} | ADMIN | Eliminar curso |

## ⚙️ Configuración

Las propiedades JWT se pueden ajustar en `application.properties`:

```properties
# Clave secreta para firmar tokens (CAMBIAR EN PRODUCCIÓN)
jwt.secret=SpringEduManagerSecretKey2026

# Tiempo de expiración en milisegundos (86400000 = 24 horas)
jwt.expiration=86400000
```

## ⚠️ Seguridad en Producción

**IMPORTANTE:** 
- Cambiar `jwt.secret` por una clave única y segura
- Usar HTTPS en lugar de HTTP
- No exponer el `.secret` en repositorios públicos
- Usar variables de entorno para almacenar credenciales
- Implementar refresh tokens para mayor seguridad

## 🧪 Prueba con Postman

1. **Crear environment variable:**
   - Variable: `token`
   - Valor: (se llenará automáticamente)

2. **Script de Login (Tests):**
```javascript
if (pm.response.code === 200) {
    var jsonData = pm.response.json();
    pm.environment.set("token", jsonData.token);
}
```

3. **Usar en headers:**
   - Authorization: `Bearer {{token}}`
