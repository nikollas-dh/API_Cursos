package com.cursos.API_REST.controller;

import com.cursos.API_REST.Cursos.*;
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
@RequestMapping("cursos")
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
    @ResponseStatus
    public ResponseEntity<Page<DadosListagemCurso>> listarCursos(@PageableDefault(size = 10, sort ={"nome"}) @ParameterObject Pageable paginacao){
        var page = cursoRepository.findAllByAtivoTrue(paginacao)
                .map(DadosListagemCurso::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @ResponseStatus
    public ResponseEntity ListarCursoId(@PathVariable long id){
        var curso = cursoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoCurso> atualizarCurso(@RequestBody @Valid DadosAtualizarProduto dados, @PathVariable long id) {
        var curso = cursoRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
        curso.editarCurso(dados);
        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }


}
