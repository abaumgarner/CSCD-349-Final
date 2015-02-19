package Game_Session;

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
      
      //build maze...
      MazeBuilder builder = new MazeBuilder(5);
      Maze maze = builder.build();
      
      //background music objects
      SFX caveBGM = new SFX("./Resources/BGM/cave.wav");
      SFX battleBGM = new SFX("./Resources/BGM/battle.wav");
      //sound effect objects
      SFX footstepsSFX = new SFX("./Resources/SFX/footsteps.wav");
      SFX attackSFX = new SFX("./Resources/SFX/attack.wav");
      SFX hurtSFX = new SFX("./Resources/SFX/hurt.wav");
      SFX arrowSFX = new SFX("./Resources/SFX/arrow.wav");
      SFX magicSFX = new SFX("./Resources/SFX/magic.wav");   
      
      caveBGM.loop(); 
      
      while(!game.isGameOver(maze))
      {
         String cmd = game.getCommand(input);
         if(cmd.equals("map"))
         {
            System.out.println("\nMAP:\n");
            System.out.println(maze.toString());
            game.navigate(maze);
            footstepsSFX.play();
         }//end if
         
         else if(cmd.equals("status"))
         {
            game.getPartyStats();
         }//end else if
         
         else
         {
            System.out.println("There was an error executing the command...");
         }//end 
      }//end while
      
   }//end main
}//end class
   
