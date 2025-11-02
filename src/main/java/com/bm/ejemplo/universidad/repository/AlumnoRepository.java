package com.bm.ejemplo.universidad.repository;

import com.bm.ejemplo.universidad.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> { }