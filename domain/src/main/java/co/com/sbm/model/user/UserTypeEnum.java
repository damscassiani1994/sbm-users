package co.com.sbm.model.user;

import lombok.Getter;

@Getter
public enum UserTypeEnum {

    EMPLOYED(1), CLIENT(2), ADMIN(3);
    private int code;

    UserTypeEnum(int code){
        this.code = code;
    }
}
