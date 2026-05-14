package com.cursos.API_REST.Cursos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosAtualizarProduto(

        @Size(min=3,max= 100)
        String nome,

        @NotNull
        Periodo periodo


) {

}
