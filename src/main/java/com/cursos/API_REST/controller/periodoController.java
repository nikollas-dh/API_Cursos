package com.cursos.API_REST.controller;

import com.cursos.API_REST.periodo.Periodo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cursos/periodos")
public class periodoController {



    @GetMapping
    public Periodo[] listarTodos() {
        // Retorna ["Matutino", "Vespertino", "Noturno", "Integral"]
        return Periodo.values();
    }
}
