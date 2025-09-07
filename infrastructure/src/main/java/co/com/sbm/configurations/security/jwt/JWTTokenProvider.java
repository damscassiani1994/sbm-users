package co.com.sbm.configurations.security.jwt;


import co.com.sbm.configurations.security.interfaces.IJWTProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

/**
 * JWTTokenProvider is responsible for generating and validating JWT tokens.
 * It uses a secure random secret key for signing the tokens and provides methods
 * to extract the username from the token and validate the token's integrity.
 */
@Component
public class JWTTokenProvider implements IJWTProvider {

    private String jwtSecret = generateSecretKey();

    private long jwtExpirationDate = 3600000;

    @Override
    public String tokenGenerate(Authentication authentication) {

        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    private Key getKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // extract username from JWT token
    @Override
    public String getUsername(String token){

        return Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // validate JWT token
    @Override
    public boolean validateToken(String token){
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) getKey())
                    .build()
                    .parse(token);
        } catch (SignatureException ex) {
            System.out.println("Invalid JWT signature - " + ex.getMessage());
            return false;
        }
        return true;
    }

    // Method to generate a secure random secret key for JWT signing
    private String generateSecretKey() {
        int length = 32;
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[length];
        secureRandom.nextBytes(keyBytes);
        return Base64.getEncoder().encodeToString(keyBytes);
    }


}
