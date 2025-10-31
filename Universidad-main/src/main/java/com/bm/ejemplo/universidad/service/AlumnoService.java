package com.bm.ejemplo.universidad.service;

import com.bm.ejemplo.universidad.model.Alumno;
import com.bm.ejemplo.universidad.repository.AlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public List<Alumno> listarTodos() {
        return alumnoRepository.findAll();
    }

    public Optional<Alumno> buscarPorId(Long id) {
        return alumnoRepository.findById(id);
    }

    public Alumno guardar(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    public Alumno actualizar(Long id, Alumno datosAlumno) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        alumno.setNombre(datosAlumno.getNombre());
        alumno.setEmail(datosAlumno.getEmail());

        return alumnoRepository.save(alumno);
    }

    public void eliminar(Long id) {
        alumnoRepository.deleteById(id);
    }
}

