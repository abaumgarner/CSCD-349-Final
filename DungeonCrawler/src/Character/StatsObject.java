package Character;

import java.util.Random;

public class StatsObject {

	private GameCharacter owner;
	private double str, dex, wis, vit, initiative;
	private double maxHP, currentHP;
	private int level, exp;
	/*
	 * protected int[] expTable = { 0, 200, 440, 728, 1074, 1489, 1987, 2584,
	 * 3301, 4161 };
	 */

	protected int[] expTable = { 50, 100, 200, 400, 600, 800, 1000, 1200, 1400,
			1600 };

	public StatsObject(GameCharacter owner) {
		this.owner = owner;
	}

	public double getCurrentHP() {

		return this.currentHP;
	}

	public double getMaxHP() {

		return this.maxHP;
	}

	public double getStr() {

		return this.str;
	}

	public double getDex() {

		return this.dex;
	}

	public double getWis() {

		return this.wis;
	}

	public double getVit() {

		return this.vit;
	}

	public int getLevel() {

		return this.level;
	}

	public int getExp() {

		return this.exp;
	}

	public double getInitiative() {
		return this.initiative;
	}

	public void setCurrentHP(double hp) {

		this.currentHP = hp;
	}

	public void heal(int num) {

		if ((this.currentHP + num) > this.maxHP)
			this.currentHP = this.maxHP;
		else
			this.currentHP += num;
	}

	public void setMaxHP(double hp) {

		this.maxHP = hp;
	}

	public void setStr(double stat) {

		this.str = stat;
	}

	public void setDex(double stat) {

		this.dex = stat;
	}

	public void setWis(double stat) {

		this.wis = stat;
	}

	public void setVit(double stat) {

		this.vit = stat;
	}

	public void setLevel(int lvl) {

		if (this.level == 0) {
			this.level = lvl;
		} else if (lvl > this.level) {

			this.level = lvl;
			owner.levelUpStats();
			this.maxHP = owner.calculateMaxHP();
			this.exp = expTable[this.level - 1];
		} else if (lvl == this.level) {
			System.out.println("This character is all ready level "
					+ this.level);
		} else {
			System.out.println("You can't de-level a character.");
		}
	}

	public void setExp(int exp) {

		this.exp = exp;
		this.checkExp();
	}

	public void checkExp() {

		if (expTable[this.level] <= this.exp) {
			this.setLevel(this.level + 1);
		}
	}

	public void setInitiative(double init) {

		this.initiative = init;

		if (owner.currentCombat != null) {
			owner.currentCombat.sortInitiatives();
		}
	}

	public void calculateInitiative() {

		Random roll = new Random();

		double base = (double) roll.nextInt(5);

		double bonus = (double) roll.nextInt(this.level);

		double dexBonus = ((this.dex - (10 + ((double) this.level - 1))));

		if (dexBonus > 0) {

			this.initiative = (base + bonus + dexBonus);
		}

		else {

			this.initiative = (base + bonus);
		}

	}
}
