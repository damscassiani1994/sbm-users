package co.com.sbm.drivenadapter.persistence.repositories;

import co.com.sbm.drivenadapter.persistence.documents.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<UserDocument, String> {

    UserDocument findByAuthenticationUserName(String userName);
}
