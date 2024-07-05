package giulio_marra.s7_l5_be_esame.services;

import giulio_marra.s7_l5_be_esame.entities.Utente;
import giulio_marra.s7_l5_be_esame.excepitions.UnauthorizedException;
import giulio_marra.s7_l5_be_esame.payloads.UtenteLoginDto;
import giulio_marra.s7_l5_be_esame.security.JwtTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {
    @Autowired
    private UtenteServices utenteServices;

    @Autowired
    private JwtTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String generateToken(UtenteLoginDto uld) {
        Utente utente = utenteServices.findByEmail(uld.email());

        if (bcrypt.matches(uld.password(), utente.getPassword())) {
            return jwtTools.createToken(utente);
        } else {
            throw new UnauthorizedException("Credenziali errate");
        }
    }
}
