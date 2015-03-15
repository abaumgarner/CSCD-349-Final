package Character;

import java.util.Random;

public class CharacterFactory {

	// update this array of heroes for user prompt
	private String[] heroTypes = { "", "Druid", "Mage", "Rogue", "Warrior" };

	// update this array of monster types for randomMonster method
	private String[] monsterTypes = { "Blob", "Dragon", "EarthGolem", "Goblin",
			"Vampire" };

	public GameCharacter spawnCharacter(String classification) {
		GameCharacter character = null;
		/*---------------------------------------------------------
								HERO CLASSES	
		----------------------------------------------------------*/
		if (classification.equalsIgnoreCase("Druid")) {
			character = new Druid();
		}// end else if

		else if (classification.equalsIgnoreCase("Mage")) {
			character = new Mage();
		}// end else if

		else if (classification.equalsIgnoreCase("Rogue")) {
			character = new Rogue();
		}// end else if

		else if (classification.equalsIgnoreCase("Warrior")) {
			character = new Warrior();
		}// end else if
		/*---------------------------------------------------------
							MONSTER CLASSES	
		----------------------------------------------------------*/
		else if (classification.equalsIgnoreCase("Blob")) {
			character = new Blob();
		}// end else if

		else if (classification.equalsIgnoreCase("Dragon")) {
			character = new Dragon();
		}// end else if

		else if (classification.equalsIgnoreCase("EarthGolem")) {
			character = new EarthGolem();
		}// end else if

		else if (classification.equalsIgnoreCase("Goblin")) {
			character = new Goblin();
		}// end if

		else if (classification.equalsIgnoreCase("Vampire")) {
			character = new Vampire();
		}// end else if

		/*---------------------------------------------------------*/
		else {
			System.out.println("Character type not recognized.");
		}// end else
		return character;
	}// end spawnCharacter

	public void displayHeroTypes() {
		for (int i = 1; i < heroTypes.length; i++)
			System.out.println(i + " - " + heroTypes[i]);

	}// end displayHeroTypes

	public String getHeroClassification(int index) {
		if (index >= 0 && index < heroTypes.length)
			return heroTypes[index];

		return "unspecified";
	}

	public Monster randomMonster() {
		int index = this.randomNumberGenerator(monsterTypes.length - 1);
		return (Monster) this.spawnCharacter(monsterTypes[index]);
	}// end randomMonster

	private int randomNumberGenerator(int totalMonsterTypes) {
		Random random = new Random();
		int randomNum = random.nextInt(totalMonsterTypes + 1);
		return randomNum;
	}// end randomNumberGenerator
}// end class
