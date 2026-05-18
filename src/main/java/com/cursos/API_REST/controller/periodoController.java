package com.cursos.API_REST.controller;

import com.cursos.API_REST.periodo.Periodo;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cursos/periodos")

@Tag(name = "Periodos", description = "Gerenciamento dos periodos")
@OpenAPIDefinition(tags ={
        @Tag(name = "Listar todos os períodos",description = "Listar todos"),
})

public class periodoController {
    @GetMapping
//    @Tag(name = "Listar todos os períodos")
    public Periodo[] listarTodos() {
        return Periodo.values();
    }
}
