package drunk.homebrew.forge.of.empires.app;

import java.util.Random;

public class BonusPool{
	
	
	
	Buildings poolLeuchtturm (Buildings poolObjekt, int randomNumber) {

		Buildings result = new Buildings(poolObjekt);
		Random rng = new Random();
		Integer rNumber = rng.nextInt(5); //poolObjekt.getLoot().size() + 1(Random ist exklusiv) vlt sp�ter einsetzen, wenn es denn funzt
		
		
		if(randomNumber > 60) {
			result.setForgepoints(20);
			
		}
		else
		{
		
			while(randomNumber > result.getLoot().get(rNumber).getChance() * 100 && rNumber != 1  ) {
				rNumber = rng.nextInt(5);
			}
			
			switch(rNumber) {
			case 0:
				result.setDiamonds(25);
					break;
			case 2: 
				result.setGoods(50);
					break;
			case 3:
				result.setMedals(1900);
					break;
			}
		}
		
		return result;
		
		
	}
}
