package com.bm.ejemplo.universidad.controller;

import com.bm.ejemplo.universidad.model.Alumno;
import com.bm.ejemplo.universidad.service.AlumnoService;
import org.springframework.http.HttpStatus; // Importación necesaria
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    // Inyección de Dependencias
    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    // Listar todos los alumnos (200 OK)
    @GetMapping(produces = "application/json")
    public List<Alumno> getAll() {
        return alumnoService.listarTodos();
    }

    // Obtener alumno por id (200 OK o 404 Not Found)
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Alumno> getById(@PathVariable Long id) {
        return alumnoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear alumno (201 Created) - ¡CONSUMES ELIMINADO!
    @PostMapping(produces = "application/json") 
    public ResponseEntity<Alumno> create(@RequestBody Alumno alumno) {
        Alumno saved = alumnoService.guardar(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved); // Código 201
    }

    // Actualizar alumno (200 OK o 404 Not Found) - CONSUMES ELIMINADO Y LÓGICA SIMPLIFICADA
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Alumno> update(@PathVariable Long id, @RequestBody Alumno alumno) {
        // Lógica delegada al servicio (que lanzará ResourceNotFoundException/404)
        Alumno updated = alumnoService.actualizar(id, alumno);
        return ResponseEntity.ok(updated);
    }

    // Eliminar alumno (204 No Content o 404 Not Found)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alumnoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

