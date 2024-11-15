package com.higor.azevedo.apiTarefas.service.tarefa;

import com.higor.azevedo.apiTarefas.model.Pessoa;
import com.higor.azevedo.apiTarefas.model.Tarefa;
import com.higor.azevedo.apiTarefas.repository.TarefaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class GerenciadorTarefas {

    private final TarefaRepository repository;

    public GerenciadorTarefas(TarefaRepository repository) {
        this.repository = repository;
    }

    public void salvar(Tarefa tarefa) {
        repository.save(tarefa);
    }

    public Tarefa buscarPorId(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Tarefa não encontrada."));
    }

    public List<Tarefa> buscarTarefasPendentes() throws Exception {
       return repository.findTop3ByPessoaIsNullOrderByPrazoAsc().orElseThrow(() -> new Exception("Tarefas não encontradas."));
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

    public Long calculaMediaHoras(Pessoa pessoa, LocalDate inicio, LocalDate fim) throws Exception {
        List<Tarefa> tarefasPorPeriodo = buscarTarefasPorPeriodo(
                pessoa, inicio, fim
        );

        if (tarefasPorPeriodo.isEmpty()) {
            return 0L;
        }
        Long horasGastas = calculaHorasTarefas(tarefasPorPeriodo);
        return horasGastas / tarefasPorPeriodo.size();
    }

    public List<Tarefa> buscarTarefasPorPeriodo(Pessoa pessoa, LocalDate inicio, LocalDate fim) throws Exception {
        return repository.findByPessoaAndPrazoBetween(pessoa, inicio, fim).orElseThrow(() -> new Exception("Tarefas não encontradas."));
    }
}
