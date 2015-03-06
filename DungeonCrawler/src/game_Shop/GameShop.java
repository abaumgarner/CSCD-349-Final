package game_Shop;

import java.util.ArrayList;
import java.util.Random;

import game_Items.GameItem;
import game_Items.MajorHealthPotion;
import game_Items.MinorHealthPotion;
import game_Items.PotionOfHealth;

public class GameShop {
	private ArrayList<GameItem> shopItems;

	public GameShop() {
		shopItems = new ArrayList<GameItem>();
		generateShopItems();
	}

	public void displayShop() {
		System.out.println("Game Shop");
		System.out.println("---------");
		int i = 1;

		for (GameItem item : shopItems)
			System.out.println((i++) + ". " + item.toString());
	}

	private void generateShopItems() {
		Random rand = new Random();

		do {
			int randomInt = rand.nextInt(20);

			if (randomInt < 10)
				shopItems.add(new MinorHealthPotion());
			else if (randomInt < 15)
				shopItems.add(new PotionOfHealth());
			else if (randomInt < 20)
				shopItems.add(new MajorHealthPotion());
			shopItems.trimToSize();
		} while (shopItems.size() < 10);
	}
}
