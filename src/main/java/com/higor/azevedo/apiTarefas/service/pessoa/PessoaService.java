package com.higor.azevedo.apiTarefas.service.pessoa;

import com.higor.azevedo.apiTarefas.dto.DepartamentoDTO;
import com.higor.azevedo.apiTarefas.dto.MediaHoraGastaPeriodoDTO;
import com.higor.azevedo.apiTarefas.dto.PessoaDTO;
import com.higor.azevedo.apiTarefas.dto.PessoaHorasGastasDTO;
import com.higor.azevedo.apiTarefas.model.Departamento;
import com.higor.azevedo.apiTarefas.model.Pessoa;
import com.higor.azevedo.apiTarefas.service.departamento.GerenciadorDepartamento;
import com.higor.azevedo.apiTarefas.service.tarefa.GerenciadorTarefas;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaService {

    private final GerenciadorDepartamento gerenciadorDepartamento;
    private final GerenciadorPessoas gerenciadorPessoas;
    private final GerenciadorTarefas gerenciadorTarefas;

    public PessoaService(
            GerenciadorDepartamento gerenciadorDepartamento,
            GerenciadorPessoas gerenciadorPessoas,
            GerenciadorTarefas gerenciadorTarefas
    ) {
        this.gerenciadorDepartamento = gerenciadorDepartamento;
        this.gerenciadorPessoas = gerenciadorPessoas;
        this.gerenciadorTarefas = gerenciadorTarefas;
    }

    public PessoaDTO salvar(PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa(pessoaDTO);
        Departamento departamento = gerenciadorDepartamento.buscarPorNome(
                pessoaDTO.departamento().nome()).orElseThrow(() -> new IllegalArgumentException(
                gerenciadorDepartamento.DEPARTAMENTO_NAO_ENCONTRADO_MSG)
        );
        pessoa.setDepartamento(departamento);

        gerenciadorPessoas.salvar(pessoa);
        return pessoaDTO;
    }

    public PessoaDTO atualizar(Long id, PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa(pessoaDTO);
        pessoa.setId(id);
        Departamento departamento = gerenciadorDepartamento.buscarPorNome(
                pessoaDTO.departamento().nome()).orElseThrow(() -> new IllegalArgumentException(
                        gerenciadorDepartamento.DEPARTAMENTO_NAO_ENCONTRADO_MSG)
        );
        pessoa.setDepartamento(departamento);

        gerenciadorPessoas.salvar(pessoa);
        return pessoaDTO;
    }

    public void deletar(Long id) {
        Pessoa pessoa = gerenciadorPessoas.buscarPorId(id);
        gerenciadorPessoas.deletar(pessoa);
    }

    public List<PessoaHorasGastasDTO> listarPessoas() {
        List<Pessoa> pessoas = gerenciadorPessoas.listarTodos();
        return criaListaPessoaHoraGastasDTO(pessoas);
    }

    private List<PessoaHorasGastasDTO> criaListaPessoaHoraGastasDTO(List<Pessoa> pessoaList) {
        List<PessoaHorasGastasDTO> pessoaDTOList = new ArrayList<>();
        for (Pessoa pessoa : pessoaList) {
            Long horasTarefas = gerenciadorTarefas.calculaHorasTarefas(pessoa.getTarefas());
            DepartamentoDTO departamentoDTO = new DepartamentoDTO(pessoa.getDepartamento().getNome());
            PessoaHorasGastasDTO pessoaHorasGastasDTO = PessoaHorasGastasDTO.criaPessoaHorasGastasDTO(
                    pessoa,
                    departamentoDTO,
                    horasTarefas
            );
            pessoaDTOList.add(pessoaHorasGastasDTO);
        }
        return pessoaDTOList;
    }

    public Long mediaHoraGastaPorPeriodo(MediaHoraGastaPeriodoDTO mediaHoraGastaPeriodoDTO) {
        Pessoa pessoa = gerenciadorPessoas.buscarPorNome(mediaHoraGastaPeriodoDTO.nome());
        return gerenciadorTarefas.calculaMediaHoras(
                pessoa, mediaHoraGastaPeriodoDTO.inicio(), mediaHoraGastaPeriodoDTO.fim()
        );
    }
}
