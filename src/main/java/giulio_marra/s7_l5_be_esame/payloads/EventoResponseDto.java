package giulio_marra.s7_l5_be_esame.payloads;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EventoResponseDto {
    private Long id;
    private String titolo;
    private String descrizione;
    private LocalDate data_evento;
    private String luogo;
    private int numero_posti;
    private String organizzatore;

}
