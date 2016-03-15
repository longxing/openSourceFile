package imusic4j.examples.musicsend;

import imusic4j.interfaces.MusicSend;

public class CommonIvrOperate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MusicSend m = new MusicSend();
		String phone = "";
		String toPhone = "";
		int bizType = 0;
		String cpid = "";
		String musicCode = "";
		String sendTime = "";
		String message = "";
		System.out.println(m.commonIvrOperate(phone, toPhone, bizType, cpid, musicCode, sendTime, message));
	}

}
