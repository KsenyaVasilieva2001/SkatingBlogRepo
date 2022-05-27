package ru.kpfu.itis.skatingblog.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.skatingblog.models.User;

import javax.annotation.PostConstruct;
import java.security.Key;

@Service
public class JwtTokenProvider {

    private Key key;
    @PostConstruct
    public void init(){
        key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }


    public String generateToken(Authentication authentication){
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getEmail())
                .signWith(key)
                .compact();
    }
}
