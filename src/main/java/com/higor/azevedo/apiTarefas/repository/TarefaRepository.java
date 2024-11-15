package com.higor.azevedo.apiTarefas.repository;

import com.higor.azevedo.apiTarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
