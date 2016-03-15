package imusic4j.examples.ivr;

import imusic4j.interfaces.Ivr;

public class IsCrbtUser {

	/**是否彩铃用户
	 * @param args
	 */
	public static void main(String[] args) {
		Ivr i = new Ivr();
		String mdn = "15348911537";
		System.out.println(i.isCrbtUser(mdn));
	}

}
