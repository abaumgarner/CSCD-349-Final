package Character;

import java.util.Random;

public class Troll extends Monster {

	//Trolls are difficult monsters to beat, but not quite a boss monster.
	
	public Troll(){
		this.name = "Troll";
	      this.profession = "Monster";
	      this.race = "Troll";
	      
	      this.stats = new StatsObject(this);
	      
	      this.stats.setLevel(1);
	      this.stats.setExp(0);
	      this.expValue = 50;
	      this.goldValue = 20;
	   
	      this.stats.setStr(12);
	      this.stats.setDex(12);
	      this.stats.setWis(8);
	      this.stats.setVit(10);
	      this.stats.setMaxHP(calculateMaxHP());
	      this.stats.setCurrentHP(this.stats.getMaxHP());
	      
	      this.isAlive = true;

	}
	
	@Override
	public void doTurn() {
		
		if(this.currentCombat != null && this.currentCombat.getTurnOrder() != null){
			
			//The troll applies Troll's Blood to itself at the beginning of every turn. It does not stack.
			this.trollsBlood(this);
			Random roll = new Random();
				
			int choice = roll.nextInt(this.currentCombat.getHeroes().size());
			this.basicAttack(this.currentCombat.getHeroes().get(choice));
		}
	}

	@Override
	protected void levelUpStats() {
		
		double vitMod = 2.0;
		double strMod = 1.5;
		double dexMod = 1.5;
		double wisMod = 1.0;
		
		this.stats.setVit(this.stats.getVit()+ vitMod);
		this.stats.setStr(this.stats.getStr()+ strMod);
		this.stats.setDex(this.stats.getDex()+ dexMod);
		this.stats.setWis(this.stats.getWis()+ wisMod);

	}

	@Override
	protected double calculateMaxHP() {
		
		return 12 + this.stats.getVit() + (5 * this.stats.getLevel());
	}
	
	public TrollsBlood trollsBlood(GameCharacter target){
		
		TrollsBlood tb = new TrollsBlood();
		tb.apply(target);
		return tb;
	}
}
