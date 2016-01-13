package com.kiekeboo.app.filter;

import com.kiekeboo.app.services.JWTTokenService;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Pattern;

public class AuthorizationFilter implements Filter {

    private JWTTokenService jwtTokenService;
    private final Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

    @Autowired
    AuthorizationFilter(JWTTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String tokenFromRequest = getToken(req);
        try {
//            Check if token HMAC is valid. Throws SignatureException if not valid
            jwtTokenService.isValidToken(tokenFromRequest);
            logger.info("Authorization successful!! token value: {}", tokenFromRequest);
            chain.doFilter(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
            throw new ServletException("Servlet exception in doFilter");
        } catch (SignatureException e) {
            throw new SignatureException("Unauthorized, JWT Signature not valid");
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    private String getToken(HttpServletRequest request) throws ServletException {
        final String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader == null) {
            throw new ServletException("Unauthorized, no Authorization header present.");
        }

        String[] parts = authorizationHeader.split(" ");
        if(parts.length != 2) {
            throw new ServletException("Unauthorized, not a valid format header. Must be Authorization: Bearer [token]");
        }

        String scheme = parts[0];
        Pattern pattern = Pattern.compile("^Bearer$", Pattern.CASE_INSENSITIVE);
        if(pattern.matcher(scheme).matches()) {
            return new String(parts[1]);
        }

        throw new ServletException("Unauthorized, error with token processing");
    }

}
