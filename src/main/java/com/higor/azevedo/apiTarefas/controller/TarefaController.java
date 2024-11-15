package com.higor.azevedo.apiTarefas.controller;

import com.higor.azevedo.apiTarefas.dto.TarefaDTO;
import com.higor.azevedo.apiTarefas.service.tarefa.TarefaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody TarefaDTO tarefaDTO) throws Exception {
        TarefaDTO tarefa = tarefaService.salvar(tarefaDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Dados da tarefa salvos");
    }
}
