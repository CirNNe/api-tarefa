package com.higor.azevedo.apiTarefas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

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
}
