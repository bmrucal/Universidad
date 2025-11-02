// src/main/java/com/bm/ejemplo/universidad/controller/HomeController.java
package com.bm.ejemplo.universidad.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home() {
        return """
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Universidad API</title>
                    <style>
                        body { font-family: Arial, sans-serif; margin: 40px; background: #f4f4f9; color: #333; }
                        h1 { color: #2c3e50; }
                        .container { max-width: 800px; margin: auto; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
                        ul { list-style: none; padding: 0; }
                        li { margin: 10px 0; }
                        a { color: #3498db; text-decoration: none; font-weight: bold; }
                        a:hover { text-decoration: underline; }
                        .badge { background: #3498db; color: white; padding: 3px 8px; border-radius: 4px; font-size: 0.8em; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>Universidad API</h1>
                        <p><strong>Estado:</strong> <span style="color:green">API REST en funcionamiento</span></p>
                        <p>Base de datos: H2 (en memoria) - <a href="/h2-console" target="_blank">Acceder a H2 Console</a></p>
                        
                        <h2>Endpoints disponibles:</h2>
                        <ul>
                            <li><a href="/api/alumnos" target="_blank">/api/alumnos</a> → <span class="badge">GET</span> <span class="badge">POST</span></li>
                            <li><a href="/api/cursos" target="_blank">/api/cursos</a> → <span class="badge">GET</span> <span class="badge">POST</span></li>
                            <li><a href="/api/inscripciones" target="_blank">/api/inscripciones</a> → <span class="badge">GET</span> <span class="badge">POST</span></li>
                        </ul>
                        
                        <hr>
                        <small>Desarrollado con Spring Boot 3.5.4 | Deploy en Railway</small>
                    </div>
                </body>
                </html>
                """;
    }
}