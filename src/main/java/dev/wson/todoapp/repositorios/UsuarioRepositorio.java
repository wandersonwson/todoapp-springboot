package dev.wson.todoapp.repositorios;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev.wson.todoapp.modelos.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, UUID>{
    Optional<Usuario> findByEmail(String email);
}