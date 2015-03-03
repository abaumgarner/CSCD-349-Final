package Character;

import java.util.Scanner;

public class Warrior extends Hero{
	
   private String[] abilities;

   public Warrior(){
  
      this.name = "NULL The Conqueror";
      this.profession = "Warrior";
      this.race = "Null'thraki";
      
      this.level = 1;
      this.exp = 0;
   
      this.str = 12;
      this.dex = 8;
      this.wis = 8;
      this.vit = 12;
      this.maxHP = calculateMaxHP();
      this.currentHP = maxHP;
      
      this.isAlive = true;
      
      this.abilities = new String[]{"Attack","Cripple"};
      
   }
   
   public double calculateMaxHP(){
      
      return 10 + this.vit + (5 * this.level);
   
   }
   
   public Cripple cripple(GameCharacter target){
   
	   if(this.calculateHitChance(target)){
		   
		   
		   double damage = this.calculateDamage()-2;
		   if(damage < 0 ){
			   damage = 0;
		   }
		   
		   System.out.println(this.getName() + " slices the enemy "
					+ target.getName() + " for " + damage + " damage!");
		   target.setCurrentHP(target.getCurrentHP() - damage);
			
		   Cripple cripple = new Cripple();
		   cripple.apply(target);
		   return cripple;
	   }
	   
	   else{
		   
		   System.out.println(this.getName() + " tries to cripple the enemy "
					+ target.getName() + " but misses!");
		   
		   return null;
	   }
      
   
      
   }
   
	public void doTurn(){
		
		Boolean correct = false;
		Scanner kb = new Scanner(System.in);
		String attackInput;
		
		if(this.currentCombat != null && this.currentCombat.getTurnOrder() != null){
			
			while(!correct){
				System.out.println(this.name+ " please make a choice:");
				
				//Print out a menu of all available ability choices.
				for(int i = 0; i < this.abilities.length;i++){
					System.out.print((i+1)+")"+this.abilities[i]+" ");
				}
					System.out.println("");
				
					if(kb.hasNextLine()){
						
						attackInput = kb.nextLine();
						
					}
					else{
						
						kb.close();
						kb = new Scanner(System.in);
						
						attackInput = kb.nextLine();
						
					}
					
				
				if(attackInput.toLowerCase().equals("1") || attackInput.toLowerCase().equals("attack")){
					
					correct = true;
					System.out.println("Who do you wish to attack? (Choose a number)");
					
					
					
					for(int i = 0; i<this.currentCombat.getMonsters().size(); i++){
						
						System.out.println((i+1)+") "+this.currentCombat.getMonsters().get(i).getName()+": "+this.currentCombat.getMonsters().get(i).getCurrentHP()+"/"+this.currentCombat.getMonsters().get(i).getMaxHP()+" HP ");
						
					}
					
					while(true){
						try{
							
							int choice = Integer.parseInt(kb.nextLine());
							this.basicAttack(this.currentCombat.getMonsters().get(choice - 1));
							break;
						}
						catch(Exception e){
							System.out.println("You must enter a valid number.");
						}
					}
				}
				
				else if(attackInput.toLowerCase().equals("2") || attackInput.toLowerCase().equals("cripple")){
					
					correct = true;
					System.out.println("Who do you wish to cripple? (Choose a number)");
					
					for(int i = 0; i<this.currentCombat.getMonsters().size(); i++){
						
						System.out.println((i+1)+") "+this.currentCombat.getMonsters().get(i).getName()+": "+this.currentCombat.getMonsters().get(i).getCurrentHP()+"/"+this.currentCombat.getMonsters().get(i).getMaxHP()+" HP ");
					}
					
					while(true){
						try{
							int choice = Integer.parseInt(kb.nextLine());
							this.cripple(this.currentCombat.getMonsters().get(choice - 1));
							break;
						}
						catch(Exception e){
							System.out.println("You must enter a valid number.");
						}
					}
					
				}
			}//endwhile
		}//endif
		
	}//end doTurn

	public void levelUpStats(){
		
		double strMod = 1.5;
		double dexMod = 1.0;
		double vitMod = 1.0;
		double wisMod = .5;
		
		this.str += strMod;
		this.dex += dexMod;
		this.vit += vitMod;
		this.wis += wisMod;
	}

}