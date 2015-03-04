package Character;

import java.util.Scanner;

public class Rogue extends Hero {

   private String[] abilities;

   public Rogue(){
  
      this.name = "Rougelol";
      this.profession = "Rogue";
      this.race = "Trollolol";
      
      this.stats = new StatsObject(this);
      
      this.stats.setLevel(1);
      this.stats.setExp(0);
   
      this.stats.setStr(10);
      this.stats.setDex(12);
      this.stats.setWis(10);
      this.stats.setVit(8);
      this.stats.setMaxHP(calculateMaxHP());
      this.stats.setCurrentHP(this.stats.getMaxHP());
      
      this.isAlive = true;
      
      this.abilities = new String[]{"Attack","Lacerate"};
      
   }
   
   public double calculateMaxHP(){
      
      return 8 + this.stats.getVit() + (3 * this.stats.getLevel());
   
   }
   
   public Bleed lacerate(GameCharacter target){
   
	   if(this.calculateHitChance(target)){
		   
		   
		   double damage = this.calculateDamage()-2;
		   if(damage < 0 ){
			   damage = 0;
		   }
		   
		   System.out.println(this.getName() + " cuts a deep wound in the enemy "
					+ target.getName() + " for " + damage + " damage!");
		   target.stats.setCurrentHP(target.stats.getCurrentHP() - damage);
			
		   Bleed bleed = new Bleed();
		   bleed.apply(target);
		   return bleed;
	   }
	   
	   else{
		   
		   System.out.println(this.getName() + " tries to lacerate the enemy "
					+ target.getName() + " but misses!");
		   
		   return null;
	   }
      
   
      
   }
   
   @Override 
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
						
						System.out.println((i+1)+") "+this.currentCombat.getMonsters().get(i).getName()+": "+this.currentCombat.getMonsters().get(i).stats.getCurrentHP()+"/"+this.currentCombat.getMonsters().get(i).stats.getMaxHP()+" HP ");
						
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
				
				else if(attackInput.toLowerCase().equals("2") || attackInput.toLowerCase().equals("lacerate")){
					
					correct = true;
					System.out.println("Who do you wish to lacerate? (Choose a number)");
					
					for(int i = 0; i<this.currentCombat.getMonsters().size(); i++){
						
						System.out.println((i+1)+") "+this.currentCombat.getMonsters().get(i).getName()+": "+this.currentCombat.getMonsters().get(i).stats.getCurrentHP()+"/"+this.currentCombat.getMonsters().get(i).stats.getMaxHP()+" HP ");
					}
					
					while(true){
						try{
							int choice = Integer.parseInt(kb.nextLine());
							this.lacerate(this.currentCombat.getMonsters().get(choice - 1));
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

	@Override
	public void levelUpStats(){
		
		double strMod = 1.5;
		double dexMod = 1.0;
		double vitMod = 1.0;
		double wisMod = .5;
		
		this.stats.setStr(this.stats.getStr()+ strMod);
		this.stats.setDex(this.stats.getDex()+ dexMod);
		this.stats.setVit(this.stats.getVit()+ vitMod);
		this.stats.setWis(this.stats.getWis()+ wisMod);
		
	}
}