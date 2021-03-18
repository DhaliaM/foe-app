package drunk.homebrew.forge.of.empires.app.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * Laden der Userdaten aus einer Datenbank mittels SpringData.
 * @author Dhalia
 */
@Repository
public interface UserRepository extends CrudRepository <UserEntity, Long>{

    UserEntity findByUserName(String userName);

}
