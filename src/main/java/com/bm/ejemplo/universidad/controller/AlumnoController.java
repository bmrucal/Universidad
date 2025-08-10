// AlumnoController.java
package com.bm.ejemplo.universidad.controller;

import com.bm.ejemplo.universidad.model.Alumno;
import com.bm.ejemplo.universidad.service.AlumnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PostMapping
    public ResponseEntity<Alumno> crearAlumno(@RequestBody Alumno alumno) {
        return ResponseEntity.ok(alumnoService.crearAlumno(alumno));
    }

    @GetMapping
    public ResponseEntity<List<Alumno>> listarAlumnos() {
        return ResponseEntity.ok(alumnoService.listarAlumnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> obtenerAlumnoPorId(@PathVariable Long id) {
        return alumnoService.obtenerAlumnoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> actualizarAlumno(@PathVariable Long id, @RequestBody Alumno alumno) {
        return alumnoService.actualizarAlumno(id, alumno)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable Long id) {
        if (alumnoService.eliminarAlumno(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
