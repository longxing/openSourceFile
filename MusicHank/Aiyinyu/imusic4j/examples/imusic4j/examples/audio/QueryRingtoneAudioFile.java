package imusic4j.examples.audio;

import imusic4j.interfaces.Audio;

public class QueryRingtoneAudioFile {

	/**振铃试听查询
	 * @param args
	 */
	public static void main(String[] args) {
		Audio a = new Audio();
		String mdmcMusicId = "1045000716";
		String format = "mp3";
		System.out.println(a.queryRingtoneAudioFile(mdmcMusicId, format));
	}

}
