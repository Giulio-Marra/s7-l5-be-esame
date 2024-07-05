package giulio_marra.s7_l5_be_esame.services;

import giulio_marra.s7_l5_be_esame.entities.Evento;
import giulio_marra.s7_l5_be_esame.excepitions.BadRequest;
import giulio_marra.s7_l5_be_esame.payloads.EventoRequiredDto;
import giulio_marra.s7_l5_be_esame.repositories.EventoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventoServices {
    @Autowired
    EventoRepo eventoRepo;

    public Evento saveEvento(EventoRequiredDto body) {
        if (eventoRepo.existsByDescrizione(body.descrizione())) {
            throw new BadRequest("Evento gia esistente");
        }
        Evento evento = new Evento();
        evento.setTitolo(body.titolo());
        evento.setDescrizione(body.descrizione());
        evento.setLuogo(body.luogo());
        evento.setData_evento(body.data_evento());
        evento.setNumero_posti(body.numero_posti());

        return eventoRepo.save(evento);
    }

    public Evento getEvento(long id) {
        Optional<Evento> optionaleVENTO = eventoRepo.findById(id);

        if (optionaleVENTO.isPresent()) {
            return optionaleVENTO.get();
        } else {
            throw new BadRequest("Evento con questo id non trovato");
        }
    }

    public Evento updateEvento(Long id, EventoRequiredDto body) {
        Evento evento = getEvento(id);

        evento.setTitolo(body.titolo());
        evento.setDescrizione(body.descrizione());
        evento.setLuogo(body.luogo());
        evento.setData_evento(body.data_evento());
        evento.setNumero_posti(body.numero_posti());

        return eventoRepo.save(evento);
    }

    public void deleteEvento(Long id) {
        Evento evento = getEvento(id);
        eventoRepo.delete(evento);
    }
}
