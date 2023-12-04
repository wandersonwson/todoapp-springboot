package dev.wson.todoapp.servicos;

import dev.wson.todoapp.excecoes.ExcecaoGenerica;
import dev.wson.todoapp.modelos.Tarefa;
import dev.wson.todoapp.repositorios.TarefaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TarefaServico {
    @Autowired
    TarefaRepositorio tarefaRepositorio;

    public Tarefa cadastrar(UUID usuarioId, Tarefa tarefa) {
        tarefa.setUsuarioId(usuarioId);
        return tarefaRepositorio.save(tarefa);
    }
    public List<Tarefa> listar() {
        return tarefaRepositorio.findAll();
    }
    public Tarefa buscarPorId(UUID id) {
        Optional<Tarefa> tarefa = tarefaRepositorio.findById(id);
        if (tarefa.isEmpty()) throw new ExcecaoGenerica("Tarefa não encontrada");
        return tarefa.get();
    }
    public Tarefa atualizar(UUID id, UUID usuarioId, Tarefa tarefa) {
        Optional<Tarefa> tuplaTarefa = tarefaRepositorio.findById(id);
        if (tuplaTarefa.isEmpty()) throw new ExcecaoGenerica("Tarefa não encontrada");
        if (tuplaTarefa.get().getUsuarioId() != usuarioId) throw new ExcecaoGenerica("Usuário sem permissão para editar a tarefa");
        tarefa.setId(id);
        tarefa.setUsuarioId(usuarioId);
        return tarefaRepositorio.save(tarefa);
    }
    public void excluir(UUID id, UUID usuarioId) {
        Optional<Tarefa> tarefa = tarefaRepositorio.findById(id);
        if (tarefa.isEmpty()) throw new ExcecaoGenerica("Tarefa não encontrada");
        if (tarefa.get().getUsuarioId() != usuarioId) throw new ExcecaoGenerica("Usuário sem permissão para excluir a tarefa");
        tarefaRepositorio.deleteById(id);
    }
}