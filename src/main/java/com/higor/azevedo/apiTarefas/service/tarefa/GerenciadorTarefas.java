package com.higor.azevedo.apiTarefas.service.tarefa;

import com.higor.azevedo.apiTarefas.model.Pessoa;
import com.higor.azevedo.apiTarefas.model.Tarefa;
import com.higor.azevedo.apiTarefas.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class GerenciadorTarefas {

    final String TAREFA_NAO_ENCONTRADA_MSG = "Tarefas não encontradas.";

    private final TarefaRepository repository;

    public GerenciadorTarefas(TarefaRepository repository) {
        this.repository = repository;
    }

    public void salvar(Tarefa tarefa) {
        repository.save(tarefa);
    }

    public Tarefa buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(TAREFA_NAO_ENCONTRADA_MSG));
    }

    public List<Tarefa> buscarTarefasPendentes() {
       return repository.findTop3ByPessoaIsNullOrderByPrazoAsc().orElseThrow(() -> new EntityNotFoundException(TAREFA_NAO_ENCONTRADA_MSG));
    }

    public Long calculaHorasTarefas(List<Tarefa> tarefaList) {
        long horasGastas = 0L;
        for (Tarefa tarefa : tarefaList) {
            if (tarefa.getDuracao() != null) {
                horasGastas += tarefa.getDuracao();
            }
        }
        return horasGastas;
    }

    public Long calculaMediaHoras(Pessoa pessoa, LocalDate inicio, LocalDate fim) {
        List<Tarefa> tarefasPorPeriodo = buscarTarefasPorPeriodo(
                pessoa, inicio, fim
        );

        if (tarefasPorPeriodo.isEmpty()) {
            return 0L;
        }
        Long horasGastas = calculaHorasTarefas(tarefasPorPeriodo);
        return horasGastas / tarefasPorPeriodo.size();
    }

    public List<Tarefa> buscarTarefasPorPeriodo(Pessoa pessoa, LocalDate inicio, LocalDate fim) {
        return repository.findByPessoaAndPrazoBetween(pessoa, inicio, fim).orElseThrow(() -> new EntityNotFoundException(TAREFA_NAO_ENCONTRADA_MSG));
    }
}
