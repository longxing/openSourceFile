package imusic4j.examples.picture;

import imusic4j.interfaces.Picture;

public class FindAlbumPics {

	/**专辑图片接口idtype 0=专辑id，1=彩铃id 2=振铃id 4=全曲id 5=内容id 6=歌手id
	 *        当type为 1 2 4 5时为查找该歌曲所在的专辑的图片，当type为6时查找该歌手专辑图片
	 *        当id有值时，该字段无效,当id无值时，将根据此字段查找
	 * @param args
	 */
	public static void main(String[] args) {
		Picture p = new Picture();
		String id = "26245";//专辑id:9059;彩铃id：810030600357;振铃id:100600063504;全曲:100600063500;内容id:12390;歌手id:809
		int idtype = 0;
		String albumname = "";
		int width = 500;
		int height = 600;
		String format = "jpg";
		int startnum = 1;
		int maxnum = 10;
		System.out.println(p.findAlbumPics(id, idtype, albumname, width, height, format, startnum, maxnum));
	}

}
