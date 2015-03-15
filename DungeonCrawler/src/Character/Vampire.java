package Character;

import java.util.Random;

public class Vampire extends Monster {

	public Vampire() {

		this.name = "Vampire";
		this.profession = "Monster";
		this.race = "Vampire";

		this.stats = new StatsObject(this);

		this.stats.setLevel(1);
		this.stats.setExp(0);
		this.expValue = 15;

		this.stats.setStr(11);
		this.stats.setDex(10);
		this.stats.setWis(9);
		this.stats.setVit(9);
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

			if (val >= .5) {

				int choice = roll
						.nextInt(this.currentCombat.getHeroes().size());
				this.drain(this.currentCombat.getHeroes().get(choice));
			} else {

				int choice = roll
						.nextInt(this.currentCombat.getHeroes().size());
				this.basicAttack(this.currentCombat.getHeroes().get(choice));
			}
		}
	}

	public void drain(GameCharacter target) {
		System.out.println(this.getName() + " attempts to suck "
				+ target.getName() + "'s blood...");
		if (this.calculateHitChance(target)) {

			double damage = this.calculateDamage() - 1;
			if (damage <= 0) {
				damage = 1;
			}

			double currentHP = this.getStats().getCurrentHP();
			double maxHP = this.getStats().getMaxHP();

			if (currentHP + damage > maxHP)
				this.getStats().setCurrentHP(maxHP);
			else
				this.getStats().setCurrentHP(currentHP + damage);

			System.out.println(this.name + " drains " + target.getName()
					+ " for " + damage + " hitpoints!");
			target.getSFXLib().playTrack("hurt.wav");
			target.stats.setCurrentHP(target.stats.getCurrentHP() - damage);

		}// end if

		else
			System.out.println(target.getName()
					+ " successfully wards off the " + this.getName());

	}// end drain

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
