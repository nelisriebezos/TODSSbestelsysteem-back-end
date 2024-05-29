package nl.hu.todds_backend.security.presentation.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import nl.hu.todds_backend.security.domain.User;
import nl.hu.todds_backend.security.presentation.dto.UserDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final String secret;
    private final Integer expirationInMs;

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(String defaultFilterProcessesUrl, String secret, Integer expirationInMs, AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl);
        this.secret = secret;
        this.expirationInMs = expirationInMs;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        if (CorsUtils.isPreFlightRequest(httpServletRequest)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            return new UsernamePasswordAuthenticationToken("x", "x");
        }
        UserDTO userDTO = new ObjectMapper().readValue(httpServletRequest.getInputStream(), UserDTO.class);
        return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        List<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        String token = Jwts.builder().signWith(Keys.hmacShaKeyFor(this.secret.getBytes()), SignatureAlgorithm.HS512)
                .setHeaderParam("type", "JWT")
                .setIssuer("Pfed")
                .setAudience("Pfed")
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + this.expirationInMs))
                .claim("role", roles)
                .compact();
        response.addHeader("Authorization", "Bearer " + token);
    }
}
