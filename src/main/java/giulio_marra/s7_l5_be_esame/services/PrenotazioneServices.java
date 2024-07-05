package giulio_marra.s7_l5_be_esame.services;

import giulio_marra.s7_l5_be_esame.entities.Evento;
import giulio_marra.s7_l5_be_esame.entities.Prenotazione;
import giulio_marra.s7_l5_be_esame.entities.Utente;
import giulio_marra.s7_l5_be_esame.excepitions.BadRequestException;
import giulio_marra.s7_l5_be_esame.excepitions.NotFoundException;
import giulio_marra.s7_l5_be_esame.repositories.PrenotazioneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrenotazioneServices {
    @Autowired
    PrenotazioneRepo prenotazioneRepo;

    public Prenotazione savePrenotazione(Evento evento, Utente utente) {
        if (prenotazioneRepo.existsByEventoAndUtente(evento, utente)) {
            throw new BadRequestException("Sei gia prenotato per questo evento");
        }

        List<Prenotazione> prenotazioniEvento = getPrenotazioniByEvento(evento);
        int numeroPrenotazioniAttuali = prenotazioniEvento.size();

        if (numeroPrenotazioniAttuali >= evento.getNumero_posti()) {
            throw new BadRequestException("Prenotazioni al completo per questo evento");
        }

        Prenotazione prenotazione = new Prenotazione(evento, utente);

        return prenotazioneRepo.save(prenotazione);
    }

    public Prenotazione getPrenotazione(long id) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepo.findById(id);

        if (optionalPrenotazione.isPresent()) {
            return optionalPrenotazione.get();
        } else {
            throw new NotFoundException("Prenotazione con questo id non trovata");
        }
    }

    public void deletePrenotazione(Long id) {
        Prenotazione prenotazione = getPrenotazione(id);
        prenotazioneRepo.delete(prenotazione);
    }

    public List<Prenotazione> getPrenotazioniByEvento(Evento evento) {
        return prenotazioneRepo.findByEvento(evento);
    }

}
