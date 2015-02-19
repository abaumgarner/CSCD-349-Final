package Character;

import java.util.ArrayList;
import java.util.Collections;


public class Combat {
	
	private Boolean combatStarted;
	private int roundCount;
	private ArrayList<Character> turnOrder;
	
	public ArrayList<Character> getTurnOrder(){
		return this.turnOrder;
	}
	
	public Combat(ArrayList<Character> Heroes, ArrayList<Character> Monsters){
		roundCount = 0;
		if(this.start(Heroes, Monsters)){
			this.run(Heroes, Monsters);
		}
		
	}
	
	public Boolean start(ArrayList<Character> Heroes, ArrayList<Character> Monsters){
		if(Heroes.isEmpty() || Monsters.isEmpty()){
			
			return false;
		}
		else{
			
			combatStarted = true;
			return true;
		}
	}
	
	public Boolean end(){
		if(combatStarted){
			combatStarted = false;
			return true;
		}
		else{
			System.out.println("Combat has not started, so you can't end it.");
			return true;
		}
			
		
	}
	
	public void run(ArrayList<Character> Heroes, ArrayList<Character> Monsters){
		
		
	}
	
	public void checkForDeaths(ArrayList<Character> Heroes, ArrayList<Character> Monsters){
		
		for(Character hero : Heroes){
			if(hero.currentHP <= 0){
				Heroes.remove(hero);
				hero.dies();
			}
		}
		
		for(Character monster : Monsters){
			if(monster.currentHP <= 0){
				Heroes.remove(monster);
				monster.dies();
			}
		}
				
			
	}
		
	public void updateEffects(ArrayList<Character> Heroes, ArrayList<Character> Monsters){
		
		for(Character hero : Heroes){
			
			for(Effect effect : hero.effectsList){
				
				effect.decreaseDuration();
				if(effect.duration == 0){
					effect.remove(hero);
				}
			}
		}
		
		for(Character monster : Monsters){
			
			for(Effect effect : monster.effectsList){
				
				effect.decreaseDuration();
				if(effect.duration == 0){
					effect.remove(monster);
				}
			}
		}
	}
	
	public void organizeTurns(ArrayList<Character> Heroes, ArrayList<Character> Monsters){
			
			turnOrder = new ArrayList<Character>();
		
			turnOrder.addAll(Heroes);
			turnOrder.addAll(Monsters);
			
			Collections.sort(turnOrder);
	}
	
	public void setInitiatives(){
		
		if(this.turnOrder != null){
			for(Character c : this.turnOrder){
				c.calculateInitiative();
			}
		}
		else{
			System.out.println("turnOrder is null, can't calc initiatives.");
		}
	}

}
