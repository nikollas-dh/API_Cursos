package com.cursos.API_REST.controller;

import com.cursos.API_REST.Cursos.CursoRepository;
import com.cursos.API_REST.Cursos.Cursos;
import com.cursos.API_REST.Cursos.DadosCadastroCurso;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class cursoController {

@Autowired
private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public void cadastrarCurso(@RequestBody @Valid DadosCadastroCurso dados){
        cursoRepository.save(new Cursos(dados));
    }
}
