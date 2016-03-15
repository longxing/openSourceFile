package imusic4j.examples.lyric;

import imusic4j.interfaces.Lyric;

public class GetLyricByName {

	/**根据歌手名歌曲名查询歌词
	 * @param args
	 */
	public static void main(String[] args) {
		Lyric l = new Lyric();
		String musicName = "七里香";
		String actorName = "周杰伦";
		String type = "txt";
		System.out.println(l.getLyricByName(musicName, actorName, type));
	}

}
