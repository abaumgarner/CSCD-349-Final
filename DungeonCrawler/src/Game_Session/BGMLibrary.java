package Game_Session;

import java.util.ArrayList;

public class BGMLibrary {
	private ArrayList<BGM> bgm;
	private BGM activeTrack;
	private String path = ".\\Resources\\BGM\\";

	public BGMLibrary() {
		bgm = new ArrayList<BGM>();
		activeTrack = null;
	}// end constructor

	protected void add(String trackName) {
		try {
			BGM bgmTrack = new BGM(path + trackName);
			bgm.add(bgmTrack);
		}// end try
		catch (Exception e) {
			System.out.println("ERROR: could not add track '" + trackName
					+ "' to BGM library");
		}// end catch
	}// end addToLibrary

	protected void remove(BGM track) {
		bgm.remove(track);
	}// end addToLibrary

	protected void playBGM(String trackName) {
		trackName = path + trackName;
		for (BGM track : bgm) {
			if (track.getTrackName().equals(trackName))// find track in list...
			{
				if (activeTrack != null)// only stop activeTrack if it's already
										// playing
				{
					activeTrack.stop();
				}// end if
				activeTrack = track;
				activeTrack.loop();
			}// end if
		}// end for
	}// end playBGM

}// end class