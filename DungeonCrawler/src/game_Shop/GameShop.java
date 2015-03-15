package game_Shop;

import java.util.ArrayList;
import java.util.Random;

import game_Items.HealPotion;
import game_Items.MajorHealthPotion;
import game_Items.MinorHealthPotion;
import game_Items.PotionOfHealth;

public class GameShop {
	private int numberOfItems = 5;

	private ArrayList<HealPotion> shopItems;

	public GameShop() {
		shopItems = new ArrayList<HealPotion>();
		generateShopItems();
	}

	public void displayShop() {
		System.out.println("\n-----------------------------------");
		System.out.printf("%-13sGAME SHOP\n", "");
		System.out.println("-----------------------------------");
		int i = 1;

		for (HealPotion item : shopItems)
			System.out.println((i++) + ". " + item.toString());
		System.out.println("-----------------------------------\n");
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
		} while (shopItems.size() < numberOfItems);
	}

	public int shopSize() {
		return shopItems.size();
	}

	public HealPotion getShopItem(int location) {
		HealPotion item = this.shopItems.get(location - 1);

		return item;
	}

}
