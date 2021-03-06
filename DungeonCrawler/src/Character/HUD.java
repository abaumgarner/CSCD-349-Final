package Character;

public class HUD {

	private Combat currentCombat;

	public HUD(Combat c) {

		this.currentCombat = c;
	}

	public void printHUD() {
		
		System.out.println("");
		
		System.out.printf("Party: %-60sMonsters:\n", "");
		for (int i = 0; i < Math.max(this.currentCombat.getHeroes().size(),
				this.currentCombat.getMonsters().size()); i++) {

			String heroString = "";
			String monsterString = "";
			if (i < this.currentCombat.getHeroes().size()) {

				if(this.currentCombat.getHeroes().get(i).isAlive){
					heroString = printHero(this.currentCombat.getHeroes().get(i));
				}
			}

			if (i < this.currentCombat.getMonsters().size()) {
				
				if(this.currentCombat.getMonsters().get(i).isAlive){
					monsterString = printMonster(this.currentCombat.getMonsters()
							.get(i));
				}
			}

			if (heroString.isEmpty()) {
				heroString = String.format("%-57s", heroString);
			}

			System.out.printf(heroString + "%-10s" + monsterString, "");
			System.out.println();

		}

		System.out.print("\n\n");
	}

	public String printHero(GameCharacter character) {

		String temp;

		temp = String.format("%-30s Level: %-3s HP: %-5s/%5s",
				"[" + character.getName() + "]", character.stats.getLevel(),
				character.stats.getCurrentHP(), character.stats.getMaxHP());
		return temp;
	}

	public String printMonster(GameCharacter character) {

		String temp;

		temp = String.format("%-30s Level: %-3s HP: %-5s/%5s",
				"[" + character.getName() + "]", character.stats.getLevel(),
				character.stats.getCurrentHP(), character.stats.getMaxHP());
		return temp;
	}
}
