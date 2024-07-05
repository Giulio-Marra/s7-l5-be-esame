package giulio_marra.s7_l5_be_esame.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UtenteRequiredDto(
        @NotEmpty(message = "Inserisci un nome")
        String name,
        @NotEmpty(message = "Inserisci un cognome")
        String surname,
        @NotEmpty(message = "Inserisci uno username")
        String username,
        @Email(message = "inserisci una mail")
        String email,
        @NotEmpty(message = "Inserisci una password")
        String password
) {
}
