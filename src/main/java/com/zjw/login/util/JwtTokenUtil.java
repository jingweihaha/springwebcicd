package com.zjw.login.util;

import com.zjw.login.service.MyUserDetailServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author jingw
 * @created 10/21/2021 11:35 PM
 * @project login
 */
@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        String token = Jwts.builder().setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +  10*60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        return token;
    }

    public String getUsernameFromToken(String token) {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return body.getSubject();
    }

    public Date getExpirationDateFromToken(String token){
        Date expiration = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
        return expiration;
    }

    public boolean isTokenExpired(String token){
        Date expirationDateFromToken = getExpirationDateFromToken(token);
        return expirationDateFromToken.before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails){
        String username = getUsernameFromToken(token);
        userDetails.getUsername();
        return userDetails.getUsername().equals(username) && !isTokenExpired(token);
    }


}
