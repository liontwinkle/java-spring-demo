package com.codesample.demo.filter;

import com.codesample.demo.exceprion.AuthorizationException;
import com.codesample.demo.mapper.UserMapper;
import com.codesample.demo.domain.UserTokenEntity;
import com.codesample.demo.request.UserPrincipal;
import com.codesample.demo.service.SecurityService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.codesample.demo.constant.ApplicationConstants.AUTH_HEADER;
import static com.codesample.demo.constant.ApplicationConstants.AUTH_HEADER_PREFIX;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    private SecurityService securityService;
    private UserMapper userMapper;

    public AuthorizationFilter(AuthenticationManager authenticationManager, SecurityService securityService, UserMapper userMapper) {
        super(authenticationManager);
        this.securityService = securityService;
        this.userMapper = userMapper;
    }

    @Override
    public void doFilterInternal(HttpServletRequest req,
                                 HttpServletResponse res,
                                 FilterChain chain) throws IOException, ServletException {
        String header = getToken(req);
        if (Objects.isNull(header) || !header.startsWith(AUTH_HEADER_PREFIX)) {
            SecurityContextHolder.getContext().setAuthentication(null);
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = getToken(request);
        if (token != null) {
            token = token.substring(AUTH_HEADER_PREFIX.length());
            UserTokenEntity tokenEntity = securityService.getToken(token);
            if (tokenEntity != null) {
                if(LocalDateTime.now().isAfter(tokenEntity.getExpireAt())) {
                    throw new AuthorizationException("Token expire");
                }
                UserPrincipal user = userMapper.entityToUserPrincipal(tokenEntity.getUser());
                if (user == null) {
                    throw new AuthorizationException("User not found.");
                }
                return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            }
        }
        return null;
    }

    private String getToken(HttpServletRequest req) {
        String header = req.getHeader(AUTH_HEADER);
        if(Objects.isNull(header)) {
            header = req.getParameter(AUTH_HEADER);
        }
        return header;
    }
}
