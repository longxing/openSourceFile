package imusic4j.examples.cate;

import imusic4j.interfaces.Cate;

public class QueryAlbumsInfo {

	/**查询分类专辑信息
	 * @param args
	 */
	public static void main(String[] args) {
		Cate c = new Cate();
		String cateID = "1812";
		int startNum = 1;
		int maxNum = 5;
		String order = "id";
		System.out.println(c.queryAlbumsInfo(cateID, startNum, maxNum, order));
	}

}
