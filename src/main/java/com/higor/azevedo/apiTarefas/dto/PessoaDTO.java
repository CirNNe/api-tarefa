package com.higor.azevedo.apiTarefas.dto;

import java.util.List;

public record PessoaDTO(String nome, DepartamentoDTO departamento, List<TarefaDTO> tarefas) {
}
