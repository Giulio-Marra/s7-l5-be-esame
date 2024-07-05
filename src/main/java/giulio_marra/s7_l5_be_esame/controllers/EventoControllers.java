package giulio_marra.s7_l5_be_esame.controllers;

import giulio_marra.s7_l5_be_esame.entities.Evento;
import giulio_marra.s7_l5_be_esame.entities.Utente;
import giulio_marra.s7_l5_be_esame.payloads.EventoRequiredDto;
import giulio_marra.s7_l5_be_esame.payloads.NewEventoResponseDto;
import giulio_marra.s7_l5_be_esame.services.EventoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventi")
public class EventoControllers {
    @Autowired
    EventoServices eventoServices;

    @PostMapping("/me")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    NewEventoResponseDto createEvento(@RequestBody EventoRequiredDto body, @AuthenticationPrincipal Utente organizzatore) {
        return new NewEventoResponseDto(eventoServices.saveEvento(body, organizzatore).getId());
    }

    @PutMapping("/me/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    Evento updateEvento(@PathVariable Long id, @RequestBody EventoRequiredDto body, @AuthenticationPrincipal Utente organizzatore) {
        return eventoServices.updateEvento(id, body, organizzatore);
    }

    @DeleteMapping("/me/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    void deleteEvento(@PathVariable Long id) {
        eventoServices.deleteEvento(id);
    }
}
