package Character;

import java.util.ArrayList;

public class HUDTester {

	public static void main(String[] args){
		
		Warrior warr = new Warrior();
		Warrior warr2 = new Warrior();
		Goblin gob = new Goblin();
		Goblin gob2 = new Goblin();
		
		warr2.setName("Bob the Builder");
		
		ArrayList<GameCharacter> heroes = new ArrayList<GameCharacter>();
		ArrayList<GameCharacter> monsters = new ArrayList<GameCharacter>();
		
		heroes.add(warr);
		heroes.add(warr2);
		monsters.add(gob);
		monsters.add(gob2);
		
		gob2.setName("Hobgoblin");
		
		System.out.printf("Party: %-50sMonsters:\n", "");
		for(int i = 0; i < Math.max(heroes.size(), monsters.size()); i++){
		
			
			System.out.printf(printHero(heroes.get(i))+"%-10s"+printMonster(monsters.get(i)),"");
			System.out.println();
		}
	}
	
	public static String printHero(GameCharacter character){
		
		String temp;
		
		temp = String.format("%-20s Level: %-3s HP: %-5s/%5s",
				"["+character.getName()+"]",character.stats.getLevel(), character.stats.getCurrentHP(), character.stats.getMaxHP());
		return temp;
	}
	
	public static String printMonster(GameCharacter character){
		
		String temp;
		
		temp = String.format("%-20s Level: %-3s HP: %-5s/%5s",
				"["+character.getName()+"]",character.stats.getLevel(), character.stats.getCurrentHP(), character.stats.getMaxHP());
		return temp;
	}
}

