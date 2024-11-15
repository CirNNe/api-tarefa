package com.higor.azevedo.apiTarefas.service.pessoa;

import com.higor.azevedo.apiTarefas.model.Pessoa;
import com.higor.azevedo.apiTarefas.repository.PessoaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GerenciadorPessoas {

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

    public Pessoa buscarPorId(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Pessoa não encontrada."));
    }

    public Pessoa buscarPorNome(String nome) throws Exception {
        return repository.findByNome(String.valueOf(nome)).orElseThrow(() -> new Exception("Pessoa não encontrada."));
    }

    public List<Pessoa> listarTodos() {
        return repository.findAll();
    }
}
