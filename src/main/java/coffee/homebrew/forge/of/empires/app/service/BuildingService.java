package coffee.homebrew.forge.of.empires.app.service;

import coffee.homebrew.forge.of.empires.app.persistence.BuildingEntity;
import coffee.homebrew.forge.of.empires.app.persistence.BuildingJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service um Daten in BuildingJpaRepository/der Datenbank zu ändern
 *
 * @author Dhalia
 */
@Service
public class BuildingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BuildingService.class);
    private BuildingJpaRepository buildingJpaRepository;

    public BuildingService(BuildingJpaRepository buildingJpaRepository) {
        this.buildingJpaRepository = buildingJpaRepository;
    }

    /**
     * Gibt alle Gebäude aus der Gebäudetabelle zurück.
     *
     * @return Liste mit Gebäuden
     */
    public List<BuildingEntity> findAll() {
        Iterable<BuildingEntity> iterable = buildingJpaRepository.findAll();
        List<BuildingEntity> buildingEntityList = null;
        if (iterable instanceof List) {
            buildingEntityList = (List<BuildingEntity>) iterable;
        }
        return buildingEntityList;
    }

    /**
     * Löscht das gewählte Gebäude aus der DB.
     *
     * @param buildingEntity Das zu löschende Gebäude.
     */
    public void delete(BuildingEntity buildingEntity) {
        buildingJpaRepository.delete(buildingEntity);
    }

    /**
     * Speichert ein Gebäude in der DB.
     *
     * @param buildingEntity
     */
    public void save(BuildingEntity buildingEntity) {
        if (buildingEntity == null) {
            LOGGER.error("kein Gebäude");
            return;
        }
        buildingJpaRepository.save(buildingEntity);
    }
}
