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
		
		
		for(int i = 0; i < Math.max(heroes.size(), monsters.size()); i++){
			System.out.printf(printString(heroes.get(i)));
		}
	}
	
	public static String printString(GameCharacter character){
		
		String temp;
		
		temp = String.format("%-25s HP: %-5s/%-5s"+" Level: "+character.getLevel(),
				"["+character.getName()+"]", character.getCurrentHP(), character.getMaxHP());
		return temp;
	}
}

