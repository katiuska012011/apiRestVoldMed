package tem.rest.apiRest.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.annotations.Comment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tem.rest.apiRest.domain.usuarios.UsuariosRepository;

import java.io.IOException;

@Component
public class SecutiryFilter extends OncePerRequestFilter {
    private TokenService tokenService;

    private UsuariosRepository usuariosRepository;
    public SecutiryFilter(TokenService tokenService,UsuariosRepository usuariosRepository){
        this.tokenService = tokenService;
        this.usuariosRepository = usuariosRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var outhHeader = request.getHeader("Authorization");
        if(outhHeader != null){
            var token = outhHeader.replace("Bearer ", "");
            var subject = tokenService.getSubject(token);

            if(subject != null){
                var usuario = usuariosRepository.findByLogin(subject);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
