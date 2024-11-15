package com.higor.azevedo.apiTarefas.service.pessoa;

import com.higor.azevedo.apiTarefas.dto.PessoaDTO;
import com.higor.azevedo.apiTarefas.model.Departamento;
import com.higor.azevedo.apiTarefas.model.Pessoa;
import com.higor.azevedo.apiTarefas.service.departamento.GerenciadorDepartamento;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    private final GerenciadorDepartamento gerenciadorDepartamento;
    private final GerenciadorPessoas gerenciadorPessoas;

    public PessoaService(
            GerenciadorDepartamento gerenciadorDepartamento,
            GerenciadorPessoas gerenciadorPessoas
    ) {
        this.gerenciadorDepartamento = gerenciadorDepartamento;
        this.gerenciadorPessoas = gerenciadorPessoas;
    }

    public void salvar(PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.nome());
        Departamento departamento = gerenciadorDepartamento.buscarPorNome(pessoaDTO.departamento());
        pessoa.setDepartamento(departamento);

        gerenciadorPessoas.salvar(pessoa);
    }
}
