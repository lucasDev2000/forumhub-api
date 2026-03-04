package br.com.forumhub.forumhub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensagem(String titulo, String mensagem);

    boolean existsByTituloAndMensagemAndIdNot(String titulo, String mensagem, Long id);

    Page<Topico> findByCurso(String curso, Pageable pageable);

    Page<Topico> findByCursoAndDataCriacaoBetween(String curso, LocalDateTime inicio, LocalDateTime fim, Pageable pageable);
}