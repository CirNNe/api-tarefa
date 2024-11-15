package com.higor.azevedo.apiTarefas.service.departamento;

import com.higor.azevedo.apiTarefas.model.Departamento;
import com.higor.azevedo.apiTarefas.repository.DepartamentoRepository;
import org.springframework.stereotype.Component;

@Component
public class GerenciadorDepartamento {

    private final DepartamentoRepository repository;

    public GerenciadorDepartamento(DepartamentoRepository repository) {
        this.repository = repository;
    }

    public Departamento buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }
}
