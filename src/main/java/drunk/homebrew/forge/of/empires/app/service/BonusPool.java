package drunk.homebrew.forge.of.empires.app.service;

import java.util.Random;

import drunk.homebrew.forge.of.empires.app.legacy.FoE;
import drunk.homebrew.forge.of.empires.app.persistence.Buildings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BonusPool {
	private static final Logger LOGGER = LoggerFactory.getLogger(FoE.class);


	public Buildings poolLeuchtturm (Buildings poolObjekt, int randomNumber) {
		boolean flag;


		Random rng = new Random();
		Integer rNumber = rng.nextInt(4); //(poolObjekt.getLoot().size() - 1);


		while(flag = false)
		{
			if(randomNumber > poolObjekt.getLoot().get(rNumber).getChance() * 100) {
				flag = true;
			}

		}

		switch(rNumber)
		{
		case 0:
			poolObjekt.setDiamonds(25);
			break;
		case 1:
			poolObjekt.setForgepoints(20);
			break;
		case 2:
			poolObjekt.setGoods(50);
			break;
		case 3:
			poolObjekt.setMedals(1900);
			break;

		}


		Buildings result = poolObjekt;





		return result;


	}
}
