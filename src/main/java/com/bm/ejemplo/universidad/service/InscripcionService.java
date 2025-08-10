package com.bm.ejemplo.universidad.service;

import com.bm.ejemplo.universidad.model.Inscripcion;
import com.bm.ejemplo.universidad.repository.InscripcionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;

    public InscripcionService(InscripcionRepository inscripcionRepository) {
        this.inscripcionRepository = inscripcionRepository;
    }

    public Inscripcion crearInscripcion(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public List<Inscripcion> listarInscripciones() {
        return inscripcionRepository.findAll();
    }

    public Optional<Inscripcion> obtenerInscripcionPorId(Long id) {
        return inscripcionRepository.findById(id);
    }

    public Optional<Inscripcion> actualizarInscripcion(Long id, Inscripcion inscripcionActualizado) {
        return inscripcionRepository.findById(id).map(inscripcion -> {
            inscripcion.setAlumno(inscripcionActualizado.getAlumno());
            inscripcion.setCurso(inscripcionActualizado.getCurso());
            inscripcion.setEstado(inscripcionActualizado.getEstado());
            return inscripcionRepository.save(inscripcion);
        });
    }

    public boolean eliminarInscripcion(Long id) {
        if (inscripcionRepository.existsById(id)) {
            inscripcionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
