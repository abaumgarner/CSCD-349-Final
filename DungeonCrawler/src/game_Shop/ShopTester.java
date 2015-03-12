package game_Shop;

import game_Items.GameItem;

import java.util.Scanner;

public class ShopTester {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);

		GameShop shop = new GameShop();

		shop.displayShop();

		System.out.print("Choice: ");
		int choice = kb.nextInt();

		GameItem item = shop.getShopItem(choice);

		System.out.println(item + " was purchased from the shop for "
				+ item.getCost() + " gold.");

		shop.displayShop();

		System.out.print("Choice: ");
		choice = kb.nextInt();

		item = shop.getShopItem(choice);

		System.out.println(item + " was purchased from the shop for "
				+ item.getCost() + " gold.");
	}
}
