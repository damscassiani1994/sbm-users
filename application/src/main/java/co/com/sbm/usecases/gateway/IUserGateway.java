package co.com.sbm.usecases.gateway;

import co.com.sbm.model.user.Client;
import co.com.sbm.model.user.Employed;
import co.com.sbm.model.user.User;

public interface IUserGateway {
    void registerClient(Client client);
    void registerEmployer(Employed employer);
    String login(String username, String password);
    User filterById(long userId);
    User findByAuthentication(String token);
}
