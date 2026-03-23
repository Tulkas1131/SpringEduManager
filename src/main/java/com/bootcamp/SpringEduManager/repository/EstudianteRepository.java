package com.bootcamp.SpringEduManager.repository;

import com.bootcamp.SpringEduManager.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para acceder a datos de Estudiante.
 * Proporciona métodos CRUD automáticos: save, findAll, findById, delete, etc.
 */
@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}
