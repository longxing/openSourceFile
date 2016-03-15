package imusic4j.examples.member;

import imusic4j.interfaces.Member;

public class MumberFindByPhone {

	/**会员信息查询
	 * @param args
	 */
	public static void main(String[] args) {
		Member m = new Member();
		String mdn = "15348911537";
		String randomkey = "273543";
		System.out.println(m.mumberFindByPhone(mdn, randomkey));
	}

}
