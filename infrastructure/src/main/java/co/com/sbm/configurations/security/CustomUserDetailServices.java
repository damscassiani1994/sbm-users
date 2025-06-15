package co.com.sbm.configurations.security;

import co.com.sbm.drivenadapter.persistence.documents.UserDocument;
import co.com.sbm.drivenadapter.persistence.repositories.IUserRepository;
import co.com.sbm.usecases.gateway.IUserGateway;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomUserDetailServices implements UserDetailsService {

    private final IUserRepository userRepository;

    public CustomUserDetailServices(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDocument user = userRepository.findByAuthenticationUserName(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        return new User(username,
                user.getAuthentication().getPassword(),
                user.getIsActive(),
                true,
                true,
                true,
                grantedAuthorities);
    }
}
