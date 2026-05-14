package com.cursos.API_REST.controller;

import com.cursos.API_REST.Cursos.DadosListagemCurso;
import com.cursos.API_REST.periodo.Periodo;
import com.cursos.API_REST.periodo.PeriodoRepository;
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

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("cursos/periodos")
public class periodoController {

    @Autowired
    private PeriodoRepository periodoRepository;

    @GetMapping
    public ResponseEntity<List<String>> listarPeriodo() {
        var periodos = Arrays.stream(Periodo.values())
                .map(Enum::name)
                .toList();

        return ResponseEntity.ok(periodos); // Retorna 200 OK com a lista
    }
}
