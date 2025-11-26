package com.cybersoft.qlsv.config;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Lớp này áp dụng cho toàn bộ controller trong ứng dụng
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("currentPath")
    public String addCurrentPath(HttpServletRequest request) {
        String servletPath = request.getServletPath(); 
        return servletPath;
    }
    @ModelAttribute("currentUserName")
    public String currentUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() ||auth instanceof AnonymousAuthenticationToken) {
            return null; // chưa đăng nhập
        }
        // auth.getName() trả về username
        // return auth.getName()+auth.getAuthorities();
         return auth.getName()+"  ("+auth.getAuthorities().stream()
        .map(a -> a.getAuthority())                   // ví dụ: ROLE_INTERN
        .map(r -> r.startsWith("ROLE_") ? r.substring(5) : r) // bỏ tiền tố
        .collect(java.util.stream.Collectors.joining(","))+")";   // nếu nhiều role
    }
}