package com.bootcamp.SpringEduManager.service;

import com.bootcamp.SpringEduManager.model.Estudiante;
import com.bootcamp.SpringEduManager.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de lógica de negocio para Estudiante.
 * Maneja las operaciones CRUD con validaciones.
 */
@Service
public class EstudianteService {

    private final EstudianteRepository repository;

    // Inyección de dependencias por constructor
    public EstudianteService(EstudianteRepository repository) {
        this.repository = repository;
    }

    // Obtiene todos los estudiantes
    public List<Estudiante> findAll() {
        return repository.findAll();
    }

    // Busca un estudiante por ID, lanzando excepción si es nulo
    public Optional<Estudiante> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del estudiante no puede ser nulo");
        }
        return repository.findById(id);
    }

    // Guarda un estudiante, validando que no sea nulo
    public Estudiante save(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo");
        }
        return repository.save(estudiante);
    }

    // Elimina un estudiante por ID, validando que no sea nulo
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del estudiante no puede ser nulo");
        }
        repository.deleteById(id);
    }
}
