package com.higor.azevedo.apiTarefas.repository;

import com.higor.azevedo.apiTarefas.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    Departamento findByNome(String nome);
}
