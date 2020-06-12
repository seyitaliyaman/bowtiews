package com.bowtie.ws.Security;

import com.bowtie.ws.Entity.User;
import com.bowtie.ws.Entity.UserAccount;
import com.bowtie.ws.Service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtGenerator {

    @Value("{auth.jwt.tokenSecret}")
    private String tokenSecret;

    @Autowired
    private UserService userService;

    public String generate(UserAccount userAccount){
        Claims claims = Jwts.claims()
                .setSubject(userAccount.getUsername());

        User user = userService.getUserByAccount(userAccount);

        claims.put("userId",user.getId());

        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512,tokenSecret)
                .compact();

        return token;
    }
}
