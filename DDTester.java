/*Ryan Medenwaldt
  CSCD349, Tom Capaul
  01/31/2015*/
  
import java.util.Scanner;
  
public class DDTester
{
   public static void main(String[] args)
   {
      Scanner input = new Scanner(System.in);
      GameSession game = new GameSession(input);
      game.titleScreen();
      game.newGame();
      
      SFX caveBGM = new SFX("./Resources/BGM/cave.wav");
      SFX battleBGM = new SFX("./Resources/BGM/battle.wav");
      
      SFX footstepsSFX = new SFX("./Resources/SFX/footsteps.wav");
      SFX attackSFX = new SFX("./Resources/SFX/attack.wav");
      SFX hurtSFX = new SFX("./Resources/SFX/hurt.wav");
      SFX arrowSFX = new SFX("./Resources/SFX/arrow.wav");
      SFX magicSFX = new SFX("./Resources/SFX/magic.wav");   
      
      caveBGM.loop(); 
      
      while(!game.isGameOver())
      {
         String str = game.navigate();
         footstepsSFX.play();
      }//end while
      
   }//end main
}//end class
   
