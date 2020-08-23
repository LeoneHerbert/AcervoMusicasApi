package br.com.israel.acervomusicasapi.controller;

import br.com.israel.acervomusicasapi.config.security.TokenService;
import br.com.israel.acervomusicasapi.controller.dto.TokenDto;
import br.com.israel.acervomusicasapi.controller.form.Loginform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid Loginform form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();

        try{
            Authentication authenticate = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authenticate);

            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        }catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("A autenticação falhou. Por favor, verifique seus dados!");
        }
    }
}
