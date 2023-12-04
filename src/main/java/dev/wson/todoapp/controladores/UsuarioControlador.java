package dev.wson.todoapp.controladores;

import java.util.List;
import java.util.UUID;
import dev.wson.todoapp.servicos.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.wson.todoapp.modelos.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {
    @Autowired
    private UsuarioServico usuarioServico;

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Usuario usuario) {
        try {
            Usuario novoUsuario = usuarioServico.cadastrar(usuario);
            return ResponseEntity.status(201).body(novoUsuario);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<Object> listar() {
        try {
            List<Usuario> usuarios = usuarioServico.listar();
            return ResponseEntity.status(200).body(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro no servidor");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorID(@PathVariable @NotBlank UUID id) {
        try {
            Usuario usuario = usuarioServico.buscarPorId(id);
            return ResponseEntity.status(200).body(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable @NotBlank UUID id, @RequestBody @Valid Usuario usuario) {
        try {
            Usuario usuarioAtualizado = usuarioServico.atualizar(id, usuario);
            return ResponseEntity.status(200).body(usuarioAtualizado);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable @NotBlank UUID id) {
        try {
            usuarioServico.excluir(id);
            return ResponseEntity.status(200).body("Usuário excluído");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}