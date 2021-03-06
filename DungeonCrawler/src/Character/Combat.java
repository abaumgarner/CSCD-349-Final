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
	private HUD hud;

	public ArrayList<GameCharacter> getTurnOrder() {
		return this.turnOrder;
	}

	public Combat(ArrayList<GameCharacter> heroes,
			ArrayList<GameCharacter> monsters) {
		setRoundCount(0);
		this.heroes = heroes;
		this.monsters = monsters;
		this.setHud(new HUD(this));

	}

	public ArrayList<GameCharacter> getMonsters() {
		return this.monsters;
	}

	public ArrayList<GameCharacter> getHeroes() {
		return this.heroes;
	}

	public Boolean start() {
		if (this.heroes.isEmpty() || this.monsters.isEmpty()) {
			System.out.println("You need heroes AND monsters to start combat.");
			return false;
		} else {

			// add the combat to characters and get turnOrder setup.
			addCombatToCharacters(this);
			organizeTurns();
			combatStarted = true;
			return true;
		}
	}

	public Boolean end() {
		if (combatStarted) {
			combatStarted = false;

			for (int i = 0; i < this.heroes.size(); i++) {

				// remove all the combat from characters, and null out all
				// effects on characters
				this.heroes.get(i).currentCombat = null;
				this.heroes.get(i).effectsList = new ArrayList<Effect>();
			}
			
			this.heroes = null;
			this.monsters = null;

			return true;

		} else {
			System.out.println("Combat has not started, so you can't end it.");
			return true;
		}

	}

	public void run() {

		this.start();

		// while each 'side' has a living member, keep doing turns.
		while (stillAlive(heroes) && stillAlive(monsters)) {

			this.currentRound = new Round(this);
			System.out.println("\n---------- ROUND " + (this.roundCount + 1)
					+ "! ----------");
			this.currentRound.start();
		}

		this.end();

	}

	public boolean stillAlive(ArrayList<GameCharacter> gameCharacters) {

		int dead = 0;
		if (gameCharacters.isEmpty()) {
			return false;
		} else {
			// iterate through the list and count how many are dead
			for (GameCharacter GameCharacter : gameCharacters) {
				if (!GameCharacter.isAlive) {

					dead++;
				}
			}
			// if ALL of the GameCharacters in this list are dead, return false
			if (dead == gameCharacters.size()) {
				return false;
			} else {
				return true;
			}
		}
	}

	public void addCombatToCharacters(Combat combat) {

		for (GameCharacter hero : this.heroes) {
			hero.currentCombat = combat;
		}

		for (GameCharacter monster : this.monsters) {
			monster.currentCombat = combat;
		}
	}

	public void checkForDeaths() {

		//check through the heroes and monsters, to see if their HP is 0 or less.
		for (int i = 0; i < this.heroes.size(); i++) {
			if (this.heroes.get(i).isAlive && this.heroes.get(i).stats.getCurrentHP() <= 0) {
				
				//make the hero die, then remove them from the turn order
				this.heroes.get(i).dies();
				this.heroes.remove(i);
			}
		}
		//same thing but for monsters.
		for (int i = 0; i < this.monsters.size(); i++) {
			if (this.monsters.get(i).isAlive && this.monsters.get(i).stats.getCurrentHP() <= 0) {
				
				this.monsters.get(i).dies();
				this.monsters.remove(i);
			}
		}
		
		for(int i = 0; i < this.turnOrder.size(); i++){
			if(!this.turnOrder.get(i).isAlive){
				this.turnOrder.remove(i);
			}
		}

	}

	public void updateEffects() {

		// check all the effects on characters in this combat, then decrement
		// their duration. remove them if their duration == zero.

		for (int i = 0; i < this.heroes.size(); i++) {

			for (int j = 0; j < this.heroes.get(i).effectsList.size(); j++) {

				this.heroes.get(i).effectsList.get(j).decreaseDuration();
				if (this.heroes.get(i).effectsList.get(j).duration == 0) {
					this.heroes.get(i).effectsList.get(j).remove(
							this.heroes.get(i));
				}
			}
		}

		for (int i = 0; i < this.monsters.size(); i++) {

			for (int j = 0; j < this.monsters.get(i).effectsList.size(); j++) {

				this.monsters.get(i).effectsList.get(j).decreaseDuration();
				if (this.monsters.get(i).effectsList.get(j).duration == 0) {

					this.monsters.get(i).effectsList.get(j).remove(
							this.monsters.get(i));
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

		// calculate new initiative values for everyone in the combat, then sort
		// turnOrder

		if (this.turnOrder != null) {
			for (GameCharacter c : this.turnOrder) {
				c.stats.calculateInitiative();
			}

			Collections.sort(turnOrder);
		} else {
			System.out.println("turnOrder is null, can't calc initiatives.");
		}
	}

	public void sortInitiatives() {

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

	public HUD getHud() {
		return this.hud;
	}

	public void setHud(HUD hud) {
		this.hud = hud;
	}

}
