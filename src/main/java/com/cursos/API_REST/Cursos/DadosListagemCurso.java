package com.cursos.API_REST.Cursos;

public record DadosListagemCurso(
        String nome,
        Periodo periodo
) {
    public DadosListagemCurso(Cursos curso){
        this(
                curso.getNome(),
                curso.getPeriodo()
        );
    }
}
