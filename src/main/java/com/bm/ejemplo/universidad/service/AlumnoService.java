package com.bm.ejemplo.universidad.service;

import com.bm.ejemplo.universidad.exception.ResourceNotFoundException; // ¡Importación esencial!
import com.bm.ejemplo.universidad.model.Alumno;
import com.bm.ejemplo.universidad.repository.AlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    private final AlumnoRepository repo;

    public AlumnoService(AlumnoRepository repo) {
        this.repo = repo;
    }

    public List<Alumno> listarTodos() { return repo.findAll(); }
    public Optional<Alumno> buscarPorId(Long id) { return repo.findById(id); }
    public Alumno guardar(Alumno alumno) { return repo.save(alumno); }
    
    // Método que el Controller llama en la operación PUT
    public Alumno actualizar(Long id, Alumno datosAlumno) {
        return repo.findById(id)
            .map(alumnoExistente -> {
                // Lógica de actualización (evita nulos si es necesario, aquí solo actualizamos nombre/email)
                alumnoExistente.setNombre(datosAlumno.getNombre());
                alumnoExistente.setEmail(datosAlumno.getEmail());
                
                return repo.save(alumnoExistente);
            })
            // Lanza 404 Not Found si el ID no existe
            .orElseThrow(() -> new ResourceNotFoundException("Alumno con ID " + id + " no encontrado para actualizar."));
    }
    
    public void eliminar(Long id) { 
        if (!repo.existsById(id)) {
            // Lanza 404 Not Found si el ID no existe
            throw new ResourceNotFoundException("Alumno con ID " + id + " no encontrado para eliminar.");
        }
        repo.deleteById(id); 
    }
}

