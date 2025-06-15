package co.com.sbm.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public sealed class User permits Employed, Client {
    private String name;
    private String lastName;
    private String age;
    private String email;
    private Authentication authentication;
    private UserTypeEnum type;
}
