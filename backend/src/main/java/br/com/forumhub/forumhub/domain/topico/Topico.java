package br.com.forumhub.forumhub.domain.topico;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensagem;

    @Column(name = "data_criacao", nullable = false, insertable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private String curso;
}