package dev.wson.todoapp.repositorios;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import dev.wson.todoapp.modelos.Tarefa;

public interface TarefaRepositorio extends JpaRepository<Tarefa, UUID> {}