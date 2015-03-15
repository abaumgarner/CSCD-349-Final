package Character;

public class Cripple extends Effect {

	public Cripple() {

		this.duration = 4;
		this.name = "Cripple";
		this.description = "A gruesome blow that hinders and slows enemies.";

	}

	@Override
	public void onApply(GameCharacter target) {

		String message;
		double currentInit = target.stats.getInitiative();
		target.stats.setInitiative(currentInit - 8);

		if (this.affected.getProfession().equals("Monster")) {
			message = "The " + affected.getRace() + " has been crippled!";
		} else {
			message = affected.getName() + " has been crippled!";
		}

		System.out.println(message);
	}

	@Override
	public void onRemoval(GameCharacter target) {

		double currentInit = target.stats.getInitiative();
		target.stats.setInitiative(currentInit + 8);
		System.out.println("The " + target.getName()
				+ " is no longer crippled!");
	}

}
