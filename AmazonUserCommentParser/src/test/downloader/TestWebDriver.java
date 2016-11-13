package test.downloader;

import com.jie.downloader.WebDriverDownloader;

public class TestWebDriver {
	public static void main(String[] args) {
		WebDriverDownloader downloader = new WebDriverDownloader();
//		downloader.initDownloader();
		downloader.OpenBrowerWithProxy();
		String content = downloader.download("http://www.baidu.com");
		System.out.println(content);
		downloader.closeBrower();
	}
}
