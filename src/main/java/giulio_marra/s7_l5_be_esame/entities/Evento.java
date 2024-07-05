package giulio_marra.s7_l5_be_esame.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titolo;
    private String descrizione;
    private LocalDate data_evento;
    private String luogo;
    private int numero_posti;

    public Evento(String titolo, String descrizione, LocalDate data_evento, String luogo, int numero_posti) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data_evento = data_evento;
        this.luogo = luogo;
        this.numero_posti = numero_posti;
    }
}
