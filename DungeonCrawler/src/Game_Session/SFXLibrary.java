package Game_Session;

import java.util.ArrayList;

public class SFXLibrary
{
   private ArrayList<SFX> sfx;
   
   public SFXLibrary()
   {
      sfx = new ArrayList<SFX>();
   }//end constructor
   
   protected void add(String track)
   {
      try
      {
         SFX newSFX = new SFX(track);
         sfx.add(newSFX);
      }//end try
      catch(Exception e)
      {
         System.out.println("ERROR: could not add track '" + track + "' to SFX library");
      }//end catch
   }//end addToLibrary
   
   protected void remove(SFX track)
   {
      sfx.remove(track);
   }//end addToLibrary
   
   protected void playTrack(String trackName)
   {
      for(SFX track: sfx)
      {
         if(track.getTrackName().equals(trackName))
         {
            track.play();
         }//end if
      }//end for
   }//end playTrack
   
}//end class