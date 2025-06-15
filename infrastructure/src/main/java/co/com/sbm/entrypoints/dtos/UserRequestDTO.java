package co.com.sbm.entrypoints.dtos;

import co.com.sbm.model.user.Authentication;
import co.com.sbm.model.user.UserTypeEnum;

public record UserRequestDTO(
        String name,
        String lastName,
        String age,
        String email,
        AutenticationRequestDTO authentication,
        Integer type
) {}
