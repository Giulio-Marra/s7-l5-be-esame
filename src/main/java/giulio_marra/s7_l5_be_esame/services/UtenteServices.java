package giulio_marra.s7_l5_be_esame.services;

import giulio_marra.s7_l5_be_esame.entities.Utente;
import giulio_marra.s7_l5_be_esame.enums.Ruoli;
import giulio_marra.s7_l5_be_esame.excepitions.BadRequestException;
import giulio_marra.s7_l5_be_esame.excepitions.NotFoundException;
import giulio_marra.s7_l5_be_esame.payloads.UtenteRequiredDto;
import giulio_marra.s7_l5_be_esame.repositories.UtenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteServices {
    @Autowired
    private UtenteRepo utenteRepo;

    @Autowired
    private PasswordEncoder bcrypt;

    public Utente saveUser(UtenteRequiredDto body) {
        if (utenteRepo.existsByEmail(body.email())) {
            throw new BadRequestException("Utente gia esistente");
        }

        Utente utente = new Utente();
        utente.setName(body.name());
        utente.setSurname(body.surname());
        utente.setUsername(body.username());
        utente.setPassword(bcrypt.encode(body.password()));
        utente.setEmail(body.email());
        utente.setRuoli(Ruoli.UTENTE);

        return utenteRepo.save(utente);
    }

    public Utente getUtente(long id) {
        Optional<Utente> optionalUtent = utenteRepo.findById(id);

        if (optionalUtent.isPresent()) {
            return optionalUtent.get();
        } else {
            throw new NotFoundException("Utente con questo id non trovato");
        }
    }

    public Utente findByEmail(String email) {
        return utenteRepo.findByEmail(email).orElseThrow(() -> new NotFoundException("utente con questa email non trovato"));
    }

    public List<Utente> getAllUtenti() {
        return utenteRepo.findAll();
    }
}
