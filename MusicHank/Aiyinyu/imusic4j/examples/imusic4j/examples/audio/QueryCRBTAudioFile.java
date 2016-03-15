package imusic4j.examples.audio;

import imusic4j.interfaces.Audio;

public class QueryCRBTAudioFile {

	/**彩铃试听查询
	 * @param args
	 */
	public static void main(String[] args) {
		Audio a = new Audio();
		String mdmcMusicId = "1002002116";
		String format = "wma";
		System.out.println(a.queryCRBTAudioFile(mdmcMusicId, format));
	}

}
