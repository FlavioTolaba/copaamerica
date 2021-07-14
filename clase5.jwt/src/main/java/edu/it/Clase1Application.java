package edu.it;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.util.HashMap;

public class Clase1Application {
    public static void main(String[] args) {
        String token = null;

        try {
            HashMap<String, String> mapa = new HashMap<>();

            mapa.put("suscriber", "1115351354454");
            mapa.put("roles", "ROLE_USER;ROLE_ADMIN");

            Algorithm algorithm = Algorithm.HMAC256("secret");
            token = JWT.create()
                .withIssuer("copaAmerica")
                .withPayload(mapa)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000))
                .sign(algorithm);

            System.out.println(token);

            } catch (JWTCreationException ex){
                System.out.println(ex.getMessage());
            }

            try { Thread.sleep(500); } catch (Exception ex) {}

            try {
                Algorithm algorithm = Algorithm.HMAC256("secret");
                JWTVerifier verifier = JWT.require(algorithm)
                    // .withIssuer("auth0")
                    .build(); //Reusable verifier instance
                    DecodedJWT jwt = verifier.verify(token);

                    System.out.println(jwt.getPayload());

                    Claim roles = jwt.getClaim("roles");
                    System.out.println(roles.asString());
                    Date fecha = jwt.getExpiresAt();
                    System.out.println(fecha);
                    Claim suscriber = jwt.getClaim("suscriber");
                    System.out.println(suscriber.asString());
            }
            catch (JWTVerificationException ex){
                System.out.println(ex.getMessage());
            }
    }
}