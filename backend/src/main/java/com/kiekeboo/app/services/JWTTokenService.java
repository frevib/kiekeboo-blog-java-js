package com.kiekeboo.app.services;

import com.kiekeboo.app.dao.AuthenticationDAO;
import com.kiekeboo.app.model.UserDataModel;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.tags.EscapeBodyTag;

public class JWTTokenService {

    private AuthenticationDAO authenticationDAO;
    private final Logger logger = LoggerFactory.getLogger(JWTTokenService.class);
    private String key;

    @Autowired
    public JWTTokenService(AuthenticationDAO authenticationDAO) {
        this.authenticationDAO = authenticationDAO;
        this.key = "aGVsbG8=";
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNewToken(UserDataModel user) throws JwtException {
        return createToken(user);
    }

    public void isValidToken(String jwt) throws JwtException {
        if(key == null) {
            throw new JwtException("No key set");
        }
        try {
            Jwts.parser().setSigningKey(key).parse(jwt);
        } catch (SignatureException e) {
            throw new SignatureException("JWT signature not OK");
        }
    }

    private String createToken(UserDataModel user) throws JwtException {
//        Key key = MacProvider.generateKey();
//        TODO: change key to user specific key! This key is just here to test the authorizationFilter.
//        For now use static key "hello" in b64.
//        String b64key = "aGVsbG8=";
        String jwt = "";
        try {
            jwt = Jwts.builder()
                    .setSubject(user.getUsername() + user.getRole_id())
//                    "aGVsbG8=" is base64 encoded "hello"
                    .signWith(SignatureAlgorithm.HS512, key)
                    .compact();
        } catch (Exception e) {
            logger.warn("Could not generate Key");
            return null;
        }
        try {
//            TODO: add key to database. For now just use static key
//            authenticationDAO.addKeytoDatabase(PasswordService.bytesToHex(key.getEncoded()));
            logger.info("Added JWT HMAC key to database");
        } catch (Exception e) {
            logger.warn("Could not add JWT HMAC key to database");
            return null;
        }
//        TODO: encrypt jwt with AES-GCM and the Key
        return jwt;
    }

//    public String encryptToken(String unencryptedToken) {
////        get encryption key from xml. Use one key for all encryption for now
//
//
//        return encryptedToken;
//    }

}
