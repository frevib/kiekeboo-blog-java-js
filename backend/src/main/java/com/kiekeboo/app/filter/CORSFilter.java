package com.kiekeboo.app.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(CORSFilter.class);

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        try {
//        TODO: CORS now accepts * == all
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Max-Age", "3600");
//        Preflight CORS
            response.setHeader("Access-Control-Allow-Methods", "GET, POST");
            response.setHeader("Access-Control-Allow-Headers", "accept, access-control-allow-headers, Authorization, content-type");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            chain.doFilter(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("Exception in CORSFilter");
        }
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}