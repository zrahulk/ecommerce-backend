package com.ecommerce.ecommerce.config;

import com.ecommerce.ecommerce.constants.SecurityConstants;
import com.ecommerce.ecommerce.dao.RoleRepo;
import com.ecommerce.ecommerce.pojo.CustomUserDetails;
import com.ecommerce.ecommerce.pojo.Role;
import com.ecommerce.ecommerce.pojo.User;
import com.ecommerce.ecommerce.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    CustomUserDetailsService customUserDetailsService;





    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwtToken = getJwtFromRequest(request);
            System.out.println(jwtToken);
            if(StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) {

                Long userId = jwtTokenProvider.getUserIdFromToken(jwtToken);
                System.out.println(userId);
                CustomUserDetails userDetails =  customUserDetailsService.loadUserById(userId);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                        userDetails, null,userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }catch (Exception e) {
            System.out.println("Exception in JWTAuthenticationFilter class");
        }

        filterChain.doFilter(request,response);

    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String e_commerceToken = request.getHeader(SecurityConstants.HEADER_STRING);
        if(StringUtils.hasText(e_commerceToken) &&
                e_commerceToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            return e_commerceToken.substring(SecurityConstants.TOKEN_PREFIX.length(),
                                    e_commerceToken.length());
        }
        return null;
    }
}
