package co.com.sbm.configurations.security.interfaces;

public interface IPasswordHandler {
    String generatePassword(String password);
    Boolean verifyPassword(String password, String encodedPassword);
}
