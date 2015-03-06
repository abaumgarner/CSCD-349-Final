package game_Items;

public abstract class HealPotion extends GameItem {
	private int healAmount;

	void setHealAmount(int amount) {
		this.healAmount = amount;
	}

	@Override
	public String toString() {
		String str;

		str = this.getItemName() + " (+" + this.healAmount + " health)";

		return str;
	}
}
