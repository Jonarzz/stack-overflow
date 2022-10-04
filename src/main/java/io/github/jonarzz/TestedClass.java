package io.github.jonarzz;

import java.util.*;

public class TestedClass {

    private long tokenValidity = 100;
    private String jwtSecret = "jwtSecret";

    public String generateToken(String id) {
        Claims claims = Jwts.claims().setSubject(id);
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + tokenValidity;
        Date exp = new Date(expMillis);

        return Jwts.builder()
                   .setClaims(claims)
                   .setIssuedAt(new Date(nowMillis))
                   .setExpiration(exp)
                   .signWith(SignatureAlgorithm.HS512, jwtSecret)
                   .compact();
    }

}
