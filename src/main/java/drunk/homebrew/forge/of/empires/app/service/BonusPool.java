package drunk.homebrew.forge.of.empires.app.service;

import java.util.Random;
import drunk.homebrew.forge.of.empires.app.persistence.Buildings;

public class BonusPool {
	
	public Buildings poolLeuchtturm (Buildings poolObjekt, int randomNumber) {
		boolean flag;
		Random rng = new Random();
		Integer rNumber = rng.nextInt(4);
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
