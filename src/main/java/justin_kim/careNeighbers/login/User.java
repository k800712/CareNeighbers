package justin_kim.careNeighbers.login;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Table(name = "users")
@Entity
public record User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,

        @NotBlank(message = "Username must not be empty")
        String username,

        @NotBlank(message = "Email must not be empty")
        @Email(message = "Email should be valid")
        String email,

        String password
) {}
