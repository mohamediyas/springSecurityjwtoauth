package com.Elaki.SpringSecurity.jwt;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.List;

public class JwtUtil {

    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long JWT_TOKEN_VALIDITY = 3600000;

    // generate JWT token
    public static String generateToken(String username, List<String> roles) {
        return Jwts.builder().setSubject(username).claim("roles", roles).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY)).signWith(KEY).compact();
    }

     //validate token
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

//    extract username from token

    public static String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody().getSubject();
    }

//    exact role from token
    public static List<String> extractRoles(String token) {
        return (List<String>) Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody().get("roles", List.class);
    }

}
