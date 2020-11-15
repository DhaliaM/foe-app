package drunk.homebrew.forge.of.empires.app;

import java.util.Random;

public class BonusPool extends Buildings{
	
	
	
	Buildings poolLeuchtturm (Buildings poolObjekt, int randomNumber) {
		
		
		
		
		
		Random rng = new Random();
		Integer rNumber = rng.nextInt(5); //poolObjekt.getLoot().size() + 1(Random ist exklusiv) vlt später einsetzen, wenn es denn funzt
		
		
		if(randomNumber > 60) {
			poolObjekt.setForgepoints(20);
			
		}
		else
		{
		
			while(randomNumber > poolObjekt.getLoot().get(rNumber).getChance() * 100 && rNumber != 1  ) {
				rNumber = rng.nextInt(5);
			}
			
			switch(rNumber) {
			case 0:
					poolObjekt.setDiamonds(25);
					break;
			case 2: 
					poolObjekt.setGoods(50);
					break;
			case 3:
					poolObjekt.setMedals(1900);
					break;
			}
		}
		
		
		
		Buildings result = poolObjekt;
		
		
		
		
		
		return result;
		
		
	}

	public BonusPool poolLeuchtturm(Buildings poolObjekt, Integer zufallszahl) {
		// TODO Auto-generated method stub
		return null;
	}
}
