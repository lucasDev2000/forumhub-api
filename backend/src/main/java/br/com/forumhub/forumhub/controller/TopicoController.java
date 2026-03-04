package br.com.forumhub.forumhub.controller;

import br.com.forumhub.forumhub.domain.topico.Topico;
import br.com.forumhub.forumhub.domain.topico.TopicoRepository;
import br.com.forumhub.forumhub.domain.topico.dto.DadosAtualizacaoTopico;
import br.com.forumhub.forumhub.domain.topico.dto.DadosCadastroTopico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository repository;

    public TopicoController(TopicoRepository repository) {
        this.repository = repository;
    }

    // LISTAGEM (GET /topicos)
    // - padrão: 10 primeiros, ordenados por dataCriacao ASC
    // - opcional: filtrar por curso
    // - opcional: filtrar por curso + ano
    // - paginação: ?page=0&size=10
    @GetMapping
    public Page<Topico> listar(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer ano,
            @PageableDefault(size = 10, sort = "dataCriacao", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        if (curso != null && ano != null) {
            LocalDateTime inicio = LocalDate.of(ano, 1, 1).atStartOfDay();
            LocalDateTime fim = LocalDate.of(ano + 1, 1, 1).atStartOfDay();
            return repository.findByCursoAndDataCriacaoBetween(curso, inicio, fim, pageable);
        }

        if (curso != null) {
            return repository.findByCurso(curso, pageable);
        }

        return repository.findAll(pageable);
    }

    // DETALHAMENTO (GET /topicos/{id})
    @GetMapping("/{id}")
    public Topico detalhar(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tópico não encontrado"
                ));
    }

    // CADASTRO (POST /topicos)
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public Topico cadastrar(@RequestBody @Valid DadosCadastroTopico dados) {

        if (repository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Tópico duplicado (mesmo título e mensagem)."
            );
        }

        Topico topico = new Topico();
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setAutor(dados.autor());
        topico.setCurso(dados.curso());
        topico.setEstado("ABERTO");

        Topico salvo = repository.save(topico);

        // Recarrega para retornar com dataCriacao vindo do BD
        return repository.findById(salvo.getId()).orElseThrow();
    }

    // ATUALIZAÇÃO (PUT /topicos/{id})
    @PutMapping("/{id}")
    @Transactional
    public Topico atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {

        Topico topico = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tópico não encontrado"
                ));

        // Mesma regra do cadastro: não permitir OUTRO tópico com mesmo titulo+mensagem
        if (repository.existsByTituloAndMensagemAndIdNot(dados.titulo(), dados.mensagem(), id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Tópico duplicado (mesmo título e mensagem)."
            );
        }

        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setAutor(dados.autor());
        topico.setCurso(dados.curso());

        Topico salvo = repository.save(topico);
        return repository.findById(salvo.getId()).orElseThrow();
    }

    // EXCLUSÃO (DELETE /topicos/{id})
    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico não encontrado");
        }

        repository.deleteById(id);
    }
}