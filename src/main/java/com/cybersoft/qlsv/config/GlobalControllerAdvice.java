package com.cybersoft.qlsv.config;

import jakarta.servlet.http.HttpServletRequest;
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
        if (auth == null || !auth.isAuthenticated()
                || "anonymousUser".equalsIgnoreCase(String.valueOf(auth.getPrincipal()))) {
            return null; // chưa đăng nhập
        }
        // auth.getName() trả về username
        return auth.getName();
    }
}