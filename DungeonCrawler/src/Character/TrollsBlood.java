package Character;

public class TrollsBlood extends Effect {
	
	public TrollsBlood(){
		
		this.duration = 9999;
		this.name = "Troll's Blood";
		this.description = "The blood of a troll is powered by ancient magic that heals the troll slowly over time.";
	}

	@Override
	public void onApply(GameCharacter target) {
		
		
	}

	@Override
	public void onRemoval(GameCharacter target) {
		

	}
	
	public void tick(){
		
		double health = (1 + this.affected.stats.getVit()) - (10 + (this.affected.stats.getLevel() - 1));
		
		this.affected.stats.setCurrentHP(this.affected.stats.getCurrentHP()+health);
		
		System.out.println("The "+this.affected.getName()+"'s magical blood heals it for "+health+" hit points!");
	}
	
	@Override
	public void decreaseDuration(){
		
		this.tick();
		this.duration -= 1;
	}

}
