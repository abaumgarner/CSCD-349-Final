package game_Items;

public abstract class GameItem {
	private String itemName;
	private int cost;

	protected void setItemName(String str, int amount) {
		this.itemName = str;
		this.cost = amount;
	}

	public String getItemName() {
		return itemName;
	}

	public int getCost() {
		return this.cost;
	}

}
