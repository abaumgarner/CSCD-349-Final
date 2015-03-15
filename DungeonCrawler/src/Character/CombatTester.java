package Character;

import java.util.ArrayList;

public class CombatTester {

	public static void main(String[] args) {

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
		// heroes.add(warr2);
		// heroes.add(warr3);
		monsters.add(gob);
		monsters.add(gob2);
		monsters.add(gob3);

		Combat combat = new Combat(heroes, monsters);
		// combat.run();

		heroes = new ArrayList<GameCharacter>();
		monsters = new ArrayList<GameCharacter>();

		warr = new Warrior();
		Rogue rog = new Rogue();
		Mage mage = new Mage();
		Druid druid = new Druid();

		gob = new Goblin();
		gob2 = new Goblin();

		gob2.setName("TESTINGTESTING");
		// heroes.add(warr);
		// heroes.add(mage);
		heroes.add(druid);

		monsters.add(gob);
		monsters.add(gob2);
		// monsters.add(gob3);

		Combat combat2 = new Combat(heroes, monsters);

		System.out.println();
		System.out.println("warr's exp: " + druid.stats.getExp());
		System.out.println("rog's exp: " + mage.stats.getExp());
		System.out.println();

		combat2.run();

		System.out.println();
		System.out.println("warr's exp: " + druid.stats.getExp());
		System.out.println("rog's exp: " + mage.stats.getExp());
	}

}
