package com.cursos.API_REST.Cursos;

import com.cursos.API_REST.periodo.Periodo;

public record DadosDetalhamentoCurso(
        long id,
        String nome,
        Periodo periodo
) {
    public DadosDetalhamentoCurso(Cursos curso){
        this(
                curso.getId(),
                curso.getNome(),
                curso.getPeriodo()
        );
    }
}
