package imusic4j.examples.member;

import imusic4j.interfaces.Member;

public class AddAiYueBi {

	/**增加爱乐币
	 * @param args
	 */
	public static void main(String[] args) {
		Member m = new Member();
		String mdn = "1532440****";
		int type = 300;
		System.out.println(m.addAiYueBi(mdn, type));
	}

}
