package game_Items;

public class PotionOfHealth extends HealPotion {
	private final String potionName = "Potion of Health";
	private final int potionCost = 2;

	public PotionOfHealth() {
		this.setItemName(potionName);
		this.setItemCost(potionCost);
		this.setHealAmount(10);
	}
}
