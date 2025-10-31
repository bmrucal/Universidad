package com.bm.ejemplo.universidad.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "curso-inscripciones")
    private Set<Inscripcion> inscripciones = new HashSet<>();

    // 🔹 Constructor vacío (requerido por JPA)
    public Curso() {}

    // 🔹 Constructor con parámetros (opcional, pero práctico)
    public Curso(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Set<Inscripcion> getInscripciones() { return inscripciones; }
    public void setInscripciones(Set<Inscripcion> inscripciones) { 
        this.inscripciones = inscripciones; 
    }

    // 🔹 Método para asociar inscripciones sin errores
    public void addInscripcion(Inscripcion inscripcion) {
        inscripciones.add(inscripcion);
        inscripcion.setCurso(this);
    }

    // 🔹 Método para eliminar inscripciones
    public void removeInscripcion(Inscripcion inscripcion) {
        inscripciones.remove(inscripcion);
        inscripcion.setCurso(null);
    }
}

