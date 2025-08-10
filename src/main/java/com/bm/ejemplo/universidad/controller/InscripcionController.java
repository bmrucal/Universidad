package com.bm.ejemplo.universidad.controller;

import com.bm.ejemplo.universidad.model.Inscripcion;
import com.bm.ejemplo.universidad.service.InscripcionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @PostMapping
    public ResponseEntity<Inscripcion> crearInscripcion(@RequestBody Inscripcion inscripcion) {
        return ResponseEntity.ok(inscripcionService.crearInscripcion(inscripcion));
    }

    @GetMapping
    public ResponseEntity<List<Inscripcion>> listarInscripciones() {
        return ResponseEntity.ok(inscripcionService.listarInscripciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> obtenerInscripcionPorId(@PathVariable Long id) {
        return inscripcionService.obtenerInscripcionPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inscripcion> actualizarInscripcion(@PathVariable Long id, @RequestBody Inscripcion inscripcion) {
        return inscripcionService.actualizarInscripcion(id, inscripcion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInscripcion(@PathVariable Long id) {
        if (inscripcionService.eliminarInscripcion(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
