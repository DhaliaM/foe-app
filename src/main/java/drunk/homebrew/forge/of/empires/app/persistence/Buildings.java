//Q: Was sind das für Kommentare?
/**
 * @(#)Buildings.java
 *
 */
package drunk.homebrew.forge.of.empires.app.persistence;

//FIXME: Unnötige Imports sollten immer entfernt werden
import java.util.List;

/**
 * FIXME: Sätze mit großen Buschstaben und mit "." beenden (PS: das mag pingelig klingen, wird aber in Code Reviews oder Tools, die Javadoc prüfen, angemeckert)
 * enthält die Attribute des Buildings-Objekts
 * @author Dhalia
 */
//FIXME: Unnötige Leerzeilen vermeiden

//FIXME: Klassennamen sind immer im Singular
public class Buildings {
    private String name;
    //FIXME: Typo, bitte in CamelCase ändern
    private int forgepoints;
    private int goods;
    private int units;
    private int medals;
    private int production;
    private int coins;
    private int diamonds;
    private int id;

    public int getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //FIXME: Typo, bitte in CamelCase ändern
    public int getForgepoints() {
        return forgepoints;
    }

    //FIXME: Typo, bitte in CamelCase ändern
    public void setForgepoints(int forgepoints) {
        this.forgepoints = forgepoints;
    }

    public int getGoods() {
        return goods;
    }

    public void setGoods(int goods) {
        this.goods = goods;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public int getMedals() {
        return medals;
    }

    public void setMedals(int medals) {
        this.medals = medals;
    }

    public int getProduction() {
        return production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getDiamonds() {
        return diamonds;
    }

    public void setDiamonds(int diamonds) {
        this.diamonds = diamonds;
    }

    @Override
    public String toString() {
        return "Buildings [id= " + id + " , name=" + name + ",diamonds=" + diamonds + ", forgepoints=" + forgepoints + ", goods=" + goods + ", units=" + units
                + ", medals=" + medals + ", production=" + production + ", coins=" + coins + "]";
    }
}
