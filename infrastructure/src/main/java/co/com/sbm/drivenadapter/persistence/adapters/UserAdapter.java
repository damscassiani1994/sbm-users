package co.com.sbm.drivenadapter.persistence.adapters;

import co.com.sbm.configurations.security.PasswordHandler;
import co.com.sbm.configurations.security.interfaces.IJWTProvider;
import co.com.sbm.drivenadapter.persistence.documents.AuthenticationDocument;
import co.com.sbm.drivenadapter.persistence.documents.ClientDocument;
import co.com.sbm.drivenadapter.persistence.documents.EmployerDocument;
import co.com.sbm.drivenadapter.persistence.repositories.IUserRepository;
import co.com.sbm.usecases.gateway.IUserGateway;
import co.com.sbm.model.user.Client;
import co.com.sbm.model.user.Employed;
import co.com.sbm.model.user.User;
import co.com.sbm.model.user.UserTypeEnum;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter implements IUserGateway {

    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordHandler passwordHandler;
    private final AuthenticationManager authenticationManager;
    private final IJWTProvider jwtProvider;


    public UserAdapter(IUserRepository userRepository, ModelMapper modelMapper,
                       PasswordHandler passwordHandler, AuthenticationManager authenticationManager,
                       IJWTProvider jwtProvider) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordHandler = passwordHandler;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }
    @Override
    public void registerClient(Client client) {
        ClientDocument userDocument = modelMapper.map(client, ClientDocument.class);
        AuthenticationDocument authenticationDocument = userDocument.getAuthentication();
        authenticationDocument.setPassword(this.passwordHandler
                .generatePassword(userDocument.getAuthentication().getPassword()));
        userDocument.setAuthentication(authenticationDocument);
        userDocument.setType(UserTypeEnum.CLIENT);
        userDocument.setIsActive(true);
        this.userRepository.insert(userDocument);
    }

    @Override
    public void registerEmployer(Employed employer) {
        EmployerDocument employerDocument = modelMapper.map(employer, EmployerDocument.class);
        AuthenticationDocument authenticationDocument = employerDocument.getAuthentication();
        employerDocument.setIsActive(true);
        authenticationDocument.setPassword(this.passwordHandler
                .generatePassword(employerDocument.getAuthentication().getPassword()));
        employerDocument.setAuthentication(authenticationDocument);
        employerDocument.setType(UserTypeEnum.EMPLOYED);
        this.userRepository.insert(employerDocument);
    }

    @Override
    public String login(String username, String password) {
        // AuhenticationManager is used to authenticate the user
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        username, password
                ));

        // If the authentication is successful, set the authentication in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token for the authenticated user
        return jwtProvider.tokenGenerate(authentication);
    }

    @Override
    public User filterById(long userId) {
        return null;
    }
}
