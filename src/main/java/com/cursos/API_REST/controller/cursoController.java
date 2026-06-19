package com.cursos.API_REST.controller;

import com.cursos.API_REST.Cursos.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Cursos",description="Gerenciamento de cursos")
@OpenAPIDefinition(tags ={
        @Tag(name = "Criar Curso",description = "Criar"),
        @Tag(name = "Listar todos os Cursos",description = "Listar todos"),
        @Tag(name = "Listar Curso por ID",description = "Listar por ID"),
        @Tag(name = "Excluir Curso",description = "Excluir"),
        @Tag(name = "Atualizar Curso",description = "Atualizar")
})
@CrossOrigin(origins = "http://localhost:5173")
public class cursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    @Operation(summary = "Criar um novo curso")
    @Tag(name="Criar Curso", description = "Salva os dados do curso no BD")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Curso criado com sucesso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DadosCadastroCurso.class))
                    })
    })

    public void cadastrarCurso(@RequestBody @Valid DadosCadastroCurso dados){
        cursoRepository.save(new Cursos(dados));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus
    @Tag(name = "Excluir Curso")

//    public void deletarCurso(@PathVariable Long id){
    public ResponseEntity deletarCurso(@PathVariable Long id){
        var curso = cursoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        curso.excluirCurso();

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @ResponseStatus
    @Tag(name = "Listar todos os Cursos")

    public ResponseEntity<Page<DadosListagemCurso>> listarCursos(@PageableDefault(size = 10, sort ={"nome"}) @ParameterObject Pageable paginacao){
        var page = cursoRepository.findAllByAtivoTrue(paginacao)
                .map(DadosListagemCurso::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @ResponseStatus
    @Tag(name = "Listar Curso por ID")
    public ResponseEntity ListarCursoId(@PathVariable long id){
        var curso = cursoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }

    @PutMapping("/{id}")
    @Transactional
    @Tag(name = "Atualizar Curso")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DadosCadastroCurso.class))
                    }),
    })
    public ResponseEntity<DadosDetalhamentoCurso> atualizarCurso(@RequestBody @Valid DadosAtualizarProduto dados, @PathVariable long id) {
        var curso = cursoRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
        curso.editarCurso(dados);
        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }


}
