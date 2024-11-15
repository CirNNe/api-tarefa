package com.higor.azevedo.apiTarefas.dto;

import java.time.LocalDate;

public record TarefaDTO(
        String titulo,
        String descricao,
        LocalDate prazo,
        Long duracao,
        boolean concluido,
        String departamento,
        String pessoa
) {
}
