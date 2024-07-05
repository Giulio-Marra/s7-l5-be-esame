package giulio_marra.s7_l5_be_esame.controllers;

import giulio_marra.s7_l5_be_esame.entities.Evento;
import giulio_marra.s7_l5_be_esame.entities.Utente;
import giulio_marra.s7_l5_be_esame.payloads.EventoRequiredDto;
import giulio_marra.s7_l5_be_esame.payloads.NewEventoResponseDto;
import giulio_marra.s7_l5_be_esame.payloads.NewPrenotazioneResponseDto;
import giulio_marra.s7_l5_be_esame.services.EventoServices;
import giulio_marra.s7_l5_be_esame.services.PrenotazioneServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventi")
public class EventoControllers {
    @Autowired
    EventoServices eventoServices;

    @Autowired
    PrenotazioneServices prenotazioneServices;

    @PostMapping("/me")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.CREATED)
    NewEventoResponseDto createEvento(@RequestBody EventoRequiredDto body, @AuthenticationPrincipal Utente organizzatore) {
        return new NewEventoResponseDto(eventoServices.saveEvento(body, organizzatore).getId());
    }

    @PutMapping("/me/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.OK)
    Evento updateEvento(@PathVariable Long id, @RequestBody EventoRequiredDto body, @AuthenticationPrincipal Utente organizzatore) {
        return eventoServices.updateEvento(id, body, organizzatore);
    }

    @DeleteMapping("/me/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvento(@PathVariable Long id) {
        eventoServices.deleteEvento(id);
    }

    @PostMapping("/me/participation/{id_evento}")
    @ResponseStatus(HttpStatus.CREATED)
    NewPrenotazioneResponseDto joinParticipation(@PathVariable("id_evento") Evento evento, @AuthenticationPrincipal Utente utente) {
        return new NewPrenotazioneResponseDto(prenotazioneServices.savePrenotazione(evento, utente).getId());
    }

    @DeleteMapping("/me/participation/{id_evento}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletPartecipation(@PathVariable Long id_evento) {
        prenotazioneServices.deletePrenotazione(id_evento);
    }
}
