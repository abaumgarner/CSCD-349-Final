package Game_Session;

import java.util.ArrayList;

public class SFXLibrary
{
   private ArrayList<SFX> sfx;
   private String path = ".\\Resources\\SFX\\";
   
   public SFXLibrary()
   {
      sfx = new ArrayList<SFX>();
   }//end constructor
   
   protected void add(String track)
   {
      try
      {
         SFX newSFX = new SFX(path + track);
         sfx.add(newSFX);
      }//end try
      catch(Exception e)
      {
         System.out.println("ERROR: could not add track '" + path + track + "' to SFX library");
      }//end catch
   }//end addToLibrary
   
   protected void remove(SFX track)
   {
      sfx.remove(track);
   }//end addToLibrary
   
   public void playTrack(String trackName)
   {
	   trackName = path + trackName; 
      for(SFX track: sfx)
      {
         if(track.getTrackName().equals(trackName))
         {
            track.play();
         }//end if
      }//end for
   }//end playTrack
   
}//end class