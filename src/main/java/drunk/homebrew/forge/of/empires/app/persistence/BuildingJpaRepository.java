package drunk.homebrew.forge.of.empires.app.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * Laden der Gebäudedaten aus einer Datenbank mittels SpringData.
 * @author Dhalia
 */
@Repository
public interface BuildingJpaRepository extends CrudRepository <BuildingEntity, Integer>{

}
