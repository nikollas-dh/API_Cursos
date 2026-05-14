package com.cursos.API_REST.Cursos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Cursos, Long> {
    Page<Cursos> findAllByAtivoTrue(Pageable paginacao);
    Optional<Cursos> findByIdAndAtivoTrue(Long id);
}
