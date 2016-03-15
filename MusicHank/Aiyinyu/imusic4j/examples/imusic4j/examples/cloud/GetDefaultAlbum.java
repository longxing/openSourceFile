package imusic4j.examples.cloud;

import imusic4j.interfaces.MoonCloudMusic;

public class GetDefaultAlbum {

	/**查询用户默认专辑
	 * @param args
	 */
	public static void main(String[] args) {
		MoonCloudMusic m = new MoonCloudMusic();
		String mdn = "15331746****";
		System.out.println(m.getDefaultAlbum(mdn));
	}
}
