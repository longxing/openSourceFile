package imusic4j.examples.picture;

import imusic4j.interfaces.Picture;

public class FindBySinger {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Picture p = new Picture();
		String actorName = "Beyond";
		System.out.println(p.findBySinger(actorName));
	}

}
