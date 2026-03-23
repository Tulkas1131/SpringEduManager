package com.bootcamp.SpringEduManager.controller;

import com.bootcamp.SpringEduManager.dto.AuthRequest;
import com.bootcamp.SpringEduManager.dto.AuthResponse;
import com.bootcamp.SpringEduManager.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para autenticación con JWT.
 * Permite a los usuarios obtener un token JWT para acceder a los endpoints protegidos.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    // Inyección de dependencias por constructor
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    /**
     * Endpoint de login.
     * POST /api/auth/login
     * Recibe credenciales y devuelve un token JWT si son válidas.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            // Valida las credenciales contra el sistema de seguridad
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );

            // Obtiene el rol del usuario autenticado
            // Si tiene ADMIN, retorna ADMIN; sino retorna USER
            String role = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .map(auth -> auth.replace("ROLE_", ""))  // Remueve prefijo "ROLE_"
                    .filter(auth -> auth.equals("ADMIN"))
                    .findFirst()
                    .orElse("USER");

            // Genera el token JWT
            String token = tokenProvider.generateToken(authRequest.getUsername(), role);

            // Retorna el token y información del usuario
            return ResponseEntity.ok(
                    new AuthResponse(
                            token,
                            authRequest.getUsername(),
                            role,
                            "Autenticación exitosa"
                    )
            );
        } catch (AuthenticationException e) {
            // Si las credenciales son inválidas
            return ResponseEntity.badRequest()
                    .body(new AuthResponse(
                            null,
                            null,
                            null,
                            "Credenciales inválidas"
                    ));
        }
    }

    /**
     * Endpoint de prueba para verificar que el token es válido.
     * GET /api/auth/verify
     * Requiere encabezado Authorization con el token.
     */
    @GetMapping("/verify")
    public ResponseEntity<?> verify() {
        return ResponseEntity.ok(new AuthResponse(
                null,
                null,
                null,
                "Token válido"
        ));
    }
}
