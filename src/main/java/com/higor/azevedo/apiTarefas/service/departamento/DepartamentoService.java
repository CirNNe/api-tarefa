package com.higor.azevedo.apiTarefas.service.departamento;

import com.higor.azevedo.apiTarefas.dto.DepartamentoDTO;
import com.higor.azevedo.apiTarefas.dto.DepartamentoPessoasTarefasDTO;
import com.higor.azevedo.apiTarefas.model.Departamento;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartamentoService {

    private final GerenciadorDepartamento gerenciadorDepartamento;

    public DepartamentoService(GerenciadorDepartamento gerenciadorDepartamento) {
        this.gerenciadorDepartamento = gerenciadorDepartamento;
    }

    public DepartamentoDTO salvar(DepartamentoDTO departamentoDTO) {
        Departamento departamento = new Departamento();
        departamento.setNome(departamentoDTO.nome());
        gerenciadorDepartamento.salvar(departamento);
        return departamentoDTO;
    }

    public List<DepartamentoPessoasTarefasDTO> listarDepartamentos() {
        List<Departamento> departamentos = gerenciadorDepartamento.listarTodos();
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
