package drunk.homebrew.forge.of.empires.app.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingJpaRepository extends CrudRepository <BuildingEntity, Integer>{

}
