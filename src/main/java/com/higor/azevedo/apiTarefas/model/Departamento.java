package com.higor.azevedo.apiTarefas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.higor.azevedo.apiTarefas.dto.DepartamentoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "departamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", unique = true, nullable = false)
    @JsonBackReference
    private String nome;

    @OneToMany(mappedBy = "departamento")
    private List<Pessoa> pessoas;

    @OneToMany(mappedBy = "departamento")
    private List<Tarefa> tarefas;

    public Departamento(DepartamentoDTO data) {
        this.nome = data.nome();
    }
}
