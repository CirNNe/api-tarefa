package com.higor.azevedo.apiTarefas.service.tarefa;

import com.higor.azevedo.apiTarefas.model.Pessoa;
import com.higor.azevedo.apiTarefas.model.Tarefa;
import com.higor.azevedo.apiTarefas.repository.TarefaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GerenciadorTarefasTest {

    @Mock
    private TarefaRepository repository;

    @Autowired
    @InjectMocks
    GerenciadorTarefas gerenciadorTarefas;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Deve somar corretamente as durações das tarefas")
    void calculaHorasTarefas_deveSomarDuracoes() {
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setDuracao(5L);

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setDuracao(3L);

        List<Tarefa> tarefaList = Arrays.asList(tarefa1, tarefa2);

        Long resultado = gerenciadorTarefas.calculaHorasTarefas(tarefaList);

        assertEquals(8L, resultado);
    }

    @Test
    @DisplayName("Deve ignorar tarefas com duração nula e retornar 0")
    void calculaHorasTarefas_deveIgnorarDuracaoNula() {
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setDuracao(null);

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setDuracao(null);

        List<Tarefa> tarefaList = Arrays.asList(tarefa1, tarefa2);

        Long resultado = gerenciadorTarefas.calculaHorasTarefas(tarefaList);

        assertEquals(0L, resultado);
    }

    @Test
    @DisplayName("Deve somar as durações válidas e ignorar as nulas")
    void calculaHorasTarefas_deveSomarIgnorandoDuracaoNula() {
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setDuracao(5L);

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setDuracao(null);

        Tarefa tarefa3 = new Tarefa();
        tarefa3.setDuracao(3L);

        List<Tarefa> tarefaList = Arrays.asList(tarefa1, tarefa2, tarefa3);

        Long resultado = gerenciadorTarefas.calculaHorasTarefas(tarefaList);

        assertEquals(8L, resultado);
    }


    @Test
    @DisplayName("Deve calcular corretamente a média de horas")
    void calculaMediaHoras_deveCalcularMedia() {
        Pessoa pessoa = new Pessoa();
        LocalDate inicio = LocalDate.of(2024, 1, 1);
        LocalDate fim = LocalDate.of(2024, 1, 31);

        Tarefa tarefa1 = new Tarefa();
        tarefa1.setDuracao(5L);

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setDuracao(3L);

        List<Tarefa> tarefas = Arrays.asList(tarefa1, tarefa2);

        when(repository.findByPessoaAndPrazoBetween(pessoa, inicio, fim)).thenReturn(Optional.of(tarefas));

        Long resultado = gerenciadorTarefas.calculaMediaHoras(pessoa, inicio, fim);

        assertEquals(4L, resultado);
    }
}
