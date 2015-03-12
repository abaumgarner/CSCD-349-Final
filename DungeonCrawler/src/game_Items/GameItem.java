package game_Items;

public abstract class GameItem {
	private int itemCost;
	private String itemName;

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
