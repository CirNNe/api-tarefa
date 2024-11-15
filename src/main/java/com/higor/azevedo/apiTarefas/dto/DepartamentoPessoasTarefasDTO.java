package com.higor.azevedo.apiTarefas.dto;

public record DepartamentoPessoasTarefasDTO(String nomeDepartamento, long quantidadePessoas, long quantidadeTarefas) {
    public static DepartamentoPessoasTarefasDTO criaDepartamentoPessoasTarefasDTO(
            String nomeDepartamento, long quantidadePessoas, long quantidadeTarefas
    ) {
        return new DepartamentoPessoasTarefasDTO(
                nomeDepartamento,
                quantidadePessoas,
                quantidadeTarefas
        );
    }
}
