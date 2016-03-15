package imusic4j.examples.member;

import imusic4j.interfaces.Member;

public class Order {

	/**会员爱乐点订购彩铃
	 * @param args
	 */
	public static void main(String[] args) {
		Member m = new Member();
		String mdn = "15348911537";
		String ringId = "810037801474";
		int price = 0;
		System.out.println(m.order(mdn, ringId, price));
	}

}
