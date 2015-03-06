package game_Items;

public abstract class GameItem {
	private String itemName;

	protected void setItemName(String str) {
		this.itemName = str;
	}

	public String getItemName() {
		return itemName;
	}

}
