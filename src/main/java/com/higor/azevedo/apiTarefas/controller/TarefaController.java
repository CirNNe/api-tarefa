package com.higor.azevedo.apiTarefas.controller;

import com.higor.azevedo.apiTarefas.dto.TarefaDTO;
import com.higor.azevedo.apiTarefas.service.tarefa.TarefaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<TarefaDTO> salvar(@RequestBody TarefaDTO tarefaDTO) {
        TarefaDTO tarefa = tarefaService.salvar(tarefaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefa);
    }

    @PutMapping("/alocar/{id}")
    public ResponseEntity<TarefaDTO> alocar(@PathVariable("id") Long idTarefa, @RequestBody Long idPessoa) {
        TarefaDTO tarefa = tarefaService.alocarPessoa(idTarefa, idPessoa);
        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<TarefaDTO> finalizar(@PathVariable("id") Long id) {
        TarefaDTO tarefa = tarefaService.finalizar(id);
        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<TarefaDTO>> pendentes() {
        List<TarefaDTO> tarefas = tarefaService.listaTarefasPendentes();
        return ResponseEntity.status(HttpStatus.OK).body(tarefas);
    }
}
