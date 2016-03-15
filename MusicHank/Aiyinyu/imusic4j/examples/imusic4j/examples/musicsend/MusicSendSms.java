package imusic4j.examples.musicsend;

import imusic4j.interfaces.MusicSend;

public class MusicSendSms {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MusicSend m = new MusicSend();
		String mdn = "15348911537";
		System.out.println(m.musicSendSms(mdn));
	}

}
