package Character;

public class Round {

	private Combat currentCombat;

	public Round(Combat c) {

		this.currentCombat = c;
	}

	public void start() {
		if (currentCombat != null) {
			// set up the initiatives and turn orders
			this.currentCombat.setInitiatives();

			// iterate through characters in combat and give them a turn
			if (this.currentCombat.getTurnOrder() != null) {

				

				for (int i = 0; i < this.currentCombat.getTurnOrder().size(); i++) {
					// do turns
					currentCombat.getHud().printHUD();
					
					if (currentCombat.stillAlive(currentCombat.getHeroes())
							&& currentCombat.stillAlive(currentCombat
									.getMonsters())) {

						if (this.currentCombat.getTurnOrder().get(i).isAlive) {

							this.currentCombat.getTurnOrder().get(i).doTurn();
							
						}
					}
				}

				// decrement effect durations
				this.currentCombat.updateEffects();
				this.currentCombat.checkForDeaths();
				// increment round count and finish
				this.currentCombat.setRoundCount(this.currentCombat
						.getRoundCount() + 1);
			}
		}
	}

}
