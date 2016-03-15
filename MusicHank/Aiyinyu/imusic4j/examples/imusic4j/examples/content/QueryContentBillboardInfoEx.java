package imusic4j.examples.content;

import imusic4j.interfaces.Content;

public class QueryContentBillboardInfoEx {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Content c = new Content();
		String billboardType = "538161";
		int startNum = 0;
		int maxNum =10;
		System.out.println(c.queryContentBillboardInfoEx(billboardType, startNum, maxNum));
	}

}
