package com.higor.azevedo.apiTarefas.repository;

import com.higor.azevedo.apiTarefas.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByNome(String nome);
}
