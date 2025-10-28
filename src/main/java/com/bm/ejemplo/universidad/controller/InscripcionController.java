package com.bm.ejemplo.universidad.controller;

import com.bm.ejemplo.universidad.model.Inscripcion;
import com.bm.ejemplo.universidad.service.InscripcionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @GetMapping
    public List<Inscripcion> listarTodas() {
        return inscripcionService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> obtenerPorId(@PathVariable Long id) {
        return inscripcionService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Inscripcion> crear(@RequestBody InscripcionRequest request) {
        try {
            Inscripcion inscripcion = inscripcionService.guardar(request.getAlumnoId(), request.getCursoId());
            return ResponseEntity.ok(inscripcion);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inscripcionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    public static class InscripcionRequest {
        private Long alumnoId;
        private Long cursoId;

        public Long getAlumnoId() {
            return alumnoId;
        }

        public void setAlumnoId(Long alumnoId) {
            this.alumnoId = alumnoId;
        }

        public Long getCursoId() {
            return cursoId;
        }

        public void setCursoId(Long cursoId) {
            this.cursoId = cursoId;
        }
    }
}

