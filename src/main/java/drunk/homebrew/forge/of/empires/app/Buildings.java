package drunk.homebrew.forge.of.empires.app;

import java.util.ArrayList;
import java.util.List;

public class Buildings {
	private String name;
	private int forgepoints;
	private int goods;
	private int units;
	private int medals;
	private int honorstreets;
	private int count;
	private int production;
	private int coins;
	private boolean galaxie;
	private int diamonds;
	
	private List<Loot> loot;
	
	public Buildings() {
		// Für SnakeYaml
	}
	
	/**
	 * Ein Copy-Konstruktor. Hier wird eine Kopie des jeweiligen Gebäudes erstellt und das übergeben Gebäunde nicht verändert.
	 */
	public Buildings(Buildings buildings) {
		this.coins = buildings.coins;
		this.count = buildings.count;
		this.diamonds = buildings.diamonds;
		this.forgepoints = buildings.forgepoints;
		this.galaxie = buildings.galaxie;
		this.goods = buildings.goods;
		this.honorstreets = buildings.honorstreets;
		this.loot = new ArrayList<>(buildings.loot); // Hier wird nur eine neue Liste erstellt, wenn du ein Loot-Objekt änderst, hat das Auswirkung auf die Kopie und das übergeben Objekt.
		this.medals = buildings.medals;
		this.name = buildings.name;
		this.production = buildings.production;
		this.units = buildings.units;
	}
	
	
	
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
	public int getHonorstreets() {
		return honorstreets;
	}
	public void setHonorstreets(int honorstreets) {
		this.honorstreets = honorstreets;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
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
	@Override
	public String toString() {
		return "Buildings [name=" + name + ",diamonds=" + diamonds + ", forgepoints=" + forgepoints + ", goods=" + goods + ", units=" + units
				+ ", medals=" + medals + ", honorstreets=" + honorstreets + ", count=" + count + ", production="
				+ production + ", coins=" + coins + "]";
	}
	public List<Loot> getLoot() {
		return loot;
	}
	public void setLoot(List<Loot> Loot) {
		this.loot = Loot;
	}
	public boolean isGalaxie() {
		return galaxie;
	}
	public void setGalaxie(boolean galaxie) {
		this.galaxie = galaxie;
	}
	public int getDiamonds() {
		return diamonds;
	}
	public void setDiamonds(int diamonds) {
		this.diamonds = diamonds;
	}
	
	
	
		
}
