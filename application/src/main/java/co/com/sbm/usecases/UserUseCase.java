package co.com.sbm.usecases;

import co.com.sbm.usecases.gateway.IUserGateway;
import co.com.sbm.usecases.factory.UserSimpleFactory;
import co.com.sbm.usecases.gateway.IUserUseCaseGateway;
import co.com.sbm.usecases.strategies.user.IUserRegisterStrategy;
import co.com.sbm.model.user.User;
import co.com.sbm.model.user.UserTypeEnum;

public class UserUseCase implements IUserUseCaseGateway {

    private final IUserGateway userGateway;

    public UserUseCase(IUserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public <T> boolean register(T user, UserTypeEnum type) {
        IUserRegisterStrategy userRegisterStrategy = UserSimpleFactory
                .getUser(userGateway, type);

        return userRegisterStrategy.register(user);
    }

    @Override
    public String login(String username, String password) {
        return userGateway.login(username, password);
    }

    @Override
    public User filterById(long userId) {
        return null;
    }

    @Override
    public User findByAuthentication(String token) {
        return userGateway.findByAuthentication(token);
    }
}
