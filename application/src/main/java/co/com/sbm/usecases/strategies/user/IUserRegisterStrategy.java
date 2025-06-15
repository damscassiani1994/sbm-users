package co.com.sbm.usecases.strategies.user;

public interface IUserRegisterStrategy {

    <T> boolean register(T user);
}
