package game_Items;

public class MajorHealthPotion extends HealPotion {
	private final String potionName = "Major Health Potion";
	private final int potionCost = 3;

	public MajorHealthPotion() {
		this.setItemName(potionName);
		this.setItemCost(potionCost);
		this.setHealAmount(3);
	}

}
