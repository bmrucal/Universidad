package com.bm.ejemplo.universidad.repository;

import com.bm.ejemplo.universidad.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> { }