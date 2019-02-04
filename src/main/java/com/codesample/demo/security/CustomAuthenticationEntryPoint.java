package com.codesample.demo.security;

import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        try {
            httpServletResponse.setStatus(401);
            httpServletResponse.getWriter().append("Not authorized")
                    .close();
        } catch (Exception ex) {
            throw new ServletException();
        }
    }
}
