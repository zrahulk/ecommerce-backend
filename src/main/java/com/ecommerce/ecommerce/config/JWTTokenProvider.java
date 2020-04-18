package com.ecommerce.ecommerce.config;

import com.ecommerce.ecommerce.constants.SecurityConstants;
import com.ecommerce.ecommerce.pojo.Role;
import com.ecommerce.ecommerce.pojo.User;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class JWTTokenProvider {

    //To Generate Token
    public String generateToken(Authentication authentication) {
        User user = (User)authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + SecurityConstants.EXPIRATION_TIME);
        String userId = Long.toString(user.getUserId());
        String roles =  user.getRoles().toString();
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("email",user.getEmail());
        claims.put("fullName",user.getName());
        claims.put("roles",roles);

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,SecurityConstants.SECRET)
                .compact();
    }

    //To Validate Token

    public boolean validateToken(String token)
    {
        try {
            Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token);
            return true;
        }catch (SignatureException e)  {
            System.out.println("Invalid JWT Signature");
        }catch (MalformedJwtException me) {
            System.out.println("Invalid JWT Token");
        }catch (ExpiredJwtException eje) {
            System.out.println("Token has Expired");
        }catch (UnsupportedJwtException uje) {
            System.out.println("Unsupported JWT Token");
        }catch (IllegalArgumentException iae) {
            System.out.println("Illegal arguments");
        }
        return false;
    }

    //getUserId from Token

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                            .setSigningKey(SecurityConstants.SECRET)
                            .parseClaimsJws(token)
                            .getBody();
        String userId = (String) claims.get("userId");
        System.out.println("roles is " + claims.get("roles"));
        return Long.parseLong(userId);
    }


}
