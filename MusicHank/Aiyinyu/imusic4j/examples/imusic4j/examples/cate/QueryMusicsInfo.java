package imusic4j.examples.cate;

import imusic4j.interfaces.Cate;

public class QueryMusicsInfo {

	/**查询分类歌曲信息
	 * @param args
	 */
	public static void main(String[] args) {
		Cate c = new Cate();
		String cateID = "339";
		int startNum = 1;
		int maxNum = 5;
		String order = "id";
		System.out.println(c.queryMusicsInfo(cateID, startNum, maxNum, order));
	}

}
