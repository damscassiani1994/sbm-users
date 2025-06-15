package co.com.sbm.usecases.strategies.user;

import co.com.sbm.usecases.gateway.IUserGateway;
import co.com.sbm.model.user.Client;

public class ClientRegister implements IUserRegisterStrategy {

    private IUserGateway userGateway;

    public ClientRegister(IUserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public <T> boolean register(T user) {
        Client client = (Client) user;
        userGateway.registerClient(client);
        return true;
    }
}
