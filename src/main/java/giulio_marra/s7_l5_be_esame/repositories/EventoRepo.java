package giulio_marra.s7_l5_be_esame.repositories;

import giulio_marra.s7_l5_be_esame.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepo extends JpaRepository<Evento, Long> {
    boolean existsByDescrizione(String descrizione);
}
