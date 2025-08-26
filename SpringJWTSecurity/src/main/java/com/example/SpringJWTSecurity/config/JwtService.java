package com.example.SpringJWTSecurity.config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    private   final String SECRET_KEY="wUWYIs0IRQL74/AB1h+ujRRZrRNSiTKyXm4kgJnQE5+qPtdGIw3iYlFL5FWMAU2TChmgMWFb844xhonV/bHdTA==";
    public  String extractUsername(String token) {
        return extractClaims(token,Claims::getSubject);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getsignInKey()).build()
                .parseSignedClaims(token).getPayload();
    }
    public <T> T extractClaims(String token,
                               Function<Claims,
            T>claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private  Key getsignInKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateToken(UserDetails userDetails){
        return generatToekn(new HashMap<>(), userDetails);
    }
    public String generatToekn(
            Map<String, Object> extraClaims, UserDetails userDetails
    ){
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000* 60 * 60 *10))
                .signWith(SignatureAlgorithm.HS256,getsignInKey())
                .compact();

    }
    public boolean isTokenValid(String token , UserDetails userDetails){
        final String userName= extractUsername(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token,Claims::getExpiration);
    }
}
