package com.higor.azevedo.apiTarefas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.higor.azevedo.apiTarefas.dto.TarefaDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tarefa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String descricao;

    private LocalDate prazo;

    private Long duracao;

    @Column(columnDefinition = "boolean default false")
    private boolean concluido;

    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    @JsonBackReference
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @JsonBackReference
    private Pessoa pessoa;

    public Tarefa(TarefaDTO data) {
        this.titulo = data.titulo();
        this.descricao = data.descricao();
        this.prazo = data.prazo();
        this.duracao = data.duracao();
        this.concluido = data.concluido();
    }
}
