package Game_Session;

/*Ryan Medenwaldt
 CSCD349, Tom Capaul
 01/31/2015*/
 
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class BGM{
	private Mixer mixer;
	private Clip clip;
	private File audioFile;
   private String trackName;

	public BGM(String track) {
		Mixer.Info[] mixInfo = AudioSystem.getMixerInfo();
		mixer = AudioSystem.getMixer(mixInfo[0]);
		DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
      trackName = track;
		try {
			clip = (Clip) mixer.getLine(dataInfo);
			audioFile = new File(track);
			AudioInputStream audioStream = AudioSystem
					.getAudioInputStream(audioFile);
			clip.open(audioStream);
		}// end try

		catch (LineUnavailableException lue) {
			lue.printStackTrace();
		}// end catch

		catch (NullPointerException npe) {
			npe.printStackTrace();
		}// end catch

		catch (UnsupportedAudioFileException uafe) {
			uafe.printStackTrace();
		}// end catch

		catch (IOException ioe) {
			ioe.printStackTrace();
		}// end catch
	}// end constructor

	public void loop() {
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}// end loop
   
   	public void stop() {
		try {
			clip.stop();
		}// end try
		catch (Exception e) {
			e.printStackTrace();
		}// end catch
	}// end stop
   
   public String getTrackName()
   {
      return this.trackName;
   }//end getTrackName
}// end class