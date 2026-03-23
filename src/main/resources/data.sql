-- Datos iniciales para la base de datos H2
-- Este archivo se ejecuta automáticamente al iniciar la aplicación

-- Limpiar datos existentes
DELETE FROM estudiante;
DELETE FROM curso;

-- Reiniciar secuencias (para H2)
ALTER TABLE estudiante ALTER COLUMN id RESTART WITH 1;
ALTER TABLE curso ALTER COLUMN id RESTART WITH 1;

-- Insertar Estudiantes
INSERT INTO estudiante (nombre, email) VALUES ('Juan Pérez García', 'juan.perez@bootcamp.com');
INSERT INTO estudiante (nombre, email) VALUES ('María García López', 'maria.garcia@bootcamp.com');
INSERT INTO estudiante (nombre, email) VALUES ('Carlos Romero Silva', 'carlos.romero@bootcamp.com');
INSERT INTO estudiante (nombre, email) VALUES ('Ana Martínez Ruiz', 'ana.martinez@bootcamp.com');
INSERT INTO estudiante (nombre, email) VALUES ('Pedro Sánchez Díaz', 'pedro.sanchez@bootcamp.com');

-- Insertar Cursos
INSERT INTO curso (nombre, descripcion) VALUES ('Java Avanzado', 'Curso completo de Java con programación orientada a objetos, colecciones y streams');
INSERT INTO curso (nombre, descripcion) VALUES ('Spring Boot Profesional', 'Desarrollo de aplicaciones web robustas con Spring Boot, JPA y REST APIs');
INSERT INTO curso (nombre, descripcion) VALUES ('Base de Datos SQL', 'Diseño de bases de datos relacionales, consultas optimizadas y transacciones');
INSERT INTO curso (nombre, descripcion) VALUES ('Frontend con React', 'Desarrollo de interfaces interactivas con React, hooks y estado global');
INSERT INTO curso (nombre, descripcion) VALUES ('DevOps y Contenedores', 'Docker, Kubernetes y CI/CD pipelines para automatización');
