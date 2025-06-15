package co.com.sbm.usecases.gateway;

import co.com.sbm.model.user.User;
import co.com.sbm.model.user.UserTypeEnum;

public interface IUserUseCaseGateway {
    <T> boolean register(T user, UserTypeEnum type);
    String login(String username, String password);
    User filterById(long userId);
}
