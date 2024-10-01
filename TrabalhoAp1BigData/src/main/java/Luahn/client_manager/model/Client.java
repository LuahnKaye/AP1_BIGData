package Luahn.client_manager.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O email deve ser válido")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve seguir o padrão XXX.XXX.XXX-XX")
    @Column(unique = true)
    private String cpf;

    @Past(message = "A data de nascimento deve ser no passado")
    @NotNull(message = "A data de nascimento é obrigatória")
    private LocalDate dataNascimento;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "O telefone deve seguir o formato (XX) XXXXX-XXXX")
    private String telefone;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Address> enderecos;
}
