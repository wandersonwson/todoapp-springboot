package dev.wson.todoapp.controladores;

import java.util.UUID;
import java.util.List;
import dev.wson.todoapp.servicos.TarefaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.wson.todoapp.modelos.Tarefa;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/tarefa")
public class TarefaControlador {
    @Autowired
    private TarefaServico tarefaServico;

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Tarefa tarefa, HttpServletRequest request) {
        UUID usuarioId = (UUID) request.getAttribute("usuarioId");
        try {
            Tarefa novaTarefa = tarefaServico.cadastrar(usuarioId, tarefa);
            return ResponseEntity.status(201).body(novaTarefa);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro no servidor");
        }
    }
    @GetMapping
    public ResponseEntity<Object> listar() {
        try {
            List<Tarefa> listaTarefas = tarefaServico.listar();
            return ResponseEntity.status(200).body(listaTarefas);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro no servidor");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable @NotBlank UUID id) {
        try {
            Tarefa tarefa = tarefaServico.buscarPorId(id);
            return ResponseEntity.status(200).body(tarefa);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable @NotBlank UUID id, HttpServletRequest request, @RequestBody @Valid Tarefa tarefa) {
        UUID usuarioId = (UUID) request.getAttribute("usuarioId");
        try {
            Tarefa tarefaAtualizada = tarefaServico.atualizar(id, usuarioId, tarefa);
            return ResponseEntity.status(200).body(tarefaAtualizada);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable @NotBlank UUID id, HttpServletRequest request) {
        UUID usuarioId = (UUID) request.getAttribute("usuarioId");
        try {
            tarefaServico.excluir(id, usuarioId);
            return ResponseEntity.status(200).body("Tarefa excluída");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}