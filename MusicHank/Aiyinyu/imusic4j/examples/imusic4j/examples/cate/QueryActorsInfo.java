package imusic4j.examples.cate;

import imusic4j.interfaces.Cate;

public class QueryActorsInfo {

	/**查询分类歌手信息
	 * @param args
	 */
	public static void main(String[] args) {
		Cate c = new Cate();
		String cateID = "1776";
		int startNum = 1;
		int maxNum = 5;
		String order = "id";
		System.out.println(c.queryActorsInfo(cateID, startNum, maxNum, order));
	}

}
