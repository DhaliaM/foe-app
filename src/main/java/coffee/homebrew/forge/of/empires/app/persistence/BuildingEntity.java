package coffee.homebrew.forge.of.empires.app.persistence;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
/**
 * Die Entität eines Objekts für die Spezialgebäudetabelle.
 *
 * @author Dhalia
 */
@Entity
@Table(name = "Spezialgebäude")
public class BuildingEntity {

    public BuildingEntity() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Forgepunkte")
    private int forgePoints;

    @Column(name = "Güter")
    private int goods;

    @Column(name = "Vorrat")
    private int production;

    @Column(name = "Einheiten")
    private int units;

    @Column(name = "Münzen")
    private int coins;

    @Column(name = "Medaillen")
    private int medals;

    @Column(name = "Diamanten")
    private int diamonds;

    @Transient
    List<Integer> deletedIds = new ArrayList<>();

    public List<Integer> getDeletedIds() {
        return deletedIds;
    }

    public void setDeletedIds(List<Integer> deletedIds) {
        this.deletedIds = deletedIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getForgePoints() {
        return forgePoints;
    }

    public void setForgePoints(int forgePoints) {
        this.forgePoints = forgePoints;
    }

    public int getGoods() {
        return goods;
    }

    public void setGoods(int goods) {
        this.goods = goods;
    }

    public int getProduction() {
        return production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getMedals() {
        return medals;
    }

    public void setMedals(int medals) {
        this.medals = medals;
    }

    public int getDiamonds() {
        return diamonds;
    }

    public void setDiamonds(int diamonds) {
        this.diamonds = diamonds;
    }

}
