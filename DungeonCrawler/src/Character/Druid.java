package Character;

import java.util.Random;
import java.util.Scanner;

public class Druid extends Hero {

	private String[] abilities;

	public Druid() {

		this.name = "Elder Greenthumb";
		this.profession = "Druid";
		this.race = "Old Person";

		this.stats = new StatsObject(this);

		this.stats.setLevel(1);
		this.stats.setExp(0);

		this.stats.setStr(10);
		this.stats.setDex(8);
		this.stats.setWis(12);
		this.stats.setVit(10);
		this.stats.setMaxHP(calculateMaxHP());
		this.stats.setCurrentHP(this.stats.getMaxHP());

		this.isAlive = true;

		this.abilities = new String[] { "Attack", "Mending Touch" };

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
						|| attackInput.toLowerCase().equals("mending touch")) {

					correct = true;
					System.out
							.println("Who do you wish to mend? (Choose a number)");

					for (int i = 0; i < this.currentCombat.getHeroes().size(); i++) {

						System.out.println((i + 1)
								+ ") "
								+ this.currentCombat.getHeroes().get(i)
										.getName()
								+ ": "
								+ this.currentCombat.getHeroes().get(i).stats
										.getCurrentHP()
								+ "/"
								+ this.currentCombat.getHeroes().get(i).stats
										.getMaxHP() + " HP ");
					}

					while (true) {
						try {
							int choice = Integer.parseInt(kb.nextLine());
							this.mendingTouch(this.currentCombat.getHeroes()
									.get(choice - 1));
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

	public void mendingTouch(GameCharacter target) {

		Random roll = new Random();

		double healing = this.stats.getWis()
				- (10 + (1 - this.stats.getLevel()));

		double bonus = roll.nextInt(3 + this.stats.getLevel()) + 1;

		// heal for the amount, or set the target's hp to MAX, whichever is
		// less.
		target.stats.setCurrentHP(Math.min((target.stats.getCurrentHP()
				+ healing + bonus), target.stats.getMaxHP()));
		System.out.println(this.getName() + " mends " + target.getName()
				+ "'s wounds, restoring " + (healing + bonus) + "HP.");
		sfxLib.playTrack("heal.wav");
	}

	@Override
	protected void levelUpStats() {

		double wisMod = 1.5;
		double strMod = 1.0;
		double vitMod = 1.0;
		double dexMod = .5;

		this.stats.setStr(this.stats.getWis() + wisMod);
		this.stats.setDex(this.stats.getStr() + strMod);
		this.stats.setVit(this.stats.getVit() + vitMod);
		this.stats.setWis(this.stats.getDex() + dexMod);

	}

	@Override
	protected double calculateMaxHP() {
		return 8 + this.stats.getVit() + (3 * this.stats.getLevel());
	}

}
