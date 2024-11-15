package com.higor.azevedo.apiTarefas.service.pessoa;

import com.higor.azevedo.apiTarefas.model.Pessoa;
import com.higor.azevedo.apiTarefas.repository.PessoaRepository;
import org.springframework.stereotype.Component;

@Component
public class GerenciadorPessoas {

    private final PessoaRepository repository;

    public GerenciadorPessoas(PessoaRepository repository) {
        this.repository = repository;
    }

    public void salvar(Pessoa pessoa) {
        repository.save(pessoa);
    }
}
