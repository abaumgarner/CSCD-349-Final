package Character;

public class Bleed extends Effect {
	
	public Bleed() {

		this.duration = 6;
		this.name = "Bleed";
		this.description = "A lightning quick slice that leaves the enemy gushing blood.";

	}

	@Override
	public void onApply(GameCharacter target) {
		
		this.tick();
		
		String message;
		
		if(this.affected.getProfession().equals("Monster")){
			message = "The "+affected.getRace()+" begins bleeding from its wounds!";
		}
		else{
			message = affected.getName()+" begins bleeding from their wounds!";
		}
		
		System.out.println(message);

	}

	@Override
	public void onRemoval(GameCharacter target) {

		String message;
		
		if(this.affected.getProfession().equals("Monster")){
			message = "The "+affected.getRace()+"'s wounds stop bleeding.";
		}
		else{
			message = affected.getName()+"'s wounds stop bleeding.";
		}
		
		System.out.println(message);

	}
	
	public void tick(){
		
		double damage = 2;
		
		this.affected.stats.setCurrentHP(this.affected.stats.getCurrentHP() - damage);
		
		String message;
		
		if(this.affected.getProfession().equals("Monster")){
			message = "The "+affected.getRace()+"'s wounds bleed, draining "+damage+" health!";
		}
		else{
			message = affected.getName()+"'s wounds bleed, draining "+damage+" health!";
		}
		
		System.out.println(message);
	}
	
	@Override
	public void decreaseDuration(){
		
		this.tick();
		this.duration -= 1;
	}

}
