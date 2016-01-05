package com.kiekeboo.app.services;

import com.kiekeboo.app.dao.AuthenticationDAO;
import com.kiekeboo.app.model.UserDataModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Key;

public class UserTokenService {

    AuthenticationDAO authenticationDAO;
    private final Logger logger = LoggerFactory.getLogger(UserTokenService.class);

    @Autowired
    UserTokenService(AuthenticationDAO authenticationDAO) {
        this.authenticationDAO = authenticationDAO;
    }

    public String getNewToken(UserDataModel user) throws Exception {
        return createToken(user);
    }

    private String createToken(UserDataModel user) throws Exception {
        Key key = MacProvider.generateKey();
        String jwt = "";
        try {
            jwt = Jwts.builder()
                    .setSubject(user.getUsername() + user.getRole_id())
//                    "aGVsbG8=" is base64 encoded "hello"
                    .signWith(SignatureAlgorithm.HS512, "aGVsbG8=")
                    .compact();
        } catch (Exception e) {
            logger.warn("Could not generate Key");
            return null;
        }
        try {
            authenticationDAO.addKeytoDatabase(PasswordService.bytesToHex(key.getEncoded()));
            logger.info("Added JWT HMAC key to database");
        } catch (Exception e) {
            logger.warn("Could not add JWT HMAC key to database");
            return null;
        }
//        TODO: encrypt jwt with AES GCM and the Key
        return jwt;
    }

}
