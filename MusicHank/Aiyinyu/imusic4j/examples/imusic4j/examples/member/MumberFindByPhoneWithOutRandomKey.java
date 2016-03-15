package imusic4j.examples.member;

import imusic4j.interfaces.Member;

public class MumberFindByPhoneWithOutRandomKey {

	/**会员信息查询（无验证码）
	 * @param args
	 */
	public static void main(String[] args) {
		Member m = new Member();
		String mdn = "15348911537";
		System.out.println(m.mumberFindByPhoneWithOutRandomKey(mdn));
	}

}
