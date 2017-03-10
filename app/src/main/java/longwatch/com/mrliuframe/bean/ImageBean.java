package longwatch.com.mrliuframe.bean;

/**
 * Created by 鍒樿吘椋�on 2016/9/3.
 */
public class ImageBean {

	/**
	 * url : http://image.hmeili.com/yunifang/images/goods/ad6/
	 * 16090209528598526317187821.jpg width : 790 height : 811
	 */

	private String url;
	private String width;
	private String height;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "ImageBean [url=" + url + ", width=" + width + ", height="
				+ height + "]";
	}

}
