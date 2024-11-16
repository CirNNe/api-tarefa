package com.higor.azevedo.apiTarefas.service.tarefa;

import com.higor.azevedo.apiTarefas.dto.DepartamentoDTO;
import com.higor.azevedo.apiTarefas.dto.PessoaDTO;
import com.higor.azevedo.apiTarefas.dto.TarefaDTO;
import com.higor.azevedo.apiTarefas.model.Departamento;
import com.higor.azevedo.apiTarefas.model.Pessoa;
import com.higor.azevedo.apiTarefas.model.Tarefa;
import com.higor.azevedo.apiTarefas.service.departamento.GerenciadorDepartamento;
import com.higor.azevedo.apiTarefas.service.pessoa.GerenciadorPessoas;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TarefaServiceTest {

    @Mock
    private GerenciadorDepartamento gerenciadorDepartamento;

    @Mock
    private GerenciadorTarefas gerenciadorTarefas;

    @Mock
    private GerenciadorPessoas gerenciadorPessoas;

    @Autowired
    @InjectMocks
    private TarefaService tarefaService;

    DepartamentoDTO departamentoDTO;
    TarefaDTO tarefaDTO;
    PessoaDTO pessoaDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        departamentoDTO = new DepartamentoDTO("Desenvolvimento");
        tarefaDTO = new TarefaDTO(
                "Desenvolver API",
                "Desenvolver API de tarefas",
                LocalDate.of(2024, 11, 15),
                0L,
                false,
                departamentoDTO,
                "João Dev"
        );
        pessoaDTO = new PessoaDTO("João Dev", departamentoDTO);
    }

    @Test
    @DisplayName("Deve salvar uma tarefa com sucesso")
    void salvar_deveSalvarTarefaComSucesso() {
        Departamento departamento = new Departamento(departamentoDTO);
        Pessoa pessoa = new Pessoa(pessoaDTO);

        when(gerenciadorDepartamento.buscarPorNome(departamento.getNome())).thenReturn(Optional.of(departamento));
        when(gerenciadorPessoas.buscarPorNome(pessoa.getNome())).thenReturn(pessoa);

        TarefaDTO resultado = tarefaService.salvar(tarefaDTO);

        assertNotNull(resultado);

        verify(gerenciadorDepartamento).buscarPorNome("Desenvolvimento");
        verify(gerenciadorPessoas).buscarPorNome("João Dev");
        verify(gerenciadorTarefas).salvar(any(Tarefa.class));
    }

    @Test
    @DisplayName("Deve alocar uma pessoa a tarefa com sucesso")
    void alocarPessoa_deveAlocarUmaPessoaATarefaComSucesso() {
        Long idTarefa = 1L;
        Long idPessoa = 1L;
        Departamento departamento = new Departamento(departamentoDTO);
        Pessoa pessoa = new Pessoa();
        pessoa.setDepartamento(departamento);
        Tarefa tarefa = new Tarefa();
        tarefa.setDepartamento(departamento);

        when(gerenciadorPessoas.buscarPorId(idPessoa)).thenReturn(pessoa);
        when(gerenciadorTarefas.buscarPorId(idTarefa)).thenReturn(tarefa);

        TarefaDTO resultado = tarefaService.alocarPessoa(idTarefa, idPessoa);

        assertNotNull(resultado);
        assertEquals(pessoa.getNome(), resultado.nomePessoa());
        verify(gerenciadorTarefas, times(1)).salvar(tarefa);
    }

    @Test
    @DisplayName("Deve lançar exceção por departamentos diferentes")
    void alocarPessoa_deveLancarExecaoDiferentesDepartamentos() {
        Long idTarefa = 1L;
        Long idPessoa = 1L;
        Departamento departamento = new Departamento(departamentoDTO);
        Departamento departamentoDiferente = new Departamento();
        departamentoDiferente.setNome("Departamento diferente");

        Pessoa pessoa = new Pessoa();
        pessoa.setDepartamento(departamento);

        Tarefa tarefa = new Tarefa();
        tarefa.setDepartamento(departamentoDiferente);

        when(gerenciadorPessoas.buscarPorId(idPessoa)).thenReturn(pessoa);
        when(gerenciadorTarefas.buscarPorId(idTarefa)).thenReturn(tarefa);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> tarefaService.alocarPessoa(idTarefa, idPessoa)
        );

        assertEquals("Pessoa e tarefa pertencem a departamentos diferentes.", exception.getMessage());
        verify(gerenciadorTarefas, never()).salvar(any(Tarefa.class));
    }

    @Test
    @DisplayName("Deve lançar exceção por não encontrar a tarefa")
    void alocarPessoa_deveLancarExcecaoTarefaNaoEncontrada() {
        Long idTarefa = 1L;
        Long idPessoa = 1L;
        Departamento departamento = new Departamento(departamentoDTO);
        Pessoa pessoa = new Pessoa();
        pessoa.setDepartamento(departamento);
        Tarefa tarefa = new Tarefa();
        tarefa.setDepartamento(departamento);

        when(gerenciadorPessoas.buscarPorId(idPessoa)).thenReturn(pessoa);
        when(gerenciadorTarefas.buscarPorId(idTarefa)).thenThrow(new EntityNotFoundException("Tarefa não encontrada."));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> gerenciadorTarefas.buscarPorId(idTarefa));
        assertEquals("Tarefa não encontrada.", exception.getMessage());

        verify(gerenciadorTarefas, never()).salvar(any(Tarefa.class));
    }

    @Test
    @DisplayName("Deve finalizar uma tarefa com sucesso")
    void finalizar_deveFinalizarComSucesso() {
        Long idTarefa = 1L;
        Long idPessoa = 1L;

        Departamento departamento = new Departamento(departamentoDTO);

        Pessoa pessoa = new Pessoa();
        pessoa.setId(idPessoa);

        Tarefa tarefa = new Tarefa(tarefaDTO);
        tarefa.setId(idTarefa);
        tarefa.setConcluido(true);
        tarefa.setPessoa(pessoa);
        tarefa.setDepartamento(departamento);


        when(gerenciadorTarefas.buscarPorId(idTarefa)).thenReturn(tarefa);
        when(gerenciadorPessoas.buscarPorId(idPessoa)).thenReturn(pessoa);

        TarefaDTO resultado = tarefaService.finalizar(idTarefa);

        assertNotNull(resultado);
        assertTrue(tarefa.isConcluido());
        assertEquals(pessoa.getNome(), resultado.nomePessoa());
        assertEquals(departamento.getNome(), resultado.departamento().nome());
        verify(gerenciadorTarefas).salvar(tarefa);
        verify(gerenciadorPessoas).buscarPorId(idPessoa);
    }

    @Test
    @DisplayName("Deve retornar uma lista de tarefas DTO quando houver tarefas pendentes")
    void listaTarefasPendentes_deveRetornarListaComTarefas() {
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setTitulo("Tarefa 1");
        tarefa1.setConcluido(false);

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setTitulo("Tarefa 2");
        tarefa2.setConcluido(false);

        List<Tarefa> tarefasPendentes = Arrays.asList(tarefa1, tarefa2);

        when(gerenciadorTarefas.buscarTarefasPendentes()).thenReturn(tarefasPendentes);

        List<TarefaDTO> resultado = tarefaService.listaTarefasPendentes();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(tarefa1.getTitulo(), resultado.get(0).titulo());
        assertEquals(tarefa2.getTitulo(), resultado.get(1).titulo());
        verify(gerenciadorTarefas, times(1)).buscarTarefasPendentes();
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia quando não houver tarefas pendentes")
    void listaTarefasPendentes_deveRetornarListaVazia() {
        when(gerenciadorTarefas.buscarTarefasPendentes()).thenReturn(new ArrayList<>());

        List<TarefaDTO> resultado = tarefaService.listaTarefasPendentes();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(gerenciadorTarefas, times(1)).buscarTarefasPendentes();
    }
}
