package med.voll.api.domain.usuario;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode (of = "id")
@Table (name = "usuarios")
@Entity (name = "Usuario")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;
}
