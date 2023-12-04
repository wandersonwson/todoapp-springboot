package dev.wson.todoapp.servicos;

import dev.wson.todoapp.excecoes.ExcecaoGenerica;
import dev.wson.todoapp.modelos.Usuario;
import dev.wson.todoapp.repositorios.UsuarioRepositorio;
import dev.wson.todoapp.utilidades.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServico {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private Criptografia criptografia;

    public Usuario cadastrar(Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepositorio.findByEmail(usuario.getEmail());
        if (usuarioExistente.isPresent()) throw new ExcecaoGenerica("Já existe um usuário cadastrado com o email informado");
        String cifra = criptografia.cifrarSenha(usuario.getSenha());
        usuario.setSenha(cifra);
        return usuarioRepositorio.save(usuario);
    }
    public List<Usuario> listar() {
        return usuarioRepositorio.findAll();
    }
    public Usuario buscarPorId(UUID id) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        if (usuario.isEmpty()) throw new ExcecaoGenerica("Usuário não encontrado");
        return usuario.get();
    }
    public Usuario atualizar(UUID id, Usuario usuario) {
        Optional<Usuario> tuplaUsuario = usuarioRepositorio.findById(id);
        if (tuplaUsuario.isEmpty()) throw new ExcecaoGenerica("Usuário não encontrado");
        usuario.setId(id);
        String cifra = criptografia.cifrarSenha(usuario.getSenha());
        usuario.setSenha(cifra);
        return usuarioRepositorio.save(usuario);
    }
    public void excluir(UUID id) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        if (usuario.isEmpty()) throw new ExcecaoGenerica("Usuário não encontrado");
        usuarioRepositorio.deleteById(id);
    }
}