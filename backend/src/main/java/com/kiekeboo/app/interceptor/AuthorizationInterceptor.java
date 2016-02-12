package com.kiekeboo.app.interceptor;

import com.kiekeboo.app.services.JWTTokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);
    private JWTTokenService jwtTokenService;

    @Autowired
    public AuthorizationInterceptor(JWTTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    /**
     * Check if user is authenticated, by parsing the JsonWebToken (JWT). The JWT's HMAC is validated and the expiry time (timestamp) is checked.
     * If the HMAC is not valid, the user is denied access to the application.
     * If the expiry timestamp > current timestamp, the user is denied access to the application.
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws JwtException (and its subclasses)
     * @throws IOException
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws JwtException, IOException {
        if(request.getMethod().equalsIgnoreCase("OPTIONS")) {
            logger.info("Request method == OPTIONS");
            return true;
        }
        try {
            String tokenFromRequest = getToken(request);

            // Check if token is valid. Throws SignatureException, ExpiredJwtException or JwtException if not valid
            String jwt = jwtTokenService.isValidToken(tokenFromRequest);

            // TODO: change name of TokenRefresher. Should be just "Token".
            // If jwt != null the refresh token is added as response header
            if(jwt != null) {
                response.addHeader("TokenRefresher", jwt);
            }
            return true;

        } catch (SignatureException e) {
            logger.warn("Unauthorized: " + e.getMessage());
            // TODO: catch IOException from createJSONResponse --- not being caught at the moment!!
            String message = "{\"message\":\"Invalid Autorization token!\"}";
            createJSONResponse(response, message);
            return false;
        } catch (ExpiredJwtException e) {
            logger.info("Unauthorized: "+ e.getMessage());
            String message = "{\"message\":\"Invalid Autorization, token expired!\"}";
            createJSONResponse(response, message);
            return false;
        } catch (JwtException e) {
            logger.warn("Unauthorized: " + e.getMessage());
            String message ="{\"message\":\"Invalid Autorization token!\"}";
            createJSONResponse(response, message);
            return false;
        }
    }

    private void createJSONResponse(HttpServletResponse response, String message) throws IOException {
        response.getWriter().write(message);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(401);
        response.addHeader("Content-Type", "application/json");
    }

    // Get JsonWebToken from HTTP header.
    private String getToken(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader == null) {
            throw new JwtException("Unauthorized, no Authorization header present.");
        }
        String[] parts = authorizationHeader.split(" ");
        if(parts.length != 2) {
            throw new JwtException("Unauthorized, not a valid format header. Must be Authorization: Bearer [token]");
        }
        String scheme = parts[0];
        Pattern pattern = Pattern.compile("^Bearer$", Pattern.CASE_INSENSITIVE);
        if(pattern.matcher(scheme).matches()) {
            return new String(parts[1]);
        }
        throw new JwtException("Unauthorized, error with processing of token");
    }
}
