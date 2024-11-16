package com.higor.azevedo.apiTarefas.service.departamento;

import com.higor.azevedo.apiTarefas.dto.DepartamentoDTO;
import com.higor.azevedo.apiTarefas.dto.DepartamentoPessoasTarefasDTO;
import com.higor.azevedo.apiTarefas.dto.PessoaDTO;
import com.higor.azevedo.apiTarefas.dto.TarefaDTO;
import com.higor.azevedo.apiTarefas.model.Departamento;
import com.higor.azevedo.apiTarefas.model.Pessoa;
import com.higor.azevedo.apiTarefas.model.Tarefa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DepartamentoServiceTest {

    @Mock
    private GerenciadorDepartamento gerenciadorDepartamento;

    @Autowired
    @InjectMocks
    private DepartamentoService departamentoService;

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
    @DisplayName("Deve salvar um departamento com sucesso")
    void salva_deSalvarNovoDepartamentoComSucesso() {
        departamentoService.salvar(departamentoDTO);

        verify(gerenciadorDepartamento, times(1)).salvar(any());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar salvar um departamento")
    void salvar_deveLancarExcecaoAoTentarSalvarNovoDepartamento() {
        doThrow(DataIntegrityViolationException.class).when(gerenciadorDepartamento).salvar(any());

        assertThrows(DataIntegrityViolationException.class, () -> departamentoService.salvar(departamentoDTO));

        verify(gerenciadorDepartamento, times(1)).salvar(any());
    }

    @Test
    @DisplayName("Deve listar departamentos e a quantidade de pessoas e tarefas associadas a ele com sucesso")
    void listar_deveListarTodosDepartamentos() {
        Departamento departamentoUm = new Departamento(
                1L,
                "Desenvolvimento",
                List.of(new Pessoa(pessoaDTO)),
                List.of(new Tarefa(tarefaDTO))
        );
        Departamento departamentoDois = new Departamento(
                2L,
                "RH",
                List.of(new Pessoa(pessoaDTO)),
                List.of(new Tarefa(tarefaDTO))
        );
        List<Departamento> departamentoList = List.of(departamentoUm, departamentoDois);

        when(gerenciadorDepartamento.listarTodos()).thenReturn(departamentoList);

        List<DepartamentoPessoasTarefasDTO> resultaEsperado = List.of(
                DepartamentoPessoasTarefasDTO.criaDepartamentoPessoasTarefasDTO(
                        "Desenvolvimento",
                        1,
                        1
                ),
                DepartamentoPessoasTarefasDTO.criaDepartamentoPessoasTarefasDTO(
                        "RH",
                        1,
                        1
                )
        );

        List<DepartamentoPessoasTarefasDTO> resultadoObtido = departamentoService.listarDepartamentos();

        assertEquals(resultaEsperado, resultadoObtido);
    }
}
