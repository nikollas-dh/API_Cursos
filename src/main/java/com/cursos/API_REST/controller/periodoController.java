package com.cursos.API_REST.controller;

import com.cursos.API_REST.Cursos.CursoRepository;
import com.cursos.API_REST.Cursos.DadosListagemCurso;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cursos/periodos")
public class periodoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    @ResponseStatus
    public ResponseEntity<Page<DadosListagemCurso>> listarPeriodo(@PageableDefault(size = 10, sort ={"nome"}) @ParameterObject Pageable paginacao) {
        var page = cursoRepository.findAllByAtivoTrue(paginacao)
                .map(DadosListagemCurso::new);

        return ResponseEntity.ok(page);


    }
}
