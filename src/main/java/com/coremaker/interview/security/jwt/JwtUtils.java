package com.coremaker.interview.security.jwt;

import com.coremaker.interview.security.services.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

  private String jwtSecret = "jwtSecretDummy";
  private String username;

  public String generateJwtToken(Authentication authentication) {
    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
    username = userPrincipal.getUsername();
    return username + jwtSecret;

    // I had to mock the below part because of the following exception:
    // java.lang.ClassNotFoundException: javax.xml.bind.DatatypeConverter

    //    return Jwts.builder()
    //        .setSubject((userPrincipal.getUsername()))
    //        .setIssuedAt(new Date())
    //        .signWith(SignatureAlgorithm.HS512, jwtSecret)
    //        .compact();
  }

  public boolean validateJwtToken(String authToken) {
    //    try {
    //      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
    //      return true;
    //    } catch (SignatureException e) {
    //      System.out.println("Invalid JWT signature: {}" + e.getMessage());
    //    } catch (MalformedJwtException e) {
    //      System.out.println("Invalid JWT token: {}" + e.getMessage());
    //    } catch (ExpiredJwtException e) {
    //      System.out.println("JWT token is expired: {}" + e.getMessage());
    //    } catch (UnsupportedJwtException e) {
    //      System.out.println("JWT token is unsupported: {}" + e.getMessage());
    //    } catch (IllegalArgumentException e) {
    //      System.out.println("JWT claims string is empty: {}" + e.getMessage());
    //    }
    String bearerToken = "Bearer " + username + jwtSecret;
    if (authToken.equals(bearerToken)) {
      return true;
    }
    return false;
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }
}
