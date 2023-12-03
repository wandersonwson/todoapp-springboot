package dev.wson.todoapp.modelos;

import java.util.List;
import java.util.UUID;
import org.hibernate.validator.constraints.Length;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Column(nullable = false, length = 100)
    @NotBlank(message = "Campo não informado")
    private String nome;
    @Column(nullable = false, length = 50, unique = true)
    @Email(message = "E-mail inválido")
    @NotBlank(message = "Campo não informado")
    private String email;
    @Column(nullable = false, length = 100)
    @Length(min = 6, message = "A senha deve conter no mínimo 6 caracteres")
    @NotBlank(message = "Campo não informado")
    private String senha;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private List<Tarefa> tarefas;
}