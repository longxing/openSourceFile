package imusic4j.examples.member;

import imusic4j.interfaces.Member;

public class GetCountOfMembersMenoy {

	/**爱乐币余额查询
	 * @param args
	 */
	public static void main(String[] args) {
		Member m = new Member();
		String mdn = "15348911537";
		System.out.println(m.getCountOfMembersMenoy(mdn));
	}

}
