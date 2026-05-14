package com.cursos.API_REST.Cursos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Table(name = "cursos")
@Entity(name = "cursos")

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Cursos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Periodo periodo;
    private boolean ativo;


    public Cursos(DadosCadastroCurso dados) {
        this.nome = dados.nome();
        this.periodo = dados.periodo();
        this.ativo = true;
    }
    public void excluirCurso() {
        this.ativo = false;
    }
}
