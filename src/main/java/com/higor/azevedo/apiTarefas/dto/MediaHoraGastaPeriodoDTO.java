package com.higor.azevedo.apiTarefas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record MediaHoraGastaPeriodoDTO(
        String nome,
        @JsonFormat(pattern = "dd/MM/yyyy") LocalDate inicio,
        @JsonFormat(pattern = "dd/MM/yyyy") LocalDate fim
) {
}
