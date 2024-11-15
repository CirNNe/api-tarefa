package com.higor.azevedo.apiTarefas.controller;

import com.higor.azevedo.apiTarefas.dto.DepartamentoPessoasTarefasDTO;
import com.higor.azevedo.apiTarefas.service.departamento.DepartamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping
    public ResponseEntity<List<DepartamentoPessoasTarefasDTO>> listar() {
        List<DepartamentoPessoasTarefasDTO> departamentos = departamentoService.listarDepartamentos();
        return ResponseEntity.status(HttpStatus.OK).body(departamentos);
    }
}
