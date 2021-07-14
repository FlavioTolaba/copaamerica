package edu.it.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

@Service
public class Filtro extends OncePerRequestFilter {
    Logger logger = LoggerFactory.getLogger(Filtro.class);

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain next) throws ServletException, IOException {
        String posibleToken = null;
        try {
            posibleToken = req.getHeader("Authorization");
            logger.info("PosibleToken: " + posibleToken);
            if (posibleToken == null) {
                throw new RuntimeException("No vino el campo Authorization");
            }
            posibleToken = posibleToken.replace("Bearer ", "");
            logger.info("Token obtenido: ");
            logger.info(posibleToken);
        }
        catch (Exception ex) {
            logger.error(ex.getMessage());
            salirPorForbidden(res);
            return;
        }

        // Idealmente tendriamos que utilizar otro bean que analice
        // la propiedades del token y validarlo contra algun repositorio

        // Ahora viene Authorization

        // Aplicacion de roles
        SecurityContextHolder.clearContext();
        ArrayList<String> authorities = new ArrayList<String>();

        if (posibleToken.equals("TOKEN_COMUN")) {
            authorities.add("ROLE_USER");
        }
        if (posibleToken.equals("TOKEN_GROSO")) {
            authorities.add("ROLE_USER");
            authorities.add("ROLE_ADMIN");
        }

        UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            "NA", null,
                            authorities.stream()
                                    .map(SimpleGrantedAuthority::new)
                                    .collect(Collectors.toList()));

        SecurityContextHolder.getContext().setAuthentication(auth);

        next.doFilter(req, res);
    }
    private void salirPorForbidden(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status " + HttpServletResponse.SC_UNAUTHORIZED + " Usuario no identificado");
    }
}
