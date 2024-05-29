package nl.hu.todds_backend.security.presentation.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import nl.hu.todds_backend.security.domain.UserProfile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final String secret;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, String secret) {
        super(authenticationManager);
        this.secret = secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = this.getAuthentication(request);
        if (authentication != null) SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) return null;
        Jws<Claims> parsedToken = Jwts.parserBuilder().setSigningKey(this.secret.getBytes()).build().parseClaimsJws(token.replace("Bearer ", ""));
        String username = parsedToken.getBody().getSubject();
        List<SimpleGrantedAuthority> authorities = ((List<?>) parsedToken.getBody().get("role"))
                .stream().map((authority) -> new SimpleGrantedAuthority((String) authority)).collect(Collectors.toList());
        if (username.isEmpty()) return null;
        UserProfile principal = new UserProfile(username);
        return new UsernamePasswordAuthenticationToken(principal, null, authorities);
    }
}
