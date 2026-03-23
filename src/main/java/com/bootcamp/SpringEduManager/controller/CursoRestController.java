package com.bootcamp.SpringEduManager.controller;

import com.bootcamp.SpringEduManager.model.Curso;
import com.bootcamp.SpringEduManager.service.CursoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API REST para Cursos. Retorna datos en JSON.
 * Todos los endpoints requieren autenticación.
 */
@RestController
@RequestMapping("/api/cursos")
public class CursoRestController {

    private final CursoService cursoService;

    // Inyección de dependencias por constructor
    public CursoRestController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    // GET /api/cursos - Obtiene todos los cursos
    @GetMapping
    public List<Curso> listarTodos() {
        return cursoService.findAll();
    }

    // GET /api/cursos/{id} - Obtiene un curso por ID
    @GetMapping("/{id}")
    public Curso obtenerPorId(@PathVariable Long id) {
        return cursoService.findById(id).orElse(null);
    }

    /**
     * @PostMapping mapea peticiones HTTP POST, típicamente para crear nuevos recursos.
     * @RequestBody indica que el curso viene en el cuerpo (body) de la petición HTTP en formato JSON.
     */
    @PostMapping
    public Curso guardar(@RequestBody Curso curso) {
        return cursoService.save(curso);
    }

    /**
     * @PutMapping("/{id}") mapea peticiones HTTP PUT, usadas para actualizar recursos existentes.
     * Recibe el ID por la URL y los nuevos datos en el cuerpo de la petición.
     */
    @PutMapping("/{id}")
    public Curso actualizar(@PathVariable Long id, @RequestBody Curso cursoDetalles) {
        // Buscamos el curso existente en la base de datos
        Curso curso = cursoService.findById(id).orElse(null);
        if (curso != null) {
            // Actualizamos los campos con los nuevos detalles
            curso.setNombre(cursoDetalles.getNombre());
            curso.setDescripcion(cursoDetalles.getDescripcion());
            // Guardamos los cambios
            return cursoService.save(curso);
        }
        return null;
    }

    /**
     * @DeleteMapping("/{id}") mapea peticiones HTTP DELETE para borrar un recurso por su ID.
     */
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        cursoService.deleteById(id);
    }
}
