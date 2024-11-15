package com.higor.azevedo.apiTarefas.service.tarefa;

import com.higor.azevedo.apiTarefas.model.Tarefa;
import com.higor.azevedo.apiTarefas.repository.TarefaRepository;
import org.springframework.stereotype.Component;

@Component
public class GerenciadorTarefas {

    private final TarefaRepository repository;

    public GerenciadorTarefas(TarefaRepository repository) {
        this.repository = repository;
    }

    public void salvar(Tarefa tarefa) {
        repository.save(tarefa);
    }
}
