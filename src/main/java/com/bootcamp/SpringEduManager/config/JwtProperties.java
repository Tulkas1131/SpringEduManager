package com.bootcamp.SpringEduManager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Clase de configuración para propiedades JWT.
 * Mapea las propiedades del archivo application.properties con prefix "jwt".
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    // Clave secreta para firmar los tokens
    private String secret = "SpringEduManagerSecretKey2026";

    // Tiempo de expiración en milisegundos
    private long expiration = 86400000;  // 24 horas por defecto

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }
}
