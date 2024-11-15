package com.higor.azevedo.apiTarefas.service.departamento;

import com.higor.azevedo.apiTarefas.dto.DepartamentoPessoasTarefasDTO;
import com.higor.azevedo.apiTarefas.model.Departamento;
import com.higor.azevedo.apiTarefas.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartamentoService {

    private final DepartamentoRepository repository;

    public DepartamentoService(DepartamentoRepository repository) {
        this.repository = repository;
    }

    public List<DepartamentoPessoasTarefasDTO> listarDepartamentos() {
        List<Departamento> departamentos = repository.findAll();
        List<DepartamentoPessoasTarefasDTO> departamentoPessoasTarefasDTOS = new ArrayList<>();

        for (Departamento departamento : departamentos) {
            DepartamentoPessoasTarefasDTO dto = DepartamentoPessoasTarefasDTO.criaDepartamentoPessoasTarefasDTO(
                    departamento.getNome(),
                    departamento.getPessoas().size(),
                    departamento.getTarefas().size()
            );
            departamentoPessoasTarefasDTOS.add(dto);
        }
        return departamentoPessoasTarefasDTOS;
    }
}
