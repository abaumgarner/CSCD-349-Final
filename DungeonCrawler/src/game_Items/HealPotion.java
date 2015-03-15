package game_Items;

public abstract class HealPotion {
	private int itemCost;
	private String itemName;
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

	public int getCost() {
		return this.itemCost;
	}

	public String getItemName() {
		return this.itemName;
	}

	protected void setItemName(String str) {
		this.itemName = str;
	}

	protected void setItemCost(int gold) {
		this.itemCost = gold;
	}
}
