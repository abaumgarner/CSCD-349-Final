package Character;

import java.util.Random;

public class Goblin extends Monster {
	
	public Goblin(){
		
		  this.name = "Goblin";
	      this.profession = "Monster";
	      this.race = "Goblin";
	      
	      this.level = 1;
	      this.exp = 0;
	      this.expValue = 25;
	   
	      this.str = 10;
	      this.dex = 10;
	      this.wis = 8;
	      this.vit = 8;
	      this.maxHP = calculateMaxHP();
	      this.currentHP = maxHP;
	      
	      this.isAlive = true;
	}
	
	public double calculateMaxHP(){
	      
	      return 8 + this.vit + (2 * this.level);
	   
	}
	
	public void doTurn(){
	
		if(this.currentCombat != null && this.currentCombat.getTurnOrder() != null){
			Random roll = new Random();
			
			double val = roll.nextDouble();
			
			if(val >= .8){
				
				int choice = roll.nextInt(this.currentCombat.getHeroes().size());
				this.ankleShank(this.currentCombat.getHeroes().get(choice));
			}
			else{
				
				int choice = roll.nextInt(this.currentCombat.getHeroes().size());
				this.basicAttack(this.currentCombat.getHeroes().get(choice));
			}
		}
	}
	
	public void ankleShank(GameCharacter target){
		
		if(this.calculateHitChance(target)){
			   
			   
			   double damage = this.calculateDamage()-1;
			   if(damage <= 0 ){
				   damage = 1;
			   }
			   
				System.out.println("The "+this.getName() + " shanks "
						+ target.getName() + "'s left ankle for " + damage + " damage!");
			   
				
		   }
		   
		   else{
			   
			   System.out.println(this.getName() + " tries to shank  "
						+ target.getName() + "'s left ankle but misses!");
			   
			   
		   }
		
		if(this.calculateHitChance(target)){
			   
			   
			   double damage = this.calculateDamage()-1;
			   if(damage <= 0 ){
				   damage = 1;
			   }
			   
				System.out.println("The "+this.getName() + " shanks "
						+ target.getName() + "'s right ankle for " + damage + " damage!");
			   
				
		   }
		   
		   else{
			   
			   System.out.println(this.getName() + " tries to shank  "
						+ target.getName() + "'s right ankle but misses!");
			   
			   
		   }
	}
	
	public void levelUpStats(){
		
		double dexMod = 1.5;
		double strMod = 1.0;
		double wisMod = 1.0;
		double vitMod = .5;
		
		this.dex += dexMod;
		this.str += strMod;
		this.wis += wisMod;
		this.vit += vitMod;
	}
	
	@Override
	public void dies(){
		
		if(this.currentCombat != null && this.currentCombat.getTurnOrder() != null){
			
			
			System.out.println(this.name + " has been slain, awarding the party "+this.expValue+" experience points!");
			this.isAlive = false;
			
			for(int i = 0; i < this.currentCombat.getHeroes().size(); i++){
				
				GameCharacter character = this.currentCombat.getHeroes().get(i);
				character.setExp(character.getExp()+expValue);
			}
			
		}
		
	}
}