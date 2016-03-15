package imusic4j.examples.picture;

import imusic4j.interfaces.Picture;

public class FindBySingerAndRing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Picture p = new Picture();
		String resourceID = "0";
		String singer = "东方神起";
		String song = "一个夏天的圣诞节";
		String type = "1";
		String reskind = "1";
		String width = "500";
		String height = "600";
		System.out.println(p.findBySingerAndRing(resourceID, singer, song, type, reskind, width, height));
	}

}
