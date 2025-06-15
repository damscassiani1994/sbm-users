package co.com.sbm.entrypoints.controllers;

import co.com.sbm.entrypoints.dtos.AutenticationRequestDTO;
import co.com.sbm.entrypoints.dtos.UserRequestDTO;
import co.com.sbm.entrypoints.facades.IUserFacade;
import co.com.sbm.usecases.gateway.IUserUseCaseGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/sbm/user")
@RestController
public class UserController {

    public final IUserUseCaseGateway userUseCaseGateway;
    public final IUserFacade userFacade;

    public UserController(IUserUseCaseGateway userUseCaseGateway, IUserFacade userFacade) {
        this.userUseCaseGateway = userUseCaseGateway;
        this.userFacade = userFacade;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ResponseEntity<Boolean> create(@RequestBody UserRequestDTO userRequestDTO) {
        Boolean result = userFacade.createUserProcess(userRequestDTO);
        return ResponseEntity.ok(result);
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AutenticationRequestDTO requestDTO) {
        String token = userUseCaseGateway.login(requestDTO.userName(),
                requestDTO.password());
        return ResponseEntity.ok(token);
    }



}
