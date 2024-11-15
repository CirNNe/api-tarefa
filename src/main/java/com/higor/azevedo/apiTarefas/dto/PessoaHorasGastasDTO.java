package com.higor.azevedo.apiTarefas.dto;


import com.higor.azevedo.apiTarefas.model.Pessoa;

public record PessoaHorasGastasDTO(String nome, DepartamentoDTO departamento, Long horasGastas) {

    public static PessoaHorasGastasDTO criaPessoaHorasGastasDTO(Pessoa pessoa, Long horasGastas) {
        DepartamentoDTO departamentoDTO = new DepartamentoDTO(pessoa.getDepartamento().getNome());
        return new PessoaHorasGastasDTO(
                pessoa.getNome(),
                departamentoDTO,
                horasGastas
        );
    }
}
