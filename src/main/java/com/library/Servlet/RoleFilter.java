package com.library.Servlet;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class RoleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        String role = (session != null) ? (String) session.getAttribute("role") : null;
        String path = request.getRequestURI();

        if (path.startsWith("/adminSection") && !"admin".equals(role)) {
            response.sendRedirect("/");
        } else if (path.startsWith("/workerSection") && !"worker".equals(role)) {
            response.sendRedirect("/");
        } else if (path.startsWith("/readerSection") && !"reader".equals(role)) {
            response.sendRedirect("/");
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
