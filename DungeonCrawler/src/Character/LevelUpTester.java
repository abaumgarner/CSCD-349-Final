package Character;

public class LevelUpTester {
	
	public static void main(String[] args){
		
		Warrior warr = new Warrior();
		
		System.out.println(printStats(warr));
		System.out.println();
		
		warr.setLevel(2);
		
		System.out.println(printStats(warr));
		System.out.println();
		
		warr.setLevel(3);
		
		System.out.println(printStats(warr));
		System.out.println();
		
		warr.setLevel(3);
		warr.setLevel(2);
		
		System.out.println(printStats(warr));
		System.out.println();
		
		Goblin gob = new Goblin();
		
		System.out.println(printStats(gob));
		System.out.println();
		
		gob.setLevel(2);
		
		System.out.println(printStats(gob));
		System.out.println();
	}
	
	public static String printStats(GameCharacter character){
		
		String temp;
		
		temp = "["+character.getName() + "] Level: "+character.getLevel()+" Experience: "+character.getExp()+"\n"
				+"HP: "+character.getMaxHP()+" Strength: "+character.getStr()+" Dexterity: "+character.getDex()+"\n"
				+"Wisdom: "+character.getWis()+" Vitality: "+character.getVit();
		
		return temp;
	}

}
