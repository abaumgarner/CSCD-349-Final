package Character;

public abstract class Effect {

	protected int duration;
	protected String name, description;
	protected GameCharacter affected;

	public abstract void onApply(GameCharacter target);

	public abstract void onRemoval(GameCharacter target);

	public String getName() {
		return this.name;
	}

	public String getDesc() {
		return this.description;
	}

	public int getDuration() {
		return this.duration;
	}

	public void decreaseDuration() {

		this.duration -= 1;
	}

	protected void apply(GameCharacter target) {
		
		if(target.addEffect(this)){
		
			this.affected= target;
			this.onApply(target);
		}
	}

	protected void remove(GameCharacter target) {

		this.onRemoval(target);
		this.affected = null;
		target.removeEffect(this);
	}
}