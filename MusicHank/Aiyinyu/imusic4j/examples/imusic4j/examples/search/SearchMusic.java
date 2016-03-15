package imusic4j.examples.search;

import imusic4j.interfaces.Search;

public class SearchMusic {

	/**歌曲搜索
	 * @param args
	 */
	public static void main(String[] args) {
		Search s = new Search();
		String keyWord = "刘";
		String type = "1815-1816";
		int startNum = 0;
		int maxNum = 5;
		System.out.println(s.searchMusic(keyWord, type, startNum, maxNum));
	}

}
