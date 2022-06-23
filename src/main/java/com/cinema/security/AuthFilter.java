package com.cinema.security;

import com.cinema.exception.CustomException;
import com.cinema.entities.User;
import com.cinema.service.UserService;
import com.cinema.util.TokenUtils;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     *
     * ServletRequest request: là HTTP request bao gồm những thông tin mà client gửi đến
     *
     * ServletResponse: HTTP response. Spring security sử dụng ServletResponse để chỉnh sửa response trước khi gửi lại cho client hoặc gửi cho các filter tiếp theo trong chuỗi.
     *
     * FilterChain chain: để forward request đến filter tiếp theo trong chuỗi.
     *
     * Authentication vs Authorization
     * Authentication: quá trình xác minh user, dựa vào thông tin đăng nhập mà user cung cấp. Ví dụ khi login, bạn nhập username và password, nó giúp hệ thống nhận ra bạn là ai.
     * Authorization: Quá tình xác định xem user có quyền thực hiện những chức năng nào của hệ thống (đọc/sửa/xóa data), sau khi user đã authenticated thành công.
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String token = authorizationHeader.replace("Bearer ", "");
                String username = tokenUtils.verifyToken(token);
                User user = userService.findByUsername(username);
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(user.getRole()));
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getId(), user, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
            } catch (JWTVerificationException | CustomException e) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                Map<String, String> error = new HashMap<>();
                error.put("errorMessage", e.getMessage());
                objectMapper.writeValue(response.getOutputStream(), error);
            }
            return;
        }
        filterChain.doFilter(request, response);
    }
}
