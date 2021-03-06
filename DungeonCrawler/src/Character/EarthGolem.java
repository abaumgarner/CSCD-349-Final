package Character;

import java.util.ArrayList;
import java.util.Random;

public class EarthGolem extends Monster {

	public EarthGolem() {

		this.name = "Earth Golem";
		this.profession = "Monster";
		this.race = "Golem";

		this.stats = new StatsObject(this);

		this.stats.setLevel(1);
		this.stats.setExp(0);
		this.expValue = 35;
		this.goldValue = 12;

		this.stats.setStr(12);
		this.stats.setDex(8);
		this.stats.setWis(5);
		this.stats.setVit(12);
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
				this.earthQuake(this.currentCombat.getHeroes());

			} else {

				int choice = roll
						.nextInt(this.currentCombat.getHeroes().size());
				this.basicAttack(this.currentCombat.getHeroes().get(choice));
			}
		}
	}

	public void earthQuake(ArrayList<GameCharacter> heroes) {
		System.out.println(this.getName()
				+ " strikes the ground causing the room to shake...");
		this.getSFXLib().playTrack("rumble.wav");
		for (GameCharacter target : heroes) {
			if (this.calculateHitChance(target)) {

				double damage = this.calculateDamage() - 1;
				if (damage <= 0) {
					damage = 1;
				}
				System.out.println(target.getName() + " is hurt for " + damage
						+ " damage!");
				target.getSFXLib().playTrack("hurt.wav");
				target.stats.setCurrentHP(target.stats.getCurrentHP() - damage);

			}// end if

			else {

				System.out.println(target.getName() + " is  not affected.");
			}// end else

		}// end for
	}// end earthQuake

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
