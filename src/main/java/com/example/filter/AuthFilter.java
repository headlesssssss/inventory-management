package com.example.filter;

import com.example.model.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        // Allow static resources and login page
        if (path.startsWith("/login") || path.endsWith(".css") || path.endsWith(".js")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpRequest.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        if (!isLoggedIn) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("user");
        String role = user.getRole();

        // Admin restricted paths
        boolean isUserManagementPath = path.startsWith("/users") || 
                                       path.startsWith("/user-form") || 
                                       path.startsWith("/create-user") || 
                                       path.startsWith("/update-user") || 
                                       path.startsWith("/delete-user");

        if (isUserManagementPath && !"ADMIN".equals(role)) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Accès refusé. Réservé aux administrateurs.");
            return;
        }

        // Proceed
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup if needed
    }
}
