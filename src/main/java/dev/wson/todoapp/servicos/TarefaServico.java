package dev.wson.todoapp.servicos;

import dev.wson.todoapp.repositorios.TarefaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarefaServico {
    @Autowired
    TarefaRepositorio tarefaRepositorio;
}