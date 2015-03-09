package game_Items;

public abstract class HealPotion implements GameItem {
	private int healAmount;

	void setHealAmount(int amount) {
		this.healAmount = amount;
	}

	@Override
	public String toString() {
		String str;

		str = getItemName() + " (+" + this.healAmount + " health)";

		return str;
	}

	public int getHealthAmount() {
		return this.healAmount;
	}
}
