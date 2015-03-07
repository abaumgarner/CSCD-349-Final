package Game_Session;

public class GameSoundsTester
{
   public static void main(String[] args)
   {
      SFXLibrary sfxLib = new SFXLibrary();
      BGMLibrary bgmLib = new BGMLibrary();
           
      //add to SFX library
      sfxLib.add("./Resources/SFX/arrow.wav");
      sfxLib.add("./Resources/SFX/attack.wav");
      sfxLib.add("./Resources/SFX/footsteps.wav");
      sfxLib.add("./Resources/SFX/hurt.wav");
      sfxLib.add("./Resources/SFX/magic.wav");
      
      //add to BGM library
      bgmLib.add("./Resources/BGM/cave.wav");
      bgmLib.add("./Resources/BGM/battle.wav");
      
      sfxLib.playTrack("./Resources/SFX/arrow.wav");
      sfxLib.playTrack("./Resources/SFX/attack.wav");
      sfxLib.playTrack("./Resources/SFX/footsteps.wav");
      sfxLib.playTrack("./Resources/SFX/hurt.wav");
      sfxLib.playTrack("./Resources/SFX/magic.wav");
   }//end main
}//end class