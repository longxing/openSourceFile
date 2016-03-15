package imusic4j.examples.member;

import imusic4j.interfaces.Member;

public class MumberOperation {

	/**会员操作
	 * @param args
	 */
	public static void main(String[] args) {
		Member m = new Member();
		String mdn = "1532440xxxx";
		String randomkey = "168201";
		int phoneType =2;
		int grade = 1;
		String openType = "3";
		System.out.println(m.mumberOperation(mdn, randomkey, phoneType, grade, openType));
	}

}
