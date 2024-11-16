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
        Long idDepartamento,
        Long idPessoa
) {
    public static TarefaDTO criaTarefaDTO(Tarefa tarefa) {
        return criaTarefaDTO(tarefa, null);
    }

    public static TarefaDTO criaTarefaDTO(Tarefa tarefa, Long idPessoa) {
        return new TarefaDTO(
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getPrazo(),
                tarefa.getDuracao(),
                tarefa.isConcluido(),
                tarefa.getDepartamento().getId(),
                idPessoa
        );
    }
}
