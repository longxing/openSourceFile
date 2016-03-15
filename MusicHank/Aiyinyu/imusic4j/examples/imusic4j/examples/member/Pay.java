package imusic4j.examples.member;

import imusic4j.interfaces.Member;

public class Pay {

	/**爱乐点消费
	 * @param args
	 */
	public static void main(String[] args) {
		Member m = new Member();
		String mdn = "1534891****";
		String code = "810037801474";
		String productName = "";
		int price  = 0;
		String shopName = "";
		System.out.println(m.pay(mdn, code, productName, price, shopName));
	}

}
