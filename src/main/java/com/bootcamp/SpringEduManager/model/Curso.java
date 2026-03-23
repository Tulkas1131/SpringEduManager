package com.bootcamp.SpringEduManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entidad que representa un curso en la base de datos.
 * Mapea automaticamente a una tabla llamada 'curso'.
 */
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;              // Identificador único, auto-generado
    private String nombre;        // Nombre del curso
    private String descripcion;   // Descripción del curso

    // Constructor vacío requerido por JPA
    public Curso() {
    }

    // Constructor con parámetros
    public Curso(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
