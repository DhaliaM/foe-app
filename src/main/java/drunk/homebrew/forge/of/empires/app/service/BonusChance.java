package drunk.homebrew.forge.of.empires.app.service;

import java.util.Random;

public class BonusChance {
	
	public boolean chance(double d) {
		Random rng = new Random();
		Integer zufallszahl = rng.nextInt(101);
		if(zufallszahl < d*100) {
			return true;
		}
		else
		{
		return false;
		}
	}
}
