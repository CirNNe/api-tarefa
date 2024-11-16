package com.higor.azevedo.apiTarefas.service.pessoa;

import com.higor.azevedo.apiTarefas.model.Pessoa;
import com.higor.azevedo.apiTarefas.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GerenciadorPessoas {

    final String PESSOA_NAO_ENCONTRADA_MSG = "Pessoa não encontrada.";

    private final PessoaRepository repository;

    public GerenciadorPessoas(PessoaRepository repository) {
        this.repository = repository;
    }

    public void salvar(Pessoa pessoa) {
        repository.save(pessoa);
    }

    public void deletar(Pessoa pessoa) {
        repository.delete(pessoa);
    }

    public Pessoa buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(PESSOA_NAO_ENCONTRADA_MSG));
    }

    public Pessoa buscarPorNome(String nome) {
        return repository.findByNome(String.valueOf(nome)).orElseThrow(() -> new EntityNotFoundException(PESSOA_NAO_ENCONTRADA_MSG));
    }

    public List<Pessoa> listarTodos() {
        return repository.findAll();
    }
}
