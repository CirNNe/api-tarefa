package com.higor.azevedo.apiTarefas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(nullable = false)
    private LocalDate prazo;

    private Long duracao;

    @Column(columnDefinition = "boolean default false")
    private boolean concluido;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    @JsonBackReference
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @JsonBackReference
    private Pessoa pessoa;
}
