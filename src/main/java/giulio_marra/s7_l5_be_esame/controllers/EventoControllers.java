package giulio_marra.s7_l5_be_esame.controllers;

import giulio_marra.s7_l5_be_esame.payloads.EventoRequiredDto;
import giulio_marra.s7_l5_be_esame.payloads.NewEventoResponseDto;
import giulio_marra.s7_l5_be_esame.services.EventoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eventi")
public class EventoControllers {
    @Autowired
    EventoServices eventoServices;

    @PostMapping("/me")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    NewEventoResponseDto createEvento(@RequestBody EventoRequiredDto body) {
        return new NewEventoResponseDto(eventoServices.saveEvento(body).getId());
    }
}
