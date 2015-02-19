public class CharacterTester {

   public static void main(String[] args){
   
      Character warr = new Warrior();
      Character enemy = new Warrior();
    
      warr.basicAttack(enemy);

      //warr.setDex(10);
      enemy.setDex(10);
      enemy.setLevel(2);
      
      warr.basicAttack(enemy);
      
      testEffects((Warrior) warr, enemy);

   
   }
   
   public static void testEffects(Warrior Friendly, Character Enemy){
	   
	   
	   Cripple cripple = Friendly.cripple(Enemy);
	 
	   cripple.remove(Enemy);
	   
	   
   }














}