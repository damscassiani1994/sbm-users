package co.com.sbm.drivenadapter.persistence.documents;


import co.com.sbm.drivenadapter.persistence.generic.GenericDocument;
import co.com.sbm.model.user.Authentication;
import co.com.sbm.model.user.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document("users")
public class UserDocument extends GenericDocument {
    @Id
    private String id;
    private String name;
    private String lastName;
    private String age;
    private String email;
    private AuthenticationDocument authentication;
    private UserTypeEnum type;
}
