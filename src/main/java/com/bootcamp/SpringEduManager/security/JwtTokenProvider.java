package com.bootcamp.SpringEduManager.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bootcamp.SpringEduManager.config.JwtProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Proveedor de tokens JWT.
 * Genera y valida tokens para autenticación sin sesión.
 */
@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /**
     * Genera un token JWT para un usuario.
     * @param username nombre del usuario
     * @param role rol del usuario
     * @return token JWT
     */
    public String generateToken(String username, String role) {
        long now = System.currentTimeMillis();
        long expiryTime = now + jwtProperties.getExpiration();

        return JWT.create()
                .withSubject(username)
                .withClaim("role", role)  // Incluye el rol en el token
                .withIssuedAt(new Date(now))
                .withExpiresAt(new Date(expiryTime))
                .sign(Algorithm.HMAC256(jwtProperties.getSecret()));
    }

    /**
     * Valida un token JWT.
     * @param token token a validar
     * @return true si es válido, false si no
     */
    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(jwtProperties.getSecret()))
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    /**
     * Extrae el nombre de usuario del token.
     * @param token token JWT
     * @return nombre de usuario
     */
    public String getUsernameFromToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(jwtProperties.getSecret()))
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    /**
     * Extrae el rol del token.
     * @param token token JWT
     * @return rol del usuario
     */
    public String getRoleFromToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(jwtProperties.getSecret()))
                    .build()
                    .verify(token)
                    .getClaim("role")
                    .asString();
        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
