package com.iiit.quarkus.sample.servlet.resource;

import javax.servlet.*;
import java.io.*;

public class ProjectServletFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        String myParam = request.getParameter("action");
        if(!"blockTheRequest".equals(myParam)){
            filterChain.doFilter(request, response);
            return;
        }
        response.getWriter().write("This request is filtered");
    }

    public void destroy() {
    }
}
