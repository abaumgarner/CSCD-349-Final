package Character;

public class LevelUpTester {
	
	public static void main(String[] args){
		
		Warrior warr = new Warrior();
		
		System.out.println(printStats(warr));
		System.out.println();
		
		
		warr.stats.setLevel(2);
		
		System.out.println(printStats(warr));
		System.out.println();
		
		warr.stats.setLevel(3);
		
		System.out.println(printStats(warr));
		System.out.println();
		
		warr.stats.setLevel(3);
		warr.stats.setLevel(2);
		
		System.out.println(printStats(warr));
		System.out.println();
		
		Goblin gob = new Goblin();
		
		System.out.println(printStats(gob));
		System.out.println();
		
		gob.stats.setLevel(2);
		
		System.out.println(printStats(gob));
		System.out.println();
	}
	
	public static String printStats(GameCharacter character){
		
		String temp;
		
		temp = "["+character.getName() + "] Level: "+character.stats.getLevel()+" Experience: "+character.stats.getExp()+"\n"
				+"HP: "+character.stats.getMaxHP()+" Strength: "+character.stats.getStr()+" Dexterity: "+character.stats.getDex()+"\n"
				+"Wisdom: "+character.stats.getWis()+" Vitality: "+character.stats.getVit();
		
		return temp;
	}

}
