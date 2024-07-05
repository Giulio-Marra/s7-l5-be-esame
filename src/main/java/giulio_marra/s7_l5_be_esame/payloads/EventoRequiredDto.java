package giulio_marra.s7_l5_be_esame.payloads;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record EventoRequiredDto(
        @NotEmpty(message = "Inserire un titolo eento")
        String titolo,
        @NotEmpty(message = "Inserire una descrizione evento")
        String descrizione,
        @NotEmpty(message = "Inserire luogo dell evento")
        String luogo,
        @NotEmpty(message = "Inserire posti massimi evento")
        int numero_posti,
        @NotEmpty(message = "Inserire una data per l'evento")
        LocalDate data_evento
) {
}

