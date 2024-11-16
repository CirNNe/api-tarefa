package com.higor.azevedo.apiTarefas.service.pessoa;

import com.higor.azevedo.apiTarefas.dto.*;
import com.higor.azevedo.apiTarefas.model.Departamento;
import com.higor.azevedo.apiTarefas.model.Pessoa;
import com.higor.azevedo.apiTarefas.model.Tarefa;
import com.higor.azevedo.apiTarefas.service.departamento.GerenciadorDepartamento;
import com.higor.azevedo.apiTarefas.service.tarefa.GerenciadorTarefas;
import jakarta.persistence.EntityNotFoundException;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PessoaServiceTest {

    @Mock
    private GerenciadorDepartamento gerenciadorDepartamento;

    @Mock
    private GerenciadorPessoas gerenciadorPessoas;

    @Mock
    private GerenciadorTarefas gerenciadorTarefas;

    @Autowired
    @InjectMocks
    private PessoaService pessoaService;

    private static final String PESSOA_NAO_ENCONTRADA_MSG = "Pessoa não encontrada.";
    private static final String DEPARTAMENTO_NAO_ENCONTRADO_MSG = "Departamento não encontrado";

    DepartamentoDTO departamentoDTO;
    TarefaDTO tarefaDTO;
    PessoaDTO pessoaDTO;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        departamentoDTO = new DepartamentoDTO("Desenvolviemento");
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
    @DisplayName("Deve salvar dados de pessoa com sucesso")
    void salvar_deveSalvarPessoaComSucesso() {
        Departamento departamento = new Departamento(departamentoDTO);
        when(gerenciadorDepartamento.buscarPorNome(departamentoDTO.nome())).thenReturn(Optional.of(departamento));

        PessoaDTO resultado = pessoaService.salvar(pessoaDTO);

        assertNotNull(resultado);
        assertEquals(pessoaDTO, resultado);

        verify(gerenciadorDepartamento, times(1)).buscarPorNome(departamentoDTO.nome());
        verify(gerenciadorPessoas, times(1)).salvar(any(Pessoa.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar salvar dados de pessoa")
    void salvar_deveLancarExcecaoQuandoDepartamentoNaoExiste() {
        when(gerenciadorDepartamento.buscarPorNome(departamentoDTO.nome())).thenReturn(Optional.empty());

        IllegalArgumentException execption = assertThrows(IllegalArgumentException.class, () -> pessoaService.salvar(pessoaDTO));
        assertEquals(DEPARTAMENTO_NAO_ENCONTRADO_MSG, execption.getMessage());

        verify(gerenciadorDepartamento, times(1)).buscarPorNome(departamentoDTO.nome());
        verifyNoInteractions(gerenciadorPessoas);
    }

    @Test
    @DisplayName("Deve atualizar os dados de uma pessoa com sucesso")
    void atualizar_deveAtualizarDadosDeUmaPessoaComSucesso() {
        Long id = 1L;
        Departamento departamento = new Departamento(departamentoDTO);

        when(gerenciadorDepartamento.buscarPorNome(departamentoDTO.nome())).thenReturn(Optional.of(departamento));

        PessoaDTO resultado = pessoaService.atualizar(id, pessoaDTO);

        assertNotNull(resultado);
        assertEquals(pessoaDTO, resultado);

        verify(gerenciadorDepartamento, times(1)).buscarPorNome(departamentoDTO.nome());
        verify(gerenciadorPessoas).salvar(argThat(pessoa ->
                pessoa.getId().equals(id) && pessoa.getDepartamento().equals(departamento)
        ));
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar dados de uma pessoa quando o departamento não existe")
    void atualizar_deveLancarExcecaoQuandoDepartamentoNaoExiste() {
        Long id = 1L;

        when(gerenciadorDepartamento.buscarPorNome(departamentoDTO.nome())).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> pessoaService.atualizar(id, pessoaDTO));

        assertEquals(DEPARTAMENTO_NAO_ENCONTRADO_MSG, exception.getMessage());

        verify(gerenciadorDepartamento, times(1)).buscarPorNome(departamentoDTO.nome());
        verifyNoInteractions(gerenciadorPessoas);
    }

    @Test
    @DisplayName("Deve deletar dados de uma pessoa com sucesso")
    void deletar_deveDeletarPessoaQuandoIdValido() {
        Long id = 1L;
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);

        when(gerenciadorPessoas.buscarPorId(id)).thenReturn(pessoa);

        pessoaService.deletar(id);

        verify(gerenciadorPessoas, times(1)).buscarPorId(id);
        verify(gerenciadorPessoas, times(1)).deletar(pessoa);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar dados de uma pessoa não encotrados")
    void deletar_deveLancarExcecaoQuandoPessoaNaoEncontrada() {
        Long id = 1L;

        when(gerenciadorPessoas.buscarPorId(id))
                .thenThrow(new EntityNotFoundException(PESSOA_NAO_ENCONTRADA_MSG));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> pessoaService.deletar(id));

        assertEquals(PESSOA_NAO_ENCONTRADA_MSG, exception.getMessage());

        verify(gerenciadorPessoas, times(1)).buscarPorId(id);
        verifyNoMoreInteractions(gerenciadorPessoas);
    }

    @Test
    @DisplayName("Deve retornar lista de pessoas acompanhado do total de horas gastas em tarefas")
    void listarPessoas_DeveRetornarListaDePessoasHorasGastasDTO() {
        Departamento departamento = new Departamento(departamentoDTO);
        Tarefa tarefa = new Tarefa(tarefaDTO);

        Pessoa pessoaUm = new Pessoa();
        pessoaUm.setNome("João");
        pessoaUm.setDepartamento(departamento);
        pessoaUm.setTarefas(List.of(tarefa));

        Pessoa pessoaDois = new Pessoa();
        pessoaDois.setNome("Maria");
        pessoaDois.setDepartamento(departamento);
        pessoaDois.setTarefas(List.of(tarefa));

        when(gerenciadorPessoas.listarTodos()).thenReturn(Arrays.asList(pessoaUm, pessoaDois));
        when(gerenciadorTarefas.calculaHorasTarefas(pessoaUm.getTarefas())).thenReturn(3L);
        when(gerenciadorTarefas.calculaHorasTarefas(pessoaDois.getTarefas())).thenReturn(3L);

        List<PessoaHorasGastasDTO> resultado = pessoaService.listarPessoas();

        assertEquals(2, resultado.size());

        assertEquals("João", resultado.get(0).nome());
        assertEquals(3L, resultado.get(0).horasGastas());

        assertEquals("Maria", resultado.get(1).nome());
        assertEquals(3L, resultado.get(1).horasGastas());

        verify(gerenciadorPessoas, times(1)).listarTodos();
        verify(gerenciadorTarefas, times(2)).calculaHorasTarefas(any());
    }

    @Test
    @DisplayName("Deve retornar a média de horas gastas em tarefas em um periodo, buscando pessoa por nome")
    void mediaHoraGastaPorPeriodo_deveRetornarMediaHorasGastasEmTarefasEmUmPeriodo() {
        Pessoa pessoa = new Pessoa(pessoaDTO);
        LocalDate inicio = LocalDate.of(2024, 11, 16);
        LocalDate fim = LocalDate.of(2024, 11, 16);
        MediaHoraGastaPeriodoDTO dto = new MediaHoraGastaPeriodoDTO(pessoa.getNome(), inicio, fim);

        when(gerenciadorPessoas.buscarPorNome(pessoa.getNome())).thenReturn(pessoa);
        when(gerenciadorTarefas.calculaMediaHoras(pessoa, inicio, fim)).thenReturn(8L);

        Long mediaHoras = pessoaService.mediaHoraGastaPorPeriodo(dto);

        assertEquals(8L, mediaHoras);

        verify(gerenciadorPessoas, times(1)).buscarPorNome(pessoa.getNome());
        verify(gerenciadorTarefas, times(1)).calculaMediaHoras(pessoa, inicio, fim);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar retornar media de horas gastas por pessoa em um periodo")
    void mediaHoraGastaPorPeriodo_deveLancarExececaoPessoaNaoEncontrada() {
        Pessoa pessoa = new Pessoa(pessoaDTO);
        LocalDate inicio = LocalDate.of(2024, 11, 16);
        LocalDate fim = LocalDate.of(2024, 11, 16);
        MediaHoraGastaPeriodoDTO dto = new MediaHoraGastaPeriodoDTO(pessoa.getNome(), inicio, fim);

        when(gerenciadorPessoas.buscarPorNome(pessoa.getNome()))
                .thenThrow(new EntityNotFoundException(PESSOA_NAO_ENCONTRADA_MSG));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> pessoaService.mediaHoraGastaPorPeriodo(dto));

        assertEquals(PESSOA_NAO_ENCONTRADA_MSG, exception.getMessage());
    }
}