package com.higor.azevedo.apiTarefas.service.tarefa;

import com.higor.azevedo.apiTarefas.dto.DepartamentoDTO;
import com.higor.azevedo.apiTarefas.dto.TarefaDTO;
import com.higor.azevedo.apiTarefas.model.Departamento;
import com.higor.azevedo.apiTarefas.model.Pessoa;
import com.higor.azevedo.apiTarefas.model.Tarefa;
import com.higor.azevedo.apiTarefas.service.departamento.GerenciadorDepartamento;
import com.higor.azevedo.apiTarefas.service.pessoa.GerenciadorPessoas;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public TarefaDTO salvar(TarefaDTO tarefaDTO) {
        Tarefa tarefa = new Tarefa(tarefaDTO);

        Departamento departamento = gerenciadorDepartamento.buscarPorId(tarefaDTO.idDepartamento());
        tarefa.setDepartamento(departamento);

        if (tarefaDTO.idPessoa() != null) {
            Pessoa pessoa = gerenciadorPessoas.buscarPorId(tarefaDTO.idPessoa());
            tarefa.setPessoa(pessoa);
        }
        gerenciadorTarefas.salvar(tarefa);
        return tarefaDTO;
    }

    public TarefaDTO alocarPessoa(Long idTarefa, Long idPessoa) {
        Pessoa pessoa = gerenciadorPessoas.buscarPorId(idPessoa);
        Tarefa tarefa = gerenciadorTarefas.buscarPorId(idTarefa);
        Departamento departamentoPessoa = pessoa.getDepartamento();
        Departamento departamentoTarefa = tarefa.getDepartamento();

        if (departamentoPessoa == departamentoTarefa) {
            tarefa.setPessoa(pessoa);
            gerenciadorTarefas.salvar(tarefa);
            return TarefaDTO.criaTarefaDTO(tarefa, pessoa.getId());
        }
        throw new IllegalArgumentException("Pessoa e tarefa pertencem a departamentos diferentes.");
    }

    public TarefaDTO finalizar(Long id) {
        Tarefa tarefa = gerenciadorTarefas.buscarPorId(id);
        tarefa.setConcluido(true);
        gerenciadorTarefas.salvar(tarefa);

        if (tarefa.getPessoa() != null) {
            Pessoa pessoa = gerenciadorPessoas.buscarPorId(tarefa.getPessoa().getId());
            return TarefaDTO.criaTarefaDTO(tarefa, pessoa.getId());
        }
        return TarefaDTO.criaTarefaDTO(tarefa);
    }

    public List<TarefaDTO> listaTarefasPendentes() {
        List<Tarefa> tarefas = gerenciadorTarefas.buscarTarefasPendentes();
        List<TarefaDTO> tarefasDTOList = new ArrayList<>();

        if (!tarefas.isEmpty()) {
            for (Tarefa tarefa : tarefas) {
                TarefaDTO tarefaDTO = TarefaDTO.criaTarefaDTO(tarefa);
                tarefasDTOList.add(tarefaDTO);
            }
        }
        return tarefasDTOList;
    }
}
