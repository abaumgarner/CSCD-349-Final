package Character;

import java.util.Random;

public class Blob extends Monster {

	public Blob() {

		this.name = "Blob";
		this.profession = "Monster";
		this.race = "Blob";

		this.stats = new StatsObject(this);

		this.stats.setLevel(1);
		this.stats.setExp(0);
		this.expValue = 15;

		this.stats.setStr(6);
		this.stats.setDex(4);
		this.stats.setWis(3);
		this.stats.setVit(3);
		this.stats.setMaxHP(calculateMaxHP());
		this.stats.setCurrentHP(this.stats.getMaxHP());

		this.isAlive = true;
	}

	public double calculateMaxHP() {

		return 8 + this.stats.getVit() + (2 * this.stats.getLevel());

	}

	public void doTurn() {

		if (this.currentCombat != null
				&& this.currentCombat.getTurnOrder() != null) {
			Random roll = new Random();

			double val = roll.nextDouble();

			if (val >= .6) {
				this.multiplySelf();
			} else {

				int choice = roll
						.nextInt(this.currentCombat.getHeroes().size());
				this.basicAttack(this.currentCombat.getHeroes().get(choice));
			}
		}
	}

	public void multiplySelf() {
		System.out.println(this.name + " begins to split itself in two...");
		GameCharacter blobOffspring = new Blob();
		int currentLevel = this.getStats().getLevel();
		if (currentLevel > 1)
			blobOffspring.getStats().setLevel(currentLevel - 1);

		this.currentCombat.getMonsters().add(blobOffspring);
		this.getSFXLib().playTrack("multiply.wav");
		System.out.println("A " + blobOffspring.getName() + " emerges!");
	}// end multiplySelf

	public void levelUpStats() {

		double dexMod = 1.5;
		double strMod = 1.0;
		double wisMod = 1.0;
		double vitMod = .5;

		this.stats.setDex(this.stats.getDex() + dexMod);
		this.stats.setStr(this.stats.getStr() + strMod);
		this.stats.setWis(this.stats.getWis() + wisMod);
		this.stats.setVit(this.stats.getVit() + vitMod);
	}

	@Override
	public void dies() {

		if (this.currentCombat != null
				&& this.currentCombat.getTurnOrder() != null) {
			this.getSFXLib().playTrack("die.wav");
			System.out.println(this.name
					+ " has been slain, awarding the party " + this.expValue
					+ " experience points!");
			this.isAlive = false;

			for (int i = 0; i < this.currentCombat.getHeroes().size(); i++) {

				GameCharacter character = this.currentCombat.getHeroes().get(i);
				character.stats.setExp(character.stats.getExp() + expValue);
			}

		}

	}
}
