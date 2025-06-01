package com.example.jwt.configuration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.jwt.service.JwtService;
import com.example.jwt.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtService jwtService;


    /**
     * Intercepts and processes HTTP requests for JWT authentication.
     *
     * This filter performs the following operations:
     * 1. Extracts the JWT token from the Authorization header
     * 2. Validates the token and authenticates the user
     * 3. Sets up the security context if authentication is successful
     *
     * Authentication Flow:
     * - Checks for "Authorization" header with "Bearer" prefix
     * - Extracts and validates the JWT token
     * - Loads user details if token is valid
     * - Establishes security context for the authenticated user
     *
     * @param request The HTTP request object
     * @param response The HTTP response object
     * @param filterChain The filter chain for additional processing
     *
     * @throws ServletException If there is an error in the servlet processing
     * @throws IOException If there is an I/O error during processing
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

      final String header = request.getHeader("Authorization");

      String jwtToken = null;
      String userName = null;
      if (header != null && header.startsWith("Bearer ")) {
        jwtToken = header.substring(7);

        try {
          // Validate the JWT token
           userName = jwtUtil.getUserNameFromToken(jwtToken);
        } catch (IllegalArgumentException e) {
            System.out.println("Unable to get JWT token");
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired");
        }

      } else {
          System.out.println("Jwt token does not start with Bearer");
      }

      if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = jwtService.loadUserByUsername(userName);
            if(jwtUtil.validateToken(jwtToken, userDetails)){

               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                                                                                null,
                                                                                          userDetails.getAuthorities());

               usernamePasswordAuthenticationToken.setDetails(request);

               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
      }

      filterChain.doFilter(request, response);

    }
}
