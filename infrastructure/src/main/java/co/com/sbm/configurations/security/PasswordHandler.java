package co.com.sbm.configurations.security;

import co.com.sbm.configurations.security.interfaces.IPasswordHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHandler implements IPasswordHandler {

    public PasswordEncoder passwordEncoder;

    public PasswordHandler(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String generatePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public Boolean verifyPassword(String password, String encodedPassword) {
        return  passwordEncoder.matches(password, encodedPassword);
    }
}
