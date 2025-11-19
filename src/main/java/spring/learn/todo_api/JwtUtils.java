package spring.learn.todo_api;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;

@Component
public class JwtUtils {

    private String jwtSecret = "LimitlessLearningIsFunAndSecureWithLongKeysLikeThisOne1234567890";
    private int jwtExpirationMs = 86400000;

    // Secure key object for signing
    // Decode turns the jwtSecret into raw bytes
    // Then hmacShaKeyFor turns it into a cryptographic key for HS256 signing
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String generateJwtToken(Authentication authentication) {
        // Get user details from authentication object
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        // Build the token HEADER.PAYLOAD.SIGNATURE
        return Jwts.builder() // Creates a new JWT
                .setSubject(userPrincipal.getUsername()) // Put the username in the main info about the token
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                // Ensures nobody can modify the token
                // Uses SHA-256 hashing
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact(); // Converts final JWT to string
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key())
                    .build().parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            System.out.println("Invalid JWT: " + e.getMessage());
            return false;
        }
    }
}
