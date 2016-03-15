package imusic4j.examples.audio;

import imusic4j.interfaces.Audio;

public class QueryFullTrackAudioFileById {

	/**根据id音源试听查询，idtype 4=全曲id 5=内容id
	 * @param args
	 */
	public static void main(String[] args) {
		Audio a = new Audio();
		String id = "2346830";//全曲id:100201079300  内容id:2346830
		int idtype = 5;
		String format = "mp3";
		System.out.println(a.queryFullTrackAudioFileById(id, idtype, format));
	}

}
