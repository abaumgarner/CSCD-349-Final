
public class Cripple extends Effect {
	
	public Cripple(){
		
		this.duration = 4;
		this.name = "Cripple";
		this.description = "A gruesome blow that hinders and slows enemies.";
		
	}

	@Override
	public void onApply(Character target) {
		
		double currentInit = target.getInitiative();
		target.setInitiative(currentInit-8);
		
		System.out.println("The "+target.getName()+" has been crippled!");
	}

	@Override
	public void onRemoval(Character target) {
		
		double currentInit = target.getInitiative();
		target.setInitiative(currentInit+8);
		System.out.println("The "+target.getName()+" is no longer crippled!");
	}

}
