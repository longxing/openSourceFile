package imusic4j.examples.cate;

import imusic4j.interfaces.Cate;

public class QueryCateList {

	/**获取分类列表
	 * @param args
	 */
	public static void main(String[] args) {
		Cate c = new Cate();
		System.out.println(c.queryCateList());
	}

}
