package giulio_marra.s7_l5_be_esame.repositories;

import giulio_marra.s7_l5_be_esame.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepo extends JpaRepository<Utente, Long> {

    boolean existsByEmail(String email);

    Optional<Utente> findByEmail(String email);
}
