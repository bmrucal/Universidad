// CursoService.java
package com.bm.ejemplo.universidad.service;

import com.bm.ejemplo.universidad.model.Curso;
import com.bm.ejemplo.universidad.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso crearCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public Optional<Curso> actualizarCurso(Long id, Curso cursoActualizado) {
        return cursoRepository.findById(id).map(curso -> {
            curso.setNombreCurso(cursoActualizado.getNombreCurso());
            curso.setDescripcion(cursoActualizado.getDescripcion());
            return cursoRepository.save(curso);
        });
    }

    public boolean eliminarCurso(Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
