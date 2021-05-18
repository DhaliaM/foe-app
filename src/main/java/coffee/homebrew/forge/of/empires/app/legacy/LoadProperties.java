/**
 * @(#)LoadProperties.java
 *
 */
package coffee.homebrew.forge.of.empires.app.legacy;

import coffee.homebrew.forge.of.empires.app.persistence.Building;

import java.util.HashMap;
import java.util.Map;

/**
 * Legacy Klasse, welche ursprünglich zum auslesen einer .yaml Datei diente, entfällt mit Spring *
 *
 * @author Dhalia
 *
 */

public class LoadProperties {

	private Map<String, Building> buildings = new HashMap<>();
	private Map<Long, Bonus> bonus = new HashMap<>();

	public Map<String, Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(Map<String, Building> buildings) {
		this.buildings = buildings;
	}

	public Map<Long, Bonus> getBonus() {
		return bonus;
	}

	public void setBonus(Map<Long, Bonus> bonus) {
		this.bonus = bonus;
	}
}
