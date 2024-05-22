package app.back.real.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class CustomFilter extends GenericFilterBean {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Verificar si la solicitud tiene encabezado de autenticación
        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            // Si no hay encabezado de autenticación o no es autenticación básica, enviar respuesta de error 401 (Unauthorized)
            httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpResponse.getWriter().write("ERROR - Invalid authentication");
            return;
        }

        // Si se proporcionan credenciales de autenticación básica, continuar con la cadena de filtros
        chain.doFilter(request, response);
    }
}
