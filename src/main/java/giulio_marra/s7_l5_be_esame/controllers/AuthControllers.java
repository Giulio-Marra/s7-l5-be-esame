package giulio_marra.s7_l5_be_esame.controllers;

import giulio_marra.s7_l5_be_esame.excepitions.BadRequest;
import giulio_marra.s7_l5_be_esame.payloads.NewUtenteResponseDto;
import giulio_marra.s7_l5_be_esame.payloads.UtenteLoginDto;
import giulio_marra.s7_l5_be_esame.payloads.UtenteLoginRespnseTokenDto;
import giulio_marra.s7_l5_be_esame.payloads.UtenteRequiredDto;
import giulio_marra.s7_l5_be_esame.services.AuthServices;
import giulio_marra.s7_l5_be_esame.services.UtenteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthControllers {
    @Autowired
    private UtenteServices utenteServices;

    @Autowired
    private AuthServices authServices;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtenteResponseDto saveUser(@RequestBody @Validated UtenteRequiredDto body, BindingResult valResult) {
        if (valResult.hasErrors()) {
            throw new BadRequest(valResult.getAllErrors().toString());
        }
        return new NewUtenteResponseDto(utenteServices.saveUser(body).getId());
    }

    @PostMapping("/login")
    public UtenteLoginRespnseTokenDto login(@RequestBody UtenteLoginDto payload) {
        return new UtenteLoginRespnseTokenDto(authServices.generateToken(payload));
    }

}