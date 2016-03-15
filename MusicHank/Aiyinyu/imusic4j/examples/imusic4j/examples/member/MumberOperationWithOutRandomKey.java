package imusic4j.examples.member;

import imusic4j.interfaces.Member;

public class MumberOperationWithOutRandomKey {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Member m = new Member();
		String mdn = "15348911537";
		int phoneType = 2;
		int grade = 1;
		String openType = "1";
		System.out.println(m.mumberOperationWithOutRandomKey(mdn, phoneType, grade, openType));
	}

}
