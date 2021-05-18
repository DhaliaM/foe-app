package coffee.homebrew.forge.of.empires.app.ui;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Enthält die Attribute des CalculationDto-Objekts
 *
 * @author Dhalia
 */
public class CalculationDto {
    @JsonProperty("Forgepunkte")
    private int forgePoints;
    @JsonProperty("Güter")
    private int goods;
    @JsonProperty("Einheiten")
    private int units;
    @JsonProperty("Medaillen")
    private int medals;
    @JsonProperty("Vorräte")
    private int production;
    @JsonProperty("Münzen")
    private int coins;
    @JsonProperty("Diamanten")
    private int diamonds;

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

    public int getDiamonds() {
        return diamonds;
    }

    public void setDiamonds(int diamonds) {
        this.diamonds = diamonds;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
