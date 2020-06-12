package com.bowtie.ws.Security;

import com.bowtie.ws.Entity.UserAccount;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    @Value("{auth.jwt.tokenSecret}")
    private String tokenSecret;

    public UserAccount validate(String token){

        UserAccount user = null;

        Claims body = Jwts.parser()
                .setSigningKey(tokenSecret)
                .parseClaimsJws(token)
                .getBody();

        user = new UserAccount();

        user.setUsername(body.getSubject());
        user.setId(Long.parseLong((String) body.get("userId")));

        return user;
    }


}
