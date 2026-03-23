package com.bootcamp.SpringEduManager.repository;

import com.bootcamp.SpringEduManager.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para acceder a datos de Curso.
 * Proporciona métodos CRUD automáticos: save, findAll, findById, delete, etc.
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
