package co.com.sbm.usecases.strategies.user;

import co.com.sbm.usecases.gateway.IUserGateway;
import co.com.sbm.model.user.Employed;

public class EmployerRegister implements IUserRegisterStrategy{

    private IUserGateway userGateway;

    public EmployerRegister(IUserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public <T> boolean register(T user) {
        Employed employed = (Employed) user;
        userGateway.registerEmployer(employed);
        return true;
    }
}
