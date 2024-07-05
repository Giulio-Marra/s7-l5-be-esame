package giulio_marra.s7_l5_be_esame.entities;

import giulio_marra.s7_l5_be_esame.enums.Ruoli;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Ruoli ruoli;

    public Utente(String name, String surname, String username, String email, String password, Ruoli ruoli) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.ruoli = ruoli;
    }
}
