package giulio_marra.s7_l5_be_esame.repositories;

import giulio_marra.s7_l5_be_esame.entities.Evento;
import giulio_marra.s7_l5_be_esame.entities.Prenotazione;
import giulio_marra.s7_l5_be_esame.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrenotazioneRepo extends JpaRepository<Prenotazione, Long> {
    boolean existsByEventoAndUtente(Evento evento, Utente utente);

    List<Prenotazione> findByEvento(Evento evento);
}
