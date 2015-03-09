package game_Items;

public class MinorHealthPotion extends HealPotion {
	private final String potionName = "Minor Health Potion";
	private final int potionCost = 1;

	public MinorHealthPotion() {
		this.setHealAmount(1);
	}

	@Override
	public int getCost() {
		return this.potionCost;
	}

	@Override
	public String getItemName() {
		return this.potionName;
	}
}
