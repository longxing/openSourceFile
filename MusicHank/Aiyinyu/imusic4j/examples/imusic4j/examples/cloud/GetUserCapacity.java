package imusic4j.examples.cloud;

import imusic4j.interfaces.MoonCloudMusic;

public class GetUserCapacity {

	/**根据用户id查询用户已用容量和可用最大容量
	 * @param args
	 */
	public static void main(String[] args) {
		MoonCloudMusic m = new MoonCloudMusic();
		String mdn = "1533174****";
		System.out.println(m.getUserCapacity(mdn));
	}

}
