package com.higor.azevedo.apiTarefas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.higor.azevedo.apiTarefas.dto.PessoaDTO;
import com.higor.azevedo.apiTarefas.dto.TarefaDTO;
import com.higor.azevedo.apiTarefas.repository.DepartamentoRepository;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "pessoa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "departamento_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Departamento departamento;

    @OneToMany(mappedBy = "pessoa")
    private List<Tarefa> tarefas;

    public Pessoa(PessoaDTO data) {
        this.nome = data.nome();
        this.departamento = new Departamento(data.departamento());
        for (TarefaDTO tarefaDTO : data.tarefas()) {
            Tarefa tarefa = new Tarefa(tarefaDTO);
            this.tarefas.add(tarefa);
        }
    }
}
