package com.higor.azevedo.apiTarefas.repository;

import com.higor.azevedo.apiTarefas.model.Pessoa;
import com.higor.azevedo.apiTarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    Optional<List<Tarefa>> findTop3ByPessoaIsNullOrderByPrazoAsc();
    Optional<List<Tarefa>> findByPessoaAndPrazoBetween(Pessoa pessoa, LocalDate inicio, LocalDate fim);
}
