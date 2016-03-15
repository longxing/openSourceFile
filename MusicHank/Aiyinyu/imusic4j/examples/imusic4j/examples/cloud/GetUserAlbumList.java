package imusic4j.examples.cloud;

import imusic4j.interfaces.MoonCloudMusic;

public class GetUserAlbumList {

	/**根据用户号码获取用户专辑列表
	 * @param args
	 */
	public static void main(String[] args) {
		MoonCloudMusic m = new MoonCloudMusic();
		String mdn = "1533174****";
		String ordertype = "1";
		System.out.println(m.getUserAlbumList(mdn, ordertype));
	}

}
