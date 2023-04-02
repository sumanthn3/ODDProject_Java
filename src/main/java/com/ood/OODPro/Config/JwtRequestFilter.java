package com.ood.OODPro.Config;



import com.ood.OODPro.Constants.Constants;
import com.ood.OODPro.Services.UserDetailService;
import com.ood.OODPro.Utils.JwtTokenUtil;
import com.ood.OODPro.pojo.UserDetailsPojo;
import io.jsonwebtoken.ExpiredJwtException;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter
{

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailService jwtUserDetailsService;



    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        String jwtToken = null;
        String username = null;

        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);

            } catch (IllegalArgumentException e) {
                log.error("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                log.error("JWT Token has expired");
            }
        }  //            log.warn("JWT Token does not begin with Bearer String");

        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            UserDetailsPojo userDetailsPojo = this.jwtUserDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(jwtToken, userDetailsPojo))
            {
                System.out.println("--------------validated--------------"+ userDetailsPojo);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        userDetailsPojo, null
                        ,userDetailsPojo.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                auth.setAuthenticated(true);
                SecurityContextHolder.getContext().setAuthentication(auth);
                request.setAttribute(Constants.USER_OBJECT_STRING, userDetailsPojo);
//                System.out.println("--------------validated-out-------------");
            }
        }
        chain.doFilter(request, response);
    }
}
