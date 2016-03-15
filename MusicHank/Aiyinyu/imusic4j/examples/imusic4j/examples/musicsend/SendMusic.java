package imusic4j.examples.musicsend;

import imusic4j.interfaces.MusicSend;

public class SendMusic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MusicSend m = new MusicSend();
		String randomkey = "662171";
		String sendPhone = "1534891****";
		String recPhone = "01534891****";
		String musicCode = "810030660046";
		int consumeType = 2;
		int price = 0;
		String sendTime = "20111107165000";
		String information = "hello";
		System.out.println(m.musicSend(randomkey, sendPhone, recPhone, musicCode, consumeType, price, sendTime, information));
	}

}
