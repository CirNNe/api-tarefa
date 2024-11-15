package com.higor.azevedo.apiTarefas.service.tarefa;

import com.higor.azevedo.apiTarefas.dto.TarefaDTO;
import com.higor.azevedo.apiTarefas.model.Departamento;
import com.higor.azevedo.apiTarefas.model.Pessoa;
import com.higor.azevedo.apiTarefas.model.Tarefa;
import com.higor.azevedo.apiTarefas.service.departamento.GerenciadorDepartamento;
import com.higor.azevedo.apiTarefas.service.pessoa.GerenciadorPessoas;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {

    private final GerenciadorDepartamento gerenciadorDepartamento;

    private final GerenciadorTarefas gerenciadorTarefas;

    private final GerenciadorPessoas gerenciadorPessoas;

    public TarefaService(
            GerenciadorDepartamento gerenciadorDepartamento,
            GerenciadorTarefas gerenciadorTarefas,
            GerenciadorPessoas gerenciadorPessoas
    ) {
        this.gerenciadorDepartamento = gerenciadorDepartamento;
        this.gerenciadorTarefas = gerenciadorTarefas;
        this.gerenciadorPessoas = gerenciadorPessoas;
    }

    public TarefaDTO salvar(TarefaDTO tarefaDTO) throws Exception {
        Tarefa tarefa = new Tarefa(tarefaDTO);

        Departamento departamento = gerenciadorDepartamento.buscarPorNome(tarefaDTO.departamento().nome());
        tarefa.setDepartamento(departamento);

        Pessoa pessoa = gerenciadorPessoas.buscarPorNome(tarefaDTO.nomePessoa());
        tarefa.setPessoa(pessoa);

        gerenciadorTarefas.salvar(tarefa);
        return tarefaDTO;
    }
}
