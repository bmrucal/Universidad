// AlumnoService.java
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

    public Alumno crearAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    public List<Alumno> listarAlumnos() {
        return alumnoRepository.findAll();
    }

    public Optional<Alumno> obtenerAlumnoPorId(Long id) {
        return alumnoRepository.findById(id);
    }

    public Optional<Alumno> actualizarAlumno(Long id, Alumno alumnoActualizado) {
        return alumnoRepository.findById(id).map(alumno -> {
            alumno.setNombre(alumnoActualizado.getNombre());
            alumno.setApellido(alumnoActualizado.getApellido());
            alumno.setEmail(alumnoActualizado.getEmail());
            return alumnoRepository.save(alumno);
        });
    }

    public boolean eliminarAlumno(Long id) {
        if (alumnoRepository.existsById(id)) {
            alumnoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
