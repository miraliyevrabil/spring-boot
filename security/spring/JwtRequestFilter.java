package com.rabilmiraliyev.bookstore.security.spring;


import com.rabilmiraliyev.bookstore.model.RequestHistory;
import com.rabilmiraliyev.bookstore.repository.RequestHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class JwtRequestFilter<RequestHitory> extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

     @Autowired
     private RequestHistoryRepository requestHistoryRepository;

      RequestHistory requestHistory;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("AUTH-TOKEN");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null) {
            jwt = authorizationHeader;
            username = jwtUtil.extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                 //additional
                requestHistory=new RequestHistory();
                requestHistory.setUsername(userDetails.getUsername());
                requestHistory.setRequestUri(request.getRequestURI());
                requestHistory.setDate(new Date());
                requestHistory.setMethod(request.getMethod());
                requestHistory.setClientIp(getClientIP(request));
                requestHistoryRepository.save(requestHistory);

            }
        }
        chain.doFilter(request, response);
    }

    private String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

}