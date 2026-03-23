package com.bootcamp.SpringEduManager.controller;

import com.bootcamp.SpringEduManager.model.Estudiante;
import com.bootcamp.SpringEduManager.service.EstudianteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API REST para Estudiantes. Retorna datos en JSON.
 * Todos los endpoints requieren autenticación.
 */
@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteRestController {

    private final EstudianteService estudianteService;

    // Inyección de dependencias por constructor
    public EstudianteRestController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    // GET /api/estudiantes - Obtiene todos los estudiantes
    @GetMapping
    public List<Estudiante> listarTodos() {
        return estudianteService.findAll();
    }

    // GET /api/estudiantes/{id} - Obtiene un estudiante por ID
    @GetMapping("/{id}")
    public Estudiante obtenerPorId(@PathVariable Long id) {
        return estudianteService.findById(id).orElse(null);
    }

    /**
     * @PostMapping mapea peticiones HTTP POST, típicamente para crear nuevos estudiantes.
     * @RequestBody indica que el estudiante viene en el cuerpo (body) de la petición HTTP en formato JSON.
     */
    @PostMapping
    public Estudiante guardar(@RequestBody Estudiante estudiante) {
        return estudianteService.save(estudiante);
    }

    /**
     * @PutMapping("/{id}") mapea peticiones HTTP PUT, usadas para actualizar recursos existentes.
     * Recibe el ID por la URL y los nuevos datos en el cuerpo de la petición.
     */
    @PutMapping("/{id}")
    public Estudiante actualizar(@PathVariable Long id, @RequestBody Estudiante estudianteDetalles) {
        // Buscamos el estudiante existente en la base de datos
        Estudiante estudiante = estudianteService.findById(id).orElse(null);
        if (estudiante != null) {
            // Actualizamos los campos con los nuevos detalles
            estudiante.setNombre(estudianteDetalles.getNombre());
            estudiante.setEmail(estudianteDetalles.getEmail());
            // Guardamos los cambios
            return estudianteService.save(estudiante);
        }
        return null;
    }

    /**
     * @DeleteMapping("/{id}") mapea peticiones HTTP DELETE para borrar un recurso por su ID.
     */
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        estudianteService.deleteById(id);
    }
}
