package com.higor.azevedo.apiTarefas.dto;


import com.higor.azevedo.apiTarefas.model.Pessoa;

public record PessoaHorasGastasDTO(String nome, Long idDepartamento, Long horasGastas) {

    public static PessoaHorasGastasDTO criaPessoaHorasGastasDTO(Pessoa pessoa, Long idDepartamento, Long horasGastas) {
        return new PessoaHorasGastasDTO(
                pessoa.getNome(),
                idDepartamento,
                horasGastas
        );
    }
}
