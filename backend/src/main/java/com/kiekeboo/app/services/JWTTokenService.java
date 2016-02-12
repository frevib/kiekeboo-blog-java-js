package com.kiekeboo.app.services;

import com.kiekeboo.app.model.UserDataModel;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;


public class JWTTokenService {

    private final Logger logger = LoggerFactory.getLogger(JWTTokenService.class);

    @Value("${jwt.password}")
    private String password;

    // Token expiration time in milliseconds
    @Value("${jwt.expirytime}")
    private long ttlMillis;

    @Value("${jwt.refreshtime}")
    private long refreshtime;

    public String getNewToken(UserDataModel user) throws JwtException {
        return createNewToken(user);
    }

    /**
     * Check if token syntax is valid, and if HMAC is valid. If token expiration is < refreshtime, return new token.
     *
     * @param jwt
     * @return String containing Json web token
     * @throws JwtException
     */
    public String isValidToken(String jwt) throws JwtException {
        if(password == null) {
            throw new JwtException("No key set");
        }

        // TODO:
        // Check if JWT is signed
        if(!Jwts.parser().isSigned(jwt)) {
            throw new SignatureException("Not a signed JWT");
        }
        JwtParser jwtParser = Jwts.parser().setSigningKey(password);

        // parse token, throws exception expiry date is due
        jwtParser.parse(jwt);

        // check if token is expiry time < refresh time. If true, generate new token with new expiration time.
        long expiryTime = jwtParser.parseClaimsJws(jwt).getBody().getExpiration().getTime();
        if(expiryTime - refreshtime < System.currentTimeMillis()) {
            UserDataModel userDataModel = new UserDataModel();
            userDataModel.setUsername(jwtParser.parseClaimsJws(jwt).getBody().getSubject());
            logger.info("Subject: {}", jwtParser.parseClaimsJws(jwt).getBody().getSubject());
            return createNewToken(userDataModel);
        }
        logger.info("JWT sent by user is checked and valid");
        return null;
    }

    /**
     * Create a new Json web token and return this as a String.
     *
     * @param user
     * @return String containing Json web token
     * @throws JwtException
     */
    private String createNewToken(UserDataModel user) throws JwtException {
        String jwt;
        try {
            jwt = Jwts.builder()
            // TODO: add role id to the JWT somehow
                    .setSubject(user.getUsername())
                    .setExpiration(new Date(System.currentTimeMillis() + ttlMillis))
                    .signWith(SignatureAlgorithm.HS512, password)
                    .compact();
            logger.info("Created JWT with expiry date: {}", Jwts.parser().setSigningKey(password).parseClaimsJws(jwt).getBody().getExpiration());
        } catch (Exception e) {
            logger.warn("Could not generate Key");
            return null;
        }
        // TODO: encrypt jwt with AES-GCM and the Key
        return jwt;
    }


}
