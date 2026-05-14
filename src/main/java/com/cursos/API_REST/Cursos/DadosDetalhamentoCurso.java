package com.cursos.API_REST.Cursos;

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
