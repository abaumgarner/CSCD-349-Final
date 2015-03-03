package Character;

import java.util.ArrayList;
import java.util.Collections;

public class Combat {

	private Boolean combatStarted;
	private int roundCount;
	private Round currentRound;
	private ArrayList<GameCharacter> turnOrder;
	private ArrayList<GameCharacter> heroes;
	private ArrayList<GameCharacter> monsters;

	public ArrayList<GameCharacter> getTurnOrder() {
		return this.turnOrder;
	}

	public Combat(ArrayList<GameCharacter> heroes, ArrayList<GameCharacter> monsters) {
		setRoundCount(0);
		this.heroes = heroes;
		this.monsters = monsters;

	}
	
	public ArrayList<GameCharacter> getMonsters(){
		return this.monsters;
	}
	
	public ArrayList<GameCharacter> getHeroes(){
		return this.heroes;
	}

	public Boolean start() {
		if (this.heroes.isEmpty() || this.monsters.isEmpty()) {
			System.out.println("You need heroes AND monsters to start combat.");
			return false;
		} else {

			
			addCombatToCharacters(this);
			organizeTurns();
			combatStarted = true;
			return true;
		}
	}

	public Boolean end() {
		if (combatStarted) {
			combatStarted = false;
			return true;
		} else {
			System.out.println("Combat has not started, so you can't end it.");
			return true;
		}

	}

	public void run() {
		
		this.start();
		
		while(stillAlive(heroes) && stillAlive(monsters)){
			
			this.currentRound = new Round(this);
			System.out.println("---------- ROUND "+(this.roundCount+1)+"! ----------");
			this.currentRound.start();
		}
		
		this.end();

	}
	
	public boolean stillAlive(ArrayList<GameCharacter> gameCharacters){
		
		int dead = 0;
		if(gameCharacters.isEmpty()){
			return false;
		}
		else{
			//iterate through the list and count how many are dead
			for(GameCharacter GameCharacter : gameCharacters){
				if(!GameCharacter.isAlive){
					
					dead++;
				}
			}
			//if ALL of the GameCharacters in this list are dead, return false
			if(dead == gameCharacters.size()){
				return false;
			}
			else{
				return true;
			}
		}
	}

	public void addCombatToCharacters(Combat combat){
		for(GameCharacter hero : this.heroes){
			hero.currentCombat = combat;
		}
		
		for(GameCharacter monster : this.monsters){
			monster.currentCombat = combat;
		}
	}
	
	public void checkForDeaths() {

		for (int i = 0; i < this.heroes.size(); i++) {
			if (this.heroes.get(i).currentHP <= 0) {
				this.heroes.get(i).dies();
				this.heroes.remove(i);
				
				
				for(int j = 0; j < this.turnOrder.size(); j++){
					
					this.turnOrder.remove(j);
					break;
					
				}
			}
		}

		for (int i = 0; i < this.monsters.size(); i++) {
			if (this.monsters.get(i).currentHP <= 0) {
				this.monsters.get(i).dies();
				this.monsters.remove(i);
				
				
				for(int j = 0; j < this.turnOrder.size(); j++){
					
					this.turnOrder.remove(j);
					break;
				}
			}
		}

	}

	public void updateEffects() {

		for (int i = 0; i<this.heroes.size(); i++) {

			for (int j = 0; j<this.heroes.get(i).effectsList.size(); j++) {

				this.heroes.get(i).effectsList.get(j).decreaseDuration();
				if (this.heroes.get(i).effectsList.get(j).duration == 0) {
					this.heroes.get(i).effectsList.get(j).remove(this.heroes.get(i));
				}
			}
		}

		for (int i = 0; i<this.monsters.size(); i++) {

			for (int j = 0; j<this.monsters.get(i).effectsList.size(); j++) {

				this.monsters.get(i).effectsList.get(j).decreaseDuration();
				if (this.monsters.get(i).effectsList.get(j).duration == 0) {
					
					this.monsters.get(i).effectsList.get(j).remove(this.monsters.get(i));
				}
			}
		}
	}

	public void organizeTurns() {

		turnOrder = new ArrayList<GameCharacter>();

		turnOrder.addAll(this.heroes);
		turnOrder.addAll(this.monsters);

		Collections.sort(turnOrder);
	}

	public void setInitiatives() {

		if (this.turnOrder != null) {
			for (GameCharacter c : this.turnOrder) {
				c.calculateInitiative();
			}
			
			Collections.sort(turnOrder);
		} else {
			System.out.println("turnOrder is null, can't calc initiatives.");
		}
	}
	
	public void sortInitiatives(){
		
		if (this.turnOrder != null) {
			Collections.sort(turnOrder);
		}
	}

	public int getRoundCount() {
		return roundCount;
	}

	public void setRoundCount(int roundCount) {
		this.roundCount = roundCount;
	}
	
}
