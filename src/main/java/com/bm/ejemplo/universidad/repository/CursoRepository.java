package com.bm.ejemplo.universidad.repository;

import com.bm.ejemplo.universidad.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> { }