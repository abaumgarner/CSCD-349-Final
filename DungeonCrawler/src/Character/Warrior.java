package Character;

import java.util.Scanner;

public class Warrior extends Hero {

	private String[] abilities;

	public Warrior() {

		this.name = "NULL The Conqueror";
		this.profession = "Warrior";
		this.race = "Null'thraki";

		this.stats = new StatsObject(this);

		this.stats.setLevel(1);
		this.stats.setExp(0);

		this.stats.setStr(14);
		this.stats.setDex(10);
		this.stats.setWis(8);
		this.stats.setVit(11);
		this.stats.setMaxHP(calculateMaxHP());
		this.stats.setCurrentHP(this.stats.getMaxHP());

		this.isAlive = true;

		this.abilities = new String[] { "Attack", "Cripple" };

	}

	public double calculateMaxHP() {

		return 10 + this.stats.getVit() + (5 * this.stats.getLevel());

	}

	public Cripple cripple(GameCharacter target) {

		if (this.calculateHitChance(target)) {

			double damage = this.calculateDamage() - 2;
			if (damage < 0) {
				damage = 0;
			}

			System.out.println(this.getName() + " slices the enemy "
					+ target.getName() + " for " + damage + " damage!");
			this.getSFXLib().playTrack("attack.wav");
			target.stats.setCurrentHP(target.stats.getCurrentHP() - damage);

			Cripple cripple = new Cripple();
			cripple.apply(target);
			return cripple;
		}

		else {

			System.out.println(this.getName() + " tries to cripple the enemy "
					+ target.getName() + " but misses!");

			return null;
		}

	}

	@Override
	public void doTurn() {

		Boolean correct = false;
		Scanner kb = new Scanner(System.in);
		String attackInput;

		if (this.currentCombat != null
				&& this.currentCombat.getTurnOrder() != null) {

			while (!correct) {
				System.out.println(this.name + " please make a choice:");

				// Print out a menu of all available ability choices.
				for (int i = 0; i < this.abilities.length; i++) {
					System.out.print((i + 1) + ")" + this.abilities[i] + " ");
				}
				System.out.println("");

				if (kb.hasNextLine()) {

					attackInput = kb.nextLine();

				} else {

					kb.close();
					kb = new Scanner(System.in);

					attackInput = kb.nextLine();

				}

				if (attackInput.toLowerCase().equals("1")
						|| attackInput.toLowerCase().equals("attack")) {

					correct = true;
					
					System.out.println("Who do you wish to attack? (Choose a number)");
					promptToAttack();

					while (true) {
						try {

							int choice = Integer.parseInt(kb.nextLine());
							if(this.currentCombat.getMonsters().get(choice - 1).isAlive){
								
								this.basicAttack(this.currentCombat.getMonsters()
										.get(choice - 1));
								break;
							}
							else{
								throw new Exception();
							}
						} catch (Exception e) {
							System.out
									.println("You must enter a valid number.");
						}
					}
				}

				else if (attackInput.toLowerCase().equals("2")
						|| attackInput.toLowerCase().equals("cripple")) {

					correct = true;
					System.out
							.println("Who do you wish to cripple? (Choose a number)");

					promptToAttack();

					while (true) {
						try {
							int choice = Integer.parseInt(kb.nextLine());
							if(this.currentCombat.getMonsters().get(choice - 1).isAlive){
								
								this.cripple(this.currentCombat.getMonsters().get(
										choice - 1));
								break;
							}
							else{
								throw new Exception();
							}
						} catch (Exception e) {
							System.out
									.println("You must enter a valid number.");
						}
					}

				}
			}// endwhile
		}// endif
	}// end doTurn

	@Override
	public void levelUpStats() {

		double strMod = 1.5;
		double dexMod = 1.0;
		double vitMod = 1.0;
		double wisMod = .5;

		this.stats.setStr(this.stats.getStr() + strMod);
		this.stats.setDex(this.stats.getDex() + dexMod);
		this.stats.setVit(this.stats.getVit() + vitMod);
		this.stats.setWis(this.stats.getWis() + wisMod);

	}

}