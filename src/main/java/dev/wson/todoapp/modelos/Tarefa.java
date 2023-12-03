package dev.wson.todoapp.modelos;

import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name = "tarefas")
public class Tarefa {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;
    @Column(nullable = false)
    @NotBlank(message = "Campo não informado")
    private String titulo;
    private String descricao;
    @Column(nullable = false)
    @NotNull(message = "Campo não informado")
    private LocalDateTime inicio;
    @Column(nullable = false)
    @NotNull(message = "Campo não informado")
    private LocalDateTime termino;
    @NotBlank(message = "Campo não informado")
    private String prioridade;
}