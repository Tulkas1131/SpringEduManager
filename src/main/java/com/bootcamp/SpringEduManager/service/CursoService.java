package com.bootcamp.SpringEduManager.service;

import com.bootcamp.SpringEduManager.model.Curso;
import com.bootcamp.SpringEduManager.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de lógica de negocio para Curso.
 * Maneja las operaciones CRUD con validaciones.
 */
@Service
public class CursoService {

    private final CursoRepository repository;

    // Inyección de dependencias por constructor
    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    // Obtiene todos los cursos
    public List<Curso> findAll() {
        return repository.findAll();
    }

    // Busca un curso por ID, lanzando excepción si es nulo
    public Optional<Curso> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del curso no puede ser nulo");
        }
        return repository.findById(id);
    }

    // Guarda un curso, validando que no sea nulo
    public Curso save(Curso curso) {
        if (curso == null) {
            throw new IllegalArgumentException("El curso no puede ser nulo");
        }
        return repository.save(curso);
    }

    // Elimina un curso por ID, validando que no sea nulo
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del curso no puede ser nulo");
        }
        repository.deleteById(id);
    }
}
