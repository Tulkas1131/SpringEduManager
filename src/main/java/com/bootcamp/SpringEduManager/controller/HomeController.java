package com.bootcamp.SpringEduManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador que maneja las páginas públicas del sitio.
 */
@Controller
public class HomeController {

    // Página de inicio pública
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // Página de login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Dashboard para usuarios autenticados
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
