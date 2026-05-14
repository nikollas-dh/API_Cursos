package com.cursos.API_REST.controller;

import com.cursos.API_REST.Cursos.CursoRepository;
import com.cursos.API_REST.Cursos.Cursos;
import com.cursos.API_REST.Cursos.DadosCadastroCurso;
import com.cursos.API_REST.Cursos.DadosListagemCurso;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus
//    public void deletarCurso(@PathVariable Long id){
    public ResponseEntity deletarCurso(@PathVariable Long id){
        var curso = cursoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        curso.excluirCurso();

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCurso>> listarCursos(@PageableDefault(size = 10, sort ={"nome"}) @ParameterObject Pageable paginacao){
        var page = cursoRepository.findAllByAtivoTrue(paginacao)
                .map(DadosListagemCurso::new);

        return ResponseEntity.ok(page);
    }
}
