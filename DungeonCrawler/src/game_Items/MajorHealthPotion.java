package game_Items;

public class MajorHealthPotion extends HealPotion {
	private final String potionName = "Major Health Potion";
	private final int potionCost = 3;

	public MajorHealthPotion() {
		this.setHealAmount(3);
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
