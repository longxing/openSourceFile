package imusic4j.examples.member;

import imusic4j.interfaces.Member;

public class MumberSendSms {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Member m = new Member();
		String mdn = "15348911537";
		System.out.println(m.mumberSendSms(mdn));
	}

}
