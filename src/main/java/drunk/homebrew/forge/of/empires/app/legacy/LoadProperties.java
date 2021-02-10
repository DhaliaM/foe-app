/**
 * @(#)LoadProperties.java
 *
 */
package drunk.homebrew.forge.of.empires.app.legacy;

import drunk.homebrew.forge.of.empires.app.persistence.Buildings;
import java.util.HashMap;
import java.util.Map;

/**
 * Legacy Klasse, welche ursprünglich zum auslesen einer .yaml Datei diente, entfällt mit Spring *
 *
 * @author Dhalia
 *
 */

public class LoadProperties {

	private Map<String, Buildings> buildings = new HashMap<>();
	private Map<Long, Bonus> bonus = new HashMap<>();

	public Map<String, Buildings> getBuildings() {
		return buildings;
	}

	public void setBuildings(Map<String, Buildings> buildings) {
		this.buildings = buildings;
	}

	public Map<Long, Bonus> getBonus() {
		return bonus;
	}

	public void setBonus(Map<Long, Bonus> bonus) {
		this.bonus = bonus;
	}
}
