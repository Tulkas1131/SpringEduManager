package com.bootcamp.SpringEduManager.controller;

import com.bootcamp.SpringEduManager.model.Curso;
import com.bootcamp.SpringEduManager.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador MVC que maneja las vistas HTML para cursos.
 * Retorna páginas HTML renderizadas por Thymeleaf.
 * Requiere autenticación para crear/editar cursos.
 */
@Controller
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    /**
     * Lista todos los cursos.
     * GET /cursos
     */
    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("cursos", cursoService.findAll());
        return "cursos/list";
    }

    /**
     * Muestra el formulario para crear un nuevo curso (cualquier usuario autenticado).
     * La validación de ADMIN ocurre en POST guardarcurso
     * GET /cursos/nuevo
     */
    @GetMapping("/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("curso", new Curso());
        return "cursos/form";
    }

    /**
     * Guarda un nuevo curso desde el formulario (cualquier usuario autenticado).
     * La validación del contenido se hace en el formulario HTML via JavaScript
     * POST /cursos
     */
    @PostMapping
    public String guardarCurso(@ModelAttribute("curso") Curso curso) {
        cursoService.save(curso);
        return "redirect:/cursos";
    }

    /**
     * Obtiene un curso por ID para editarlo (cualquier usuario autenticado).
     * GET /cursos/{id}
     * La validación de ADMIN ocurre en el POST due to @PreAuthorize en actualizarCurso
     */
    @GetMapping("/{id}")
    public String obtenerCurso(@PathVariable Long id, Model model) {
        Curso curso = cursoService.findById(id).orElse(null);
        if (curso == null) {
            return "redirect:/cursos";
        }
        model.addAttribute("curso", curso);
        return "cursos/form";
    }

    /**
     * Actualiza un curso existente (cualquier usuario autenticado).
     * POST /cursos/{id}
     */
    @PostMapping("/{id}")
    public String actualizarCurso(@PathVariable Long id, @ModelAttribute("curso") Curso curso) {
        curso.setId(id);
        cursoService.save(curso);
        return "redirect:/cursos";
    }

    /**
     * Elimina un curso por ID (cualquier usuario autenticado).
     * GET /cursos/eliminar/{id}
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable Long id) {
        cursoService.deleteById(id);
        return "redirect:/cursos";
    }
}
