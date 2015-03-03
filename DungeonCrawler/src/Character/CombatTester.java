package Character;

import java.util.ArrayList;
import java.util.Random;


public class CombatTester {
	
	public static void main(String[] args){
		
		Warrior warr = new Warrior();
		Warrior warr2 = new Warrior();
		Warrior warr3 = new Warrior();
	    Goblin gob = new Goblin();
	    Goblin gob2 = new Goblin();
	    Goblin gob3 = new Goblin();
	    
	    
	    warr2.setName("Two");
	    warr3.setName("Three");
	    
	    ArrayList<GameCharacter> heroes = new ArrayList<GameCharacter>();
	    ArrayList<GameCharacter> monsters = new ArrayList<GameCharacter>();
	    
	    heroes.add(warr);
	    //heroes.add(warr2);
	    //heroes.add(warr3);
	    monsters.add(gob);
	    monsters.add(gob2);
	    monsters.add(gob3);
	    
	    
	    Combat combat = new Combat(heroes, monsters);
	   // combat.run();
	    
	    heroes = new ArrayList<GameCharacter>();
	    monsters = new ArrayList<GameCharacter>();
	    
	    warr = new Warrior();
	    warr2 = new Warrior();
	    gob = new Goblin();
	    gob2 = new Goblin();
	    
	    heroes.add(warr);
	    heroes.add(warr2);
	    
	    monsters.add(gob);
	    monsters.add(gob2);
	    Combat combat2 = new Combat(heroes, monsters);
	    
	    System.out.println();
	    System.out.println("warr's exp: "+warr.getExp());
	    System.out.println("warr2's exp: "+warr2.getExp());
	    System.out.println();
	    
	    combat2.run();
	    
	    System.out.println();
	    System.out.println("warr's exp: "+warr.getExp());
	    System.out.println("warr2's exp: "+warr2.getExp());
	}

}
