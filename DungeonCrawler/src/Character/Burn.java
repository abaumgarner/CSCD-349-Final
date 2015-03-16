package Character;

public class Burn extends Effect {
	
	public Burn(){
		this.duration = 5;
		this.name = "Burn";
		this.description = "A fiery blaze that deals damage to the target and slowly diminishes over time.";
	}

	@Override
	public void onApply(GameCharacter target) {
		
		System.out.println(this.affected.getName()+" has been set on fire!");

	}

	@Override
	public void onRemoval(GameCharacter target) {
		
		System.out.println("The flames covering "+this.affected.getName()+" have been extinguished!");

	}
	
	public void tick(){
		
		double damage = (this.duration + (this.affected.stats.getLevel() - 1));
	}
	
	@Override
	public void decreaseDuration() {

		this.tick();
		this.duration -= 1;
	}

}

	