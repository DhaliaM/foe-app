package drunk.homebrew.forge.of.empires.app;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BonusChance {
	private static final Logger LOGGER = LoggerFactory.getLogger(BonusChance.class);
	
	public boolean galaxieChance(double d) {
		Random rng = new Random();
		Integer zufallszahl = rng.nextInt(101);
		if(zufallszahl < d*100) {
			//LOGGER.error("Die Zufallszahl ist: " + zufallszahl);
			return true;
		}
		else
		{
		//LOGGER.error("Die Zufallszahl ist: " + zufallszahl);
		return false;
		}
	}
}
