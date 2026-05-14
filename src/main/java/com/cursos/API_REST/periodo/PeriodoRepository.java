package com.cursos.API_REST.periodo;

import aj.org.objectweb.asm.commons.Remapper;
import com.cursos.API_REST.Cursos.Cursos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodoRepository extends JpaRepository<Periodo, Long> {
    Page<Periodo> findAllByAtivoTrue(Pageable paginacao);
}
