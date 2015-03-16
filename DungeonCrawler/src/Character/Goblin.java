package Character;

import java.util.Random;

public class Goblin extends Monster {

	public Goblin() {

		this.name = "Goblin";
		this.profession = "Monster";
		this.race = "Goblin";

		this.stats = new StatsObject(this);

		this.stats.setLevel(1);
		this.stats.setExp(0);
		this.expValue = 25;
		this.goldValue = 8;

		this.stats.setStr(9);
		this.stats.setDex(10);
		this.stats.setWis(8);
		this.stats.setVit(8);
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

			if (val >= .8) {

				int choice = roll
						.nextInt(this.currentCombat.getHeroes().size());
				this.ankleShank(this.currentCombat.getHeroes().get(choice));
			} else {

				int choice = roll
						.nextInt(this.currentCombat.getHeroes().size());
				this.basicAttack(this.currentCombat.getHeroes().get(choice));
			}
		}
	}

	public void ankleShank(GameCharacter target) {

		if (this.calculateHitChance(target)) {

			double damage = this.calculateDamage() - 1;
			if (damage <= 0) {
				damage = 1;
			}

			System.out.println("The " + this.getName() + " shanks "
					+ target.getName() + "'s left ankle for " + damage
					+ " damage!");

		}

		else {

			System.out.println(this.getName() + " tries to shank  "
					+ target.getName() + "'s left ankle but misses!");

		}

		if (this.calculateHitChance(target)) {

			double damage = this.calculateDamage() - 1;
			if (damage <= 0) {
				damage = 1;
			}
			this.getSFXLib().playTrack("hurt.wav");
			System.out.println("The " + this.getName() + " shanks "
					+ target.getName() + "'s right ankle for " + damage
					+ " damage!");
			target.getSFXLib().playTrack("hurt.wav");
		}

		else {

			System.out.println(this.getName() + " tries to shank  "
					+ target.getName() + "'s right ankle but misses!");

		}
	}

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

}
