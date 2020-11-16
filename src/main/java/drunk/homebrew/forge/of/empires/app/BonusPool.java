package drunk.homebrew.forge.of.empires.app;

import java.util.Random;

public class BonusPool {
	
	
	
	public Buildings poolLeuchtturm (Buildings poolObjekt, int randomNumber) {
		boolean flag;
		int id = 0;
		
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
