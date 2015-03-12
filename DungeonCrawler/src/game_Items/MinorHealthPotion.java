package game_Items;

public class MinorHealthPotion extends HealPotion {
	private final String potionName = "Minor Health Potion";
	private final int potionCost = 1;

	public MinorHealthPotion() {
		this.setItemName(potionName);
		this.setItemCost(potionCost);
		this.setHealAmount(1);
	}
}
