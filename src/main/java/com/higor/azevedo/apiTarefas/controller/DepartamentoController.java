package com.higor.azevedo.apiTarefas.controller;

import com.higor.azevedo.apiTarefas.dto.DepartamentoDTO;
import com.higor.azevedo.apiTarefas.dto.DepartamentoPessoasTarefasDTO;
import com.higor.azevedo.apiTarefas.service.departamento.DepartamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @PostMapping
    public ResponseEntity<DepartamentoDTO> salvar(@RequestBody DepartamentoDTO departamentoDTO) {
        DepartamentoDTO departamento = departamentoService.salvar(departamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(departamento);
    }

    @GetMapping
    public ResponseEntity<List<DepartamentoPessoasTarefasDTO>> listar() {
        List<DepartamentoPessoasTarefasDTO> departamentos = departamentoService.listarDepartamentos();
        return ResponseEntity.status(HttpStatus.OK).body(departamentos);
    }
}
