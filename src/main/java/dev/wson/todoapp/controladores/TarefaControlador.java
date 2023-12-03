package dev.wson.todoapp.controladores;

import java.util.UUID;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.wson.todoapp.modelos.Tarefa;
import dev.wson.todoapp.repositorios.TarefaRepositorio;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/tarefa")
public class TarefaControlador {
    @Autowired
    private TarefaRepositorio tarefaRepositorio;

    @PostMapping
    public ResponseEntity<Tarefa> cadastrar(@RequestBody @Valid Tarefa tarefa, HttpServletRequest request) {
        UUID usuarioId = (UUID) request.getAttribute("usuarioId");
        tarefa.setUsuarioId(usuarioId);
        Tarefa novaTarefa = tarefaRepositorio.save(tarefa);
        return ResponseEntity.status(201).body(novaTarefa);
    }
    @GetMapping
    public ResponseEntity<List<Tarefa>> listar() {
        List<Tarefa> listaTarefas = tarefaRepositorio.findAll();
        return ResponseEntity.status(200).body(listaTarefas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable @NotBlank UUID id) {
        Optional<Tarefa> tarefOptional = tarefaRepositorio.findById(id);
        if (tarefOptional.isEmpty()) {
            return ResponseEntity.status(400).body("Tarefa não encontrada");
        }
        return ResponseEntity.status(200).body(tarefOptional.get());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable @NotBlank UUID id, HttpServletRequest request, @RequestBody @Valid Tarefa tarefa) {
        Optional<Tarefa> tarefaOptional = tarefaRepositorio.findById(id);
        if (tarefaOptional.isEmpty()) {
            return ResponseEntity.status(400).body("Tarefa não encontrada");
        }
        Tarefa registroTarefa = tarefaOptional.get();
        UUID usuarioId = (UUID) request.getAttribute("usuarioId");
        if (registroTarefa.getUsuarioId() != usuarioId) {
            return ResponseEntity.status(401).body("Você não tem permissão para editar a tarefa");
        }
        tarefa.setId(id);
        tarefa.setUsuarioId(usuarioId);
        Tarefa tarefaAtualizada = tarefaRepositorio.save(tarefa);
        return ResponseEntity.status(200).body(tarefaAtualizada);
    }
}