package Character;

import java.util.Scanner;

public class Mage extends Hero {

	private String[] abilities;

	public Mage() {

		this.name = "Dangalf The Off-White";
		this.profession = "Mage";
		this.race = "Maiar";

		this.stats = new StatsObject(this);

		this.stats.setLevel(1);
		this.stats.setExp(0);

		this.stats.setStr(10);
		this.stats.setDex(10);
		this.stats.setWis(12);
		this.stats.setVit(8);
		this.stats.setMaxHP(calculateMaxHP());
		this.stats.setCurrentHP(this.stats.getMaxHP());

		this.isAlive = true;

		this.abilities = new String[] { "Attack", "Freeze" };

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
					System.out
							.println("Who do you wish to attack? (Choose a number)");

					for (int i = 0; i < this.currentCombat.getMonsters().size(); i++) {

						System.out.println((i + 1)
								+ ") "
								+ this.currentCombat.getMonsters().get(i)
										.getName()
								+ ": "
								+ this.currentCombat.getMonsters().get(i).stats
										.getCurrentHP()
								+ "/"
								+ this.currentCombat.getMonsters().get(i).stats
										.getMaxHP() + " HP ");

					}

					while (true) {
						try {

							int choice = Integer.parseInt(kb.nextLine());
							this.magicAttack(this.currentCombat.getMonsters()
									.get(choice - 1));
							break;
						} catch (Exception e) {
							System.out
									.println("You must enter a valid number.");
						}
					}
				}

				else if (attackInput.toLowerCase().equals("2")
						|| attackInput.toLowerCase().equals("freeze")) {

					correct = true;
					System.out
							.println("Who do you wish to cripple? (Choose a number)");

					for (int i = 0; i < this.currentCombat.getMonsters().size(); i++) {

						System.out.println((i + 1)
								+ ") "
								+ this.currentCombat.getMonsters().get(i)
										.getName()
								+ ": "
								+ this.currentCombat.getMonsters().get(i).stats
										.getCurrentHP()
								+ "/"
								+ this.currentCombat.getMonsters().get(i).stats
										.getMaxHP() + " HP ");
					}

					while (true) {
						try {
							int choice = Integer.parseInt(kb.nextLine());
							this.freeze(this.currentCombat.getMonsters().get(
									choice - 1));
							break;
						} catch (Exception e) {
							System.out
									.println("You must enter a valid number.");
						}
					}

				}
			}// endwhile
		}// endif
	}

	public Freeze freeze(GameCharacter target) {

		if (this.calculateHitChance(target)) {

			double damage = this.calculateMagicDamage() - 1;
			if (damage < 0) {
				damage = 0;
			}

			System.out.println(this.getName()
					+ " sends a freezing blast at the enemy "
					+ target.getName() + " for " + damage + " damage!");
			this.getSFXLib().playTrack("freeze.wav");
			target.stats.setCurrentHP(target.stats.getCurrentHP() - damage);

			Freeze freeze = new Freeze();
			freeze.apply(target);
			return freeze;
		}

		else {

			System.out.println(this.getName() + " tries to freeze the enemy "
					+ target.getName() + " but misses!");

			return null;
		}

	}

	@Override
	protected void levelUpStats() {

		double wisMod = 1.5;
		double dexMod = 1.0;
		double strMod = 1.0;
		double vitMod = .5;

		this.stats.setStr(this.stats.getWis() + wisMod);
		this.stats.setDex(this.stats.getDex() + dexMod);
		this.stats.setVit(this.stats.getStr() + strMod);
		this.stats.setWis(this.stats.getVit() + vitMod);

	}

	@Override
	protected double calculateMaxHP() {
		return 8 + this.stats.getVit() + (3 * this.stats.getLevel());
	}

}
