package imusic4j.examples.dep;

import imusic4j.interfaces.DEPManager;

public class FindPhoneNumberByIMSI {

	/**通过imsi查询手机号码
	 * @param args
	 */
	public static void main(String[] args) {
		DEPManager d = new DEPManager();
		String imsi = "460036221166226";
		System.out.println(d.findPhoneNumberByIMSI(imsi));
	}

}
