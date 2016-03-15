package imusic4j.examples.contentmanager;

import imusic4j.interfaces.ContentManager;

public class QueryContentInfo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ContentManager c = new ContentManager();
		System.out.println(c.queryContentInfo(115267));
	}

}
