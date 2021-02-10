/**
 * @(#)Buildings.java
 *
 */
package drunk.homebrew.forge.of.empires.app.persistence;

import java.util.List;

/**
 * enthält die Attribute des Buildings-Objekts
 * @author Dhalia
 */

public class Buildings {
    private String name;
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

    public int getForgepoints() {
        return forgepoints;
    }

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
