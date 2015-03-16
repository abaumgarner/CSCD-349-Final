package Character;

import java.util.ArrayList;
import java.util.Random;

public class Dragon extends Monster {

	public Dragon() {

		this.name = "Dragon";
		this.profession = "Boss Monster";
		this.race = "Dragon";

		this.stats = new StatsObject(this);

		this.stats.setLevel(1);
		this.stats.setExp(0);
		this.expValue = 400;
		this.goldValue = 1000;
		this.stats.setStr(18);
		this.stats.setDex(15);
		this.stats.setWis(12);
		this.stats.setVit(20);
		this.stats.setMaxHP(calculateMaxHP());
		this.stats.setCurrentHP(this.stats.getMaxHP());

		this.isAlive = true;
	}

	public double calculateMaxHP() {

		return 12 + this.stats.getVit() + (7 * this.stats.getLevel());

	}

	public void doTurn() {

		if (this.currentCombat != null
				&& this.currentCombat.getTurnOrder() != null) {
			Random roll = new Random();

			double val = roll.nextDouble();

			if (val >= .8) {
				this.breatheFire(this.currentCombat.getHeroes());
			}

			else if (val >= .5 && val <= .8) {

				this.tailAttack(this.currentCombat.getHeroes());
			}// end else if

			else {

				int choice = roll
						.nextInt(this.currentCombat.getHeroes().size());
				this.basicAttack(this.currentCombat.getHeroes().get(choice));
			}
		}
	}

	public void breatheFire(ArrayList<GameCharacter> heroes) {

		System.out.println("embers emit from the " + this.getName()
				+ " mouth...");
		this.getSFXLib().playTrack("fire.wav");
		for (GameCharacter target : heroes) {
			
			if(this.calculateHitChance(target)){
				
				double damage = this.calculateMagicDamage();
				
				if (damage <= 0)
					damage = 1;
				
				System.out.println("The " + this.getName() + " breathes fire and "
						+ target.getName() + " receives " + damage + " damage!");
				target.getSFXLib().playTrack("hurt.wav");
				
				if(this.calculateHitChance(target)){
					Burn burn = new Burn();
					burn.apply(target);
				}
				
			}//end if
		}// end for

	}// end breatheFire

	public void tailAttack(ArrayList<GameCharacter> heroes) {

		System.out.println("The " + this.getName()
				+ " sweeps its barbed tail across the floor...");
		this.getSFXLib().playTrack("rumble.wav");
		for (GameCharacter target : heroes) {
			double damage = this.calculateDamage() - 1;
			if (damage <= 0)
				damage = 1;
			System.out.println(target.getName() + "'receives " + damage
					+ " damage!");
			target.getSFXLib().playTrack("hurt");

		}// end for

	}// end breatheFire

	public void levelUpStats() {

		double strMod = 2.0;
		double wisMod = 1.5;
		double dexMod = 1.0;
		double vitMod = 1.0;
		
		this.stats.setStr(this.stats.getStr() + strMod);
		this.stats.setWis(this.stats.getWis() + wisMod);
		this.stats.setDex(this.stats.getDex() + dexMod);
		this.stats.setVit(this.stats.getVit() + vitMod);
	}
}
