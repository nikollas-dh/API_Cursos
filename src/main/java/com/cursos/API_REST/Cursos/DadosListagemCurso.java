package com.cursos.API_REST.Cursos;

import com.cursos.API_REST.periodo.Periodo;

public record DadosListagemCurso(
        long id,
        String nome,
        Periodo periodo
) {
    public DadosListagemCurso(Cursos curso){
        this(
                curso.getId(),
                curso.getNome(),
                curso.getPeriodo()
        );
    }
}
