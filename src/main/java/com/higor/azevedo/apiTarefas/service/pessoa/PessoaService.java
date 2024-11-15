package com.higor.azevedo.apiTarefas.service.pessoa;

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

    public PessoaDTO salvar(PessoaDTO pessoaDTO) throws Exception {
        Pessoa pessoa = new Pessoa(pessoaDTO);
        Departamento departamento = gerenciadorDepartamento.buscarPorNome(pessoaDTO.departamento().nome());
        pessoa.setDepartamento(departamento);

        gerenciadorPessoas.salvar(pessoa);
        return pessoaDTO;
    }

    public PessoaDTO atualizar(Long id, PessoaDTO pessoaDTO) throws Exception {
        Pessoa pessoa = new Pessoa(pessoaDTO);
        pessoa.setId(id);
        Departamento departamento = gerenciadorDepartamento.buscarPorNome(pessoaDTO.departamento().nome());
        pessoa.setDepartamento(departamento);

        gerenciadorPessoas.salvar(pessoa);
        return pessoaDTO;
    }

    public void deletar(Long id) throws Exception {
        Pessoa pessoa = gerenciadorPessoas.buscarPorId(id);
        gerenciadorPessoas.deletar(pessoa);
    }

    public List<PessoaHorasGastasDTO> listarPessoas() {
        List<Pessoa> pessoas = gerenciadorPessoas.listarTodos();
        List<PessoaHorasGastasDTO> pessoaDTOList = new ArrayList<>();

        for (Pessoa pessoa : pessoas) {
            Long horasTarefas = gerenciadorTarefas.calculaHorasTarefas(pessoa.getTarefas());
            PessoaHorasGastasDTO pessoaHorasGastasDTO = PessoaHorasGastasDTO.criaPessoaHorasGastasDTO(
                    pessoa,
                    horasTarefas
            );
            pessoaDTOList.add(pessoaHorasGastasDTO);
        }
        return pessoaDTOList;
    }
}
