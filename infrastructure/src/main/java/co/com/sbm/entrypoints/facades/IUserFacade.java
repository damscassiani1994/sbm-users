package co.com.sbm.entrypoints.facades;

import co.com.sbm.entrypoints.dtos.UserRequestDTO;

public interface IUserFacade {

    Boolean createUserProcess(UserRequestDTO userRequestDTO);
}
