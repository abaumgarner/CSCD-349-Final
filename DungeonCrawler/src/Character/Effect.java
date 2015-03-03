package Character;

public abstract class Effect {

	protected int duration;
	protected String name, description;

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

		target.addEffect(this);
		this.onApply(target);
	}

	protected void remove(GameCharacter target) {

		this.onRemoval(target);
		target.removeEffect(this);
	}
}