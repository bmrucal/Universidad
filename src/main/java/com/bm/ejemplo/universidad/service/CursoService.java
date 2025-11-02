package com.bm.ejemplo.universidad.service;

import com.bm.ejemplo.universidad.exception.ResourceNotFoundException;
import com.bm.ejemplo.universidad.model.Curso;
import com.bm.ejemplo.universidad.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository repo;

    public CursoService(CursoRepository repo) { this.repo = repo; }

    public List<Curso> listarTodos() { return repo.findAll(); }
    public Optional<Curso> buscarPorId(Long id) { return repo.findById(id); }
    public Curso guardar(Curso curso) { return repo.save(curso); }

    public Curso actualizar(Long id, Curso datosCurso) {
        return repo.findById(id)
            .map(cursoExistente -> {
                cursoExistente.setNombre(datosCurso.getNombre());
                cursoExistente.setDescripcion(datosCurso.getDescripcion());
                return repo.save(cursoExistente);
            })
            .orElseThrow(() -> new ResourceNotFoundException("Curso con ID " + id + " no encontrado para actualizar."));
    }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Curso con ID " + id + " no encontrado para eliminar.");
        }
        repo.deleteById(id);
    }
}

