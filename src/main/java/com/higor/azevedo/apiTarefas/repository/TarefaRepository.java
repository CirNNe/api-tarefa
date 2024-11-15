package com.higor.azevedo.apiTarefas.repository;

import com.higor.azevedo.apiTarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    Optional<List<Tarefa>> findTop3ByPessoaIsNullOrderByPrazoAsc();
}
