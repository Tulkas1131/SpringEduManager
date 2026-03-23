package com.bootcamp.SpringEduManager.controller;

import com.bootcamp.SpringEduManager.model.Estudiante;
import com.bootcamp.SpringEduManager.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador MVC que maneja las vistas HTML para estudiantes.
 * Retorna páginas HTML renderizadas por Thymeleaf.
 */
@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    /**
     * Lista todos los estudiantes.
     * GET /estudiantes
     */
    @GetMapping
    public String listarEstudiantes(Model model) {
        model.addAttribute("estudiantes", estudianteService.findAll());
        return "estudiantes/list";
    }

    /**
     * Muestra el formulario para crear un nuevo estudiante.
     * GET /estudiantes/nuevo
     */
    @GetMapping("/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "estudiantes/form";
    }

    /**
     * Guarda un nuevo estudiante desde el formulario.
     * POST /estudiantes
     */
    @PostMapping
    public String guardarEstudiante(@ModelAttribute("estudiante") Estudiante estudiante) {
        estudianteService.save(estudiante);
        return "redirect:/estudiantes";
    }

    /**
     * Obtiene un estudiante por ID para editarlo.
     * GET /estudiantes/{id}
     */
    @GetMapping("/{id}")
    public String obtenerEstudiante(@PathVariable Long id, Model model) {
        Estudiante estudiante = estudianteService.findById(id).orElse(null);
        if (estudiante == null) {
            return "redirect:/estudiantes";
        }
        model.addAttribute("estudiante", estudiante);
        return "estudiantes/form";
    }

    /**
     * Actualiza un estudiante existente.
     * PUT /estudiantes/{id} (via POST con hidden field)
     */
    @PostMapping("/{id}")
    public String actualizarEstudiante(@PathVariable Long id, @ModelAttribute("estudiante") Estudiante estudiante) {
        estudiante.setId(id);
        estudianteService.save(estudiante);
        return "redirect:/estudiantes";
    }

    /**
     * Elimina un estudiante por ID.
     * GET /estudiantes/eliminar/{id}
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {
        estudianteService.deleteById(id);
        return "redirect:/estudiantes";
    }
}
