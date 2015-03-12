package Character;

public class Freeze extends Effect {

	public Freeze(){
		
		this.duration = 6;
		this.name = "Freeze";
		this.name = "A blast of frozen magic that leaves the target frozen solid!";
	}
	@Override
	public void onApply(GameCharacter target) {

		for(int i = 0; i < target.currentCombat.getTurnOrder().size(); i++){
			
			//remove the affected enemy from turnOrder, making them lose turns.
			
			if(target.currentCombat.getTurnOrder().get(i).equals(target)){
				target.currentCombat.getTurnOrder().remove(i);
			}
		}

		String message;
		
		if(this.affected.getProfession().equals("Monster")){
			message = "The "+affected.getRace()+" has been frozen solid!";
		}
		else{
			message = affected.getName()+" has been frozen solid!";
		}
		
		System.out.println(message);
	}
	
	@Override
	public void decreaseDuration(){
		
		this.duration -= 1;
		
		if(this.duration == 4){
			
			affected.currentCombat.getTurnOrder().add(affected);
			
			String message;
			
			if(this.affected.getProfession().equals("Monster")){
				message = "The "+affected.getRace()+" shatters the ice and joins the fray!";
			}
			else{
				message = affected.getName()+" shatters the ice and joins the fray!";
			}
		}
	}

	@Override
	public void onRemoval(GameCharacter target) {
		
		String message;
		
		if(this.affected.getProfession().equals("Monster")){
			message = "The "+affected.getRace()+" is vulnerable to freezing again!";
		}
		else{
			message = affected.getName()+" is vulnerable to freezing again!";
		}
		
		System.out.println(message);

	}

}
