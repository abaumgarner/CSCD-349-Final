package Character;

import java.util.ArrayList;
import java.util.Random;

public abstract class GameCharacter implements Comparable<GameCharacter>{

	protected String name;
	protected String race;
	protected Boolean isAlive;
	protected String profession;

	protected StatsObject stats;
	protected ArrayList<Effect> effectsList = new ArrayList<Effect>();
	
	protected Combat currentCombat;
	
	protected abstract void levelUpStats();
	protected abstract double calculateMaxHP();
	
	public abstract void doTurn();
	

	public boolean addEffect(Effect newEffect) {

		boolean duplicate = false;
		
		for(int i = 0; i < this.effectsList.size(); i++){
			
			if(this.effectsList.get(i).getName().equals(newEffect.getName())){
				duplicate = true;
			}
		}
		
		if(!duplicate){
			this.effectsList.add(newEffect);
			return true;
		}
		
		return false;
		
	}

	public void removeEffect(Effect expiredEffect) {

		this.effectsList.remove(expiredEffect);
	}

	public String getName() {

		return this.name;

	}

	public void heal(int num) {
		this.stats.heal(num);
	}
	
	public String getRace() {

		return this.race;

	}

	public StatsObject getStats()
	{
		return this.stats;
	}//end getStats
	
	public void setName(String name) {

		this.name = name;
	}
	

	public boolean calculateHitChance(GameCharacter target) {

		// get hit chance modifiers based on attacker's stats, get miss chance
		// based on level differences and
		// enemy stats, then calculate a hit chance (normal chance is 80%).

		double enemyMissFactor = getMissFactor(target);
		double attackerHitFactor = this.getHitFactor();
		double hitChance = attackerHitFactor + enemyMissFactor;

		// roll to see if the attack hits.

		Random roll = new Random();
		double val = 0;
		val = roll.nextDouble();

		if (val < hitChance) {

			return true;
		} else {

			return false;
		}

	}

	public void basicAttack(GameCharacter target) {

		if (this.calculateHitChance(target)) {

			double damage = this.calculateDamage();
			
			if(damage > 0){
				
				System.out.println(this.getName() + " strikes the enemy "
						+ target.getName() + " for " + damage + " damage!");
				target.stats.setCurrentHP(target.stats.getCurrentHP() - damage);
			}
			else{
				//treating 0 damage like a miss.
				System.out.println(this.getName() + " tries to strike the enemy "
						+ target.getName() + " but misses!");
			}
		}

		else {

			System.out.println(this.getName() + " tries to strike the enemy "
					+ target.getName() + " but misses!");
		}

	}
	
	public void magicAttack(GameCharacter target) {

		if (this.calculateHitChance(target)) {

			double damage = this.calculateMagicDamage();
			
			if(damage > 0){
				
				System.out.println(this.getName() + " fires a magical bolt at the enemy "
						+ target.getName() + " for " + damage + " damage!");
				target.stats.setCurrentHP(target.stats.getCurrentHP() - damage);
			}
			else{
				//treating 0 damage like a miss.
				System.out.println(this.getName() + " tries to strike the enemy with magic "
						+ target.getName() + " but misses!");
			}
		}

		else {

			System.out.println(this.getName() + " tries to strike the enemy with magic "
					+ target.getName() + " but misses!");
		}

	}
	
	public double calculateMagicDamage(){
		
		Random roll = new Random();

		double baseDamage = (double) roll.nextInt(4 + this.stats.getLevel());

		double extraDamage = ((this.stats.getWis() - (10 + ((double) this.stats.getLevel() - 1)))) * .5;

		return baseDamage + extraDamage;
	}

	public double calculateDamage() {

		Random roll = new Random();

		double baseDamage = (double) roll.nextInt(4 + this.stats.getLevel());

		double extraDamage = ((this.stats.getStr() - (10 + ((double) this.stats.getLevel() - 1)))) * .5;

		return baseDamage + extraDamage;

	}

	public double getHitFactor() {

		// base hit chance is 80%
		double normalChance = .8;

		// dex affects hit chance by 1.5% per stat above or below normal
		double dexFactor = ((this.stats.getDex() - (10 + (this.stats.getLevel() - 1)))) * .015;

		return normalChance + dexFactor;

	}

	public double getMissFactor(GameCharacter target) {

		double missFactor = 0, lvlFactor = 0;
		double lvlDifference = this.stats.getLevel() - target.stats.getLevel();

		// dex affects dodge chance by .75% per dex above or below normal
		double dexFactor = (target.stats.getDex() - (10 + (target.stats.getLevel() - 1))) * .0075;

		if ((lvlDifference <= 5 && lvlDifference >= 0)
				|| (lvlDifference >= -5 && lvlDifference <= 0)) {

			// level differences affect dodges/miss by 5% per level
			lvlFactor = (lvlDifference * .05);

		}

		// level difference modifiers are capped at 25%
		else if (lvlDifference > 5) {

			lvlFactor = .25;
		}

		else if (lvlDifference < -5) {

			lvlFactor = -.25;
		}

		missFactor = lvlFactor + dexFactor;

		return missFactor;
	}

	public void dies() {

		System.out.println(this.name + " has been slain!");
		this.isAlive = false;
	}

	@Override
	public int compareTo(GameCharacter other) {

		if (this.stats.getInitiative() < other.stats.getInitiative()) {
			return -1;
		} 
		else if (this.stats.getInitiative() > other.stats.getInitiative()) {
			return 1;
		} 
		else{
			
			if(this.stats.getLevel() < other.stats.getInitiative()){
				return -1;
			}
			else if(this.stats.getLevel() > other.stats.getInitiative()){
				return 1;
			}
			else{
				
				if(this.getProfession().toLowerCase().equals("monster") && (!other.getProfession().toLowerCase().equals("monster")) ){
					return 1;
				}
				else if((!this.getProfession().toLowerCase().equals("monster")) && other.getProfession().toLowerCase().equals("monster") ){
					return  -1;
				}
				else{
					return 0;
				}
			}
	    }
	}

	public String getProfession() {

		return this.profession;

	}
	
	
}