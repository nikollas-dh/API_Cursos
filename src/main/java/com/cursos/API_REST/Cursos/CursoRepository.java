package com.cursos.API_REST.Cursos;

import org.hibernate.query.Page;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Cursos, Long> {
    Optional<Cursos> findByIdAndAtivoTrue(Long id);
}
