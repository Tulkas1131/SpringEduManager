package com.bootcamp.SpringEduManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entidad que representa un estudiante en la base de datos.
 * Mapea automaticamente a una tabla llamada 'estudiante'.
 */
@Entity
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;             // Identificador único, auto-generado
    private String nombre;       // Nombre del estudiante
    private String email;        // Email del estudiante

    // Constructor vacío requerido por JPA
    public Estudiante() {
    }

    // Constructor con parámetros
    public Estudiante(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
