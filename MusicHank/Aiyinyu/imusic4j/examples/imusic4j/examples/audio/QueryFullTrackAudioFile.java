package imusic4j.examples.audio;

import imusic4j.interfaces.Audio;

public class QueryFullTrackAudioFile {

	/**全曲试听查询
	 * @param args
	 */
	public static void main(String[] args) {
		Audio a = new Audio();
		String mdmcMusicId = "1002010981";
		String format = "mp3";
		System.out.println(a.queryFullTrackAudioFile(mdmcMusicId, format));
	}

}
