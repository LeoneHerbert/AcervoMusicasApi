package br.com.israel.acervomusicasapi.config.security;

import br.com.israel.acervomusicasapi.models.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${playlist.jwt.expiration}")
    private String tempoExpiracao;

    @Value("${playlist.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authenticate) {
        Usuario logado = (Usuario) authenticate.getPrincipal();
        Date atual = new Date();
        Date dataExpiracao = new Date(atual.getTime() + Long.parseLong(this.tempoExpiracao));

        return Jwts.builder()
                .setIssuer("API de Playlist")
                .setSubject(logado.getId().toString())
                .setIssuedAt(atual)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Integer getIdUsuario(String token) {
        Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Integer.parseInt(body.getSubject());
    }

    public String retornarTokenSemBearer(String token) {
        return token.substring(7, token.length());
    }
}
