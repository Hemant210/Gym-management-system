package jwtrest;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import java.io.Serializable;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;

import static java.util.stream.Collectors.joining;
import static jwtrest.Constants.REMEMBERME_VALIDITY_SECONDS;

@Named
public class TokenProvider implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(TokenProvider.class.getName());
    private static final String AUTHORITIES_KEY = "auth";

    private String secretKey;
    private String privateKey;
    private String publicKey;
    private PrivateKey myprivateKey;
    private PublicKey mypublicKey;

    private long tokenValidity;
    private long tokenValidityForRememberMe;

    @PostConstruct
    public void init() {
        // Load from config
//        this.secretKey = "my-secret-jwt-key";
        this.secretKey = Base64.getEncoder().encodeToString("your-very-secure-key-which-is-longer-than-32-bytes".getBytes());
        this.tokenValidity = TimeUnit.HOURS.toMillis(10);   // 10 hours
        this.tokenValidityForRememberMe = TimeUnit.SECONDS.toMillis(REMEMBERME_VALIDITY_SECONDS);   // 24 hours

        // Add code here to initialize `myprivateKey` and `mypublicKey` if needed
    }

    public String createToken(String username, Set<String> authorities, Boolean rememberMe) {
        long now = System.currentTimeMillis();
        long validity = rememberMe ? tokenValidityForRememberMe : tokenValidity;

        System.out.println("TokenProvider - In create Token from gym-pro-new");
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("localhost")
                .claim(AUTHORITIES_KEY, authorities.stream().collect(Collectors.joining(",")))
                .signWith(key, SignatureAlgorithm.HS512) // Pass the Key object here
                .setExpiration(new Date(now + validity))
                .compact();
    }

    public JWTCredential getCredential(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(mypublicKey) // Replace with your actual public key
                .build()
                .parseClaimsJws(token)
                .getBody();

        System.out.println("TokenProvider - Token Provider - In Get Credential");

        Set<String> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .collect(Collectors.toSet());

        return new JWTCredential(claims.getSubject(), authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            System.out.println("TokenProvider - TokenProvider - validate token");

            Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes()) // Ensure signing key is converted to byte[]
                    .build()
                    .parseClaimsJws(authToken);

            return true;
        } catch (SignatureException e) {
            LOGGER.log(Level.INFO, "Invalid JWT signature: {0}", e.getMessage());
            return false;
        } catch (ExpiredJwtException e) {
            LOGGER.log(Level.INFO, "Expired JWT token: {0}", e.getMessage());
            return false;
        } catch (MalformedJwtException e) {
            LOGGER.log(Level.INFO, "Malformed JWT token: {0}", e.getMessage());
            return false;
        } catch (UnsupportedJwtException e) {
            LOGGER.log(Level.INFO, "Unsupported JWT token: {0}", e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.INFO, "JWT token compact or handler is invalid: {0}", e.getMessage());
            return false;
        }
    }
}
