package com.higor.azevedo.apiTarefas.dto;

import com.higor.azevedo.apiTarefas.model.Departamento;

public record DepartamentoDTO(String nome) {
    public static DepartamentoDTO criaDepartamentoDTO(Departamento departamento) {
        return new DepartamentoDTO(
                departamento.getNome()
        );
    }
}
