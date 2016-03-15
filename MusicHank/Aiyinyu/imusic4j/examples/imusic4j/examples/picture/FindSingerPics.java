package imusic4j.examples.picture;

import imusic4j.interfaces.Picture;

public class FindSingerPics {

	/**歌手图片接口 idtype 0=歌手id，1=彩铃id 2=振铃id 4=全曲id 5=内容id
	 * 当type为 1 2 4 5时为查找该歌曲所对应的歌手图片
	 * 当id有值时，该字段无效,当id无值时，将根据singername查找
	 * @param args
	 */
	public static void main(String[] args) {
		Picture p = new Picture();
		String id = "0";//歌手id:809;彩铃id：810030206132;振铃id:100200262104;全曲:100600063500;内容id:12390
		int idtype = 5;
		String singername = "东方神起";
		int width = 500;
		int height = 600;
		String format = "jpg";
		int startnum = 1;
		int maxnum = 10;
		System.out.println(p.findSingerPics(id, idtype, singername, width, height, format, startnum, maxnum));
	}

}
