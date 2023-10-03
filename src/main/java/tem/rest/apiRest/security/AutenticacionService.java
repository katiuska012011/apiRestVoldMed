package tem.rest.apiRest.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tem.rest.apiRest.domain.usuarios.UsuariosRepository;

@Service
public class AutenticacionService implements UserDetailsService {

    private UsuariosRepository usuariosRepository;
    public AutenticacionService (UsuariosRepository usuariosRepository){
        this.usuariosRepository = usuariosRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuariosRepository.findByLogin(username);
    }
}
