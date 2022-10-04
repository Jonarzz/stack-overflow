package io.github.jonarzz;

import java.util.*;

public class Jwts {

    static Claims claims() {
        return new Claims();
    }

    static JwtsBuilder builder() {
        return new JwtsBuilder();
    }

    static class JwtsBuilder {

        JwtsBuilder setClaims(Claims claims) {
            return this;
        }

        JwtsBuilder setIssuedAt(Date date) {
            return this;
        }

        JwtsBuilder setExpiration(Date exp) {
            return this;
        }

        JwtsBuilder signWith(String hs512, String jwtSecret) {
            return this;
        }

        String compact() {
            return "";
        }
    }
}
