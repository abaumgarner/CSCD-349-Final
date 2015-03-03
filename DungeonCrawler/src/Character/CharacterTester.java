package Character;

public class CharacterTester {

	public static void main(String[] args) {

		GameCharacter warr = new Warrior();
		GameCharacter enemy = new Warrior();

		warr.basicAttack(enemy);

		// warr.setDex(10);
		enemy.setDex(10);
		enemy.setLevel(2);

		warr.basicAttack(enemy);

		testEffects((Warrior) warr, enemy);

	}

	public static void testEffects(Warrior Friendly, GameCharacter Enemy) {

		Cripple cripple = Friendly.cripple(Enemy);

		cripple.remove(Enemy);

	}

}