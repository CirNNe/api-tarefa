package com.higor.azevedo.apiTarefas.service.departamento;

import com.higor.azevedo.apiTarefas.model.Departamento;
import com.higor.azevedo.apiTarefas.repository.DepartamentoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GerenciadorDepartamento {

    public final String DEPARTAMENTO_NAO_ENCONTRADO_MSG = "Departamento não encontrado";

    private final DepartamentoRepository repository;

    public GerenciadorDepartamento(DepartamentoRepository repository) {
        this.repository = repository;
    }

    public Optional<Departamento> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public void salvar(Departamento departamento) {
        repository.save(departamento);
    }

    public List<Departamento> listarTodos() {
        return repository.findAll();
    }
}
