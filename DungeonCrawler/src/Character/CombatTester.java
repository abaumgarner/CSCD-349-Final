import java.util.ArrayList;


public class CombatTester {
	
	public static void main(String[] args){
		
		Warrior warr = new Warrior();
	    Character enemy1 = new Warrior();
	    Character enemy2 = new Warrior();
	    Character enemy3 = new Warrior();
	    Character enemy4 = new Warrior();
	    
	    enemy1.setName("one");
	    enemy2.setName("two");
	    enemy3.setName("three");
	    enemy4.setName("four");
	    
	    ArrayList<Character> heroes = new ArrayList<Character>();
	    ArrayList<Character> monsters = new ArrayList<Character>();
	    
	    heroes.add(warr);
	    monsters.add(enemy1);
	    monsters.add(enemy2);
	    monsters.add(enemy3);
	    monsters.add(enemy4);
	    
	    
	    Combat combat = new Combat(heroes, monsters);
	    
	    combat.setInitiatives(); //this will print an error message.
	    combat.organizeTurns(heroes, monsters);
	    combat.setInitiatives();
	    
	    
	    for(Character c : combat.getTurnOrder()){
	    	
	    	System.out.println(c.name+": "+c.initiative);
	    }
	    
	   warr.cripple(enemy2);
	    
	    for(Character c : combat.getTurnOrder()){
	    	
	    	System.out.println(c.name+": "+c.initiative);
	    }
	}

}
