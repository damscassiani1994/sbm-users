package co.com.sbm.entrypoints.facades;

import co.com.sbm.entrypoints.dtos.UserRequestDTO;
import co.com.sbm.model.user.Authentication;
import co.com.sbm.model.user.User;
import co.com.sbm.usecases.gateway.IUserUseCaseGateway;
import co.com.sbm.model.user.Client;
import co.com.sbm.model.user.Employed;
import co.com.sbm.model.user.UserTypeEnum;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserFacade implements IUserFacade {

    public IUserUseCaseGateway userUseCaseGateway;
    public ModelMapper modelMapper;

    public UserFacade(IUserUseCaseGateway userUseCaseGateway,
                      ModelMapper modelMapper) {
        this.userUseCaseGateway = userUseCaseGateway;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean createUserProcess(UserRequestDTO userRequestDTO) {
        if (1 == userRequestDTO.type()) {
            Employed employed =  getEmployedData(userRequestDTO);
            return userUseCaseGateway.register(employed, employed.getType());
        }
        Client client = getClientData(userRequestDTO);
        return userUseCaseGateway.register(client, client.getType());
    }

    private Client getClientData(UserRequestDTO userRequestDTO) {
        Client client = new Client();
        client.setAge(userRequestDTO.age());
        client.setName(userRequestDTO.name());
        client.setLastName(userRequestDTO.lastName());
        client.setEmail(userRequestDTO.email());
        Authentication authentication = new Authentication();
        authentication.setUserName(userRequestDTO.authentication().userName());
        authentication.setPassword(userRequestDTO.authentication().password());
        client.setAuthentication(authentication);
        client.setType(UserTypeEnum.CLIENT);
        return client;
    }

    private Employed getEmployedData(UserRequestDTO userRequestDTO) {
        Employed employed = new Employed();
        employed.setAge(userRequestDTO.age());
        employed.setName(userRequestDTO.name());
        employed.setLastName(userRequestDTO.lastName());
        employed.setEmail(userRequestDTO.email());
        Authentication authentication = new Authentication();
        authentication.setUserName(userRequestDTO.authentication().userName());
        authentication.setPassword(userRequestDTO.authentication().password());
        employed.setAuthentication(authentication);
        employed.setType(UserTypeEnum.EMPLOYED);
        return  employed;
    }
}
