package game_Items;

public class PotionOfHealth extends HealPotion {
	private final String potionName = "Potion of Health";
	private final int potionCost = 2;

	public PotionOfHealth() {
		this.setHealAmount(2);
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
