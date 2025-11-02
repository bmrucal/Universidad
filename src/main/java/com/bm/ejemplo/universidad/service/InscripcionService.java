package com.bm.ejemplo.universidad.service;

import com.bm.ejemplo.universidad.exception.ResourceNotFoundException;
import com.bm.ejemplo.universidad.model.Alumno;
import com.bm.ejemplo.universidad.model.Curso;
import com.bm.ejemplo.universidad.model.Inscripcion;
import com.bm.ejemplo.universidad.repository.AlumnoRepository;
import com.bm.ejemplo.universidad.repository.CursoRepository;
import com.bm.ejemplo.universidad.repository.InscripcionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionService {

    private final InscripcionRepository repo;
    private final AlumnoRepository alumnoRepo;
    private final CursoRepository cursoRepo;

    public InscripcionService(InscripcionRepository repo, AlumnoRepository alumnoRepo, CursoRepository cursoRepo) {
        this.repo = repo;
        this.alumnoRepo = alumnoRepo;
        this.cursoRepo = cursoRepo;
    }

    public List<Inscripcion> listarTodas() { return repo.findAll(); }
    public Optional<Inscripcion> buscarPorId(Long id) { return repo.findById(id); }

    public Inscripcion guardar(Long alumnoId, Long cursoId) {
        // Valida existencia con la nueva excepción 404
        Alumno alumno = alumnoRepo.findById(alumnoId)
                .orElseThrow(() -> new ResourceNotFoundException("Alumno con ID " + alumnoId + " no encontrado."));

        Curso curso = cursoRepo.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso con ID " + cursoId + " no encontrado."));

        return repo.save(new Inscripcion(alumno, curso));
    }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Inscripción con ID " + id + " no encontrada para eliminar.");
        }
        repo.deleteById(id);
    }
}
	


