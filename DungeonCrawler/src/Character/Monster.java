package Character;

import java.util.ArrayList;

public abstract class Monster extends GameCharacter {

	protected int expValue;
	protected int goldValue;

	public abstract void doTurn();
	
	@Override
	public void dies() {

		if (this.currentCombat != null
				&& this.currentCombat.getTurnOrder() != null) {
			this.getSFXLib().playTrack("die.wav");
			System.out.println(this.name
					+ " has been slain, awarding the party " + this.expValue
					+ " experience points and "+ this.goldValue +" gold!");
			
			this.isAlive = false;
			this.effectsList = new ArrayList<Effect>();
			
			for (int i = 0; i < this.currentCombat.getHeroes().size(); i++) {

				GameCharacter character = this.currentCombat.getHeroes().get(i);
				character.stats.setExp(character.stats.getExp() + expValue);
				character.addGold(this.goldValue);
			}

		}

	}
}