package imusic4j.examples.crbt;

import imusic4j.interfaces.Crbt;

public class SendRandomKey {

	/**发送短信验证码
	 * @param args
	 */
	public static void main(String[] args) {
		Crbt c = new Crbt();
		String mdn = "15348911537";
		System.out.println(c.sendRandomKey(mdn));
	}

}
