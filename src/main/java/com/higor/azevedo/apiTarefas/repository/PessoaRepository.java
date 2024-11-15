package com.higor.azevedo.apiTarefas.repository;

import com.higor.azevedo.apiTarefas.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
