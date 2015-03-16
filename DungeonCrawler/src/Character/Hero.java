package Character;

abstract class Hero extends GameCharacter {

	public abstract void doTurn();
	
	protected void promptToAttack(){
		
		for (int i = 0; i < this.currentCombat.getMonsters().size(); i++) {
		
			if(this.currentCombat.getMonsters().get(i).isAlive){
				
				System.out.println((i + 1)
						+ ") "
						+ this.currentCombat.getMonsters().get(i)
								.getName()
						+ ": "
						+ this.currentCombat.getMonsters().get(i).stats
								.getCurrentHP()
						+ "/"
						+ this.currentCombat.getMonsters().get(i).stats
								.getMaxHP() + " HP ");
			}
		}
	}
	
	protected void promptToHeal(){
		
		for (int i = 0; i < this.currentCombat.getHeroes().size(); i++) {
			
			if(this.currentCombat.getHeroes().get(i).isAlive){
				
				System.out.println((i + 1)
						+ ") "
						+ this.currentCombat.getHeroes().get(i)
								.getName()
						+ ": "
						+ this.currentCombat.getHeroes().get(i).stats
								.getCurrentHP()
						+ "/"
						+ this.currentCombat.getHeroes().get(i).stats
								.getMaxHP() + " HP ");
			}
		}
		
	}
}