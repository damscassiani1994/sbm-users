package co.com.sbm.usecases.factory;

import co.com.sbm.usecases.gateway.IUserGateway;
import co.com.sbm.usecases.strategies.user.ClientRegister;
import co.com.sbm.usecases.strategies.user.EmployerRegister;
import co.com.sbm.usecases.strategies.user.IUserRegisterStrategy;
import co.com.sbm.model.user.UserTypeEnum;

public class UserSimpleFactory {
    public static IUserRegisterStrategy getUser(IUserGateway userGateway, UserTypeEnum type) {
        return switch (type) {
            case EMPLOYED -> new EmployerRegister(userGateway);
            case null -> throw new RuntimeException("Type can't be null");
            default -> new ClientRegister(userGateway);
        };
    }
}
