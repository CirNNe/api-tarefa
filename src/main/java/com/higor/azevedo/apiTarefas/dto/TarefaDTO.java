package com.higor.azevedo.apiTarefas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.higor.azevedo.apiTarefas.model.Tarefa;

import java.time.LocalDate;

public record TarefaDTO(
        String titulo,
        String descricao,
        @JsonFormat(pattern = "dd/MM/yyyy") LocalDate prazo,
        Long duracao,
        boolean concluido,
        DepartamentoDTO departamento,
        String nomePessoa
) {
    public static TarefaDTO criaTarefaDTO(Tarefa tarefa, String nomePessoa) {
        DepartamentoDTO departamentoDTO = new DepartamentoDTO(tarefa.getDepartamento().getNome());
        return new TarefaDTO(
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getPrazo(),
                tarefa.getDuracao(),
                tarefa.isConcluido(),
                departamentoDTO,
                nomePessoa
        );
    }
}
