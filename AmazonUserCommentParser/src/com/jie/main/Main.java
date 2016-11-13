package com.jie.main;

import java.io.IOException;
import java.util.List;

import com.jie.db.JdbcPool;
import com.jie.downloader.DownloaderJsop;
import com.jie.downloader.HDownloader;
import com.jie.downloader.WebDriverDownloader;
import com.jie.downloader.imp.DownLoader;
import com.jie.urlmanager.UrlManagerAmazon;

/**
 * Created by jie on 16-7-24.
 */
public class Main {
	private static UrlManagerAmazon urlManager;
	static WebDriverDownloader downLoader;

	private static void initSys() {
		new JdbcPool();
		urlManager = UrlManagerAmazon.GetUrlManager();
		urlManager.initUrlManager();
		if (urlManager.isEmpty()) {
			System.out.println(" url manager is null !");
			urlManager.addOneUrl("https://www.amazon.cn/");
		}
//		 downLoader = new WebDriverDownloader();
//		// downLoader.initDownloader();
//		 downLoader.OpenBrowerWithProxy();

	}

	public static void main(String[] args) throws InterruptedException {
		int i = 23;
		initSys();
		while (!urlManager.isEmpty()) {
			String url = urlManager.getOneUrl();

//			 if(i == 0){
//				 i=23;
//			 downLoader.closeBrower();
//			 downLoader.OpenBrowerWithProxy();
//			 }

			DownLoader downLoaderJ = new DownloaderJsop();
			// System.out.println("将解析的url："+url);
			String content = null;
			try {
				content = downLoaderJ.downLoad(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (content != null) {
				if (content.contains("抱歉，我们只是想确认一下当前访问者并非自动程序。为了达到最佳效果，请确保您浏览器上的 Cookie 已启用。")) {
//					 downLoader.closeBrower();
//					 downLoader.OpenBrower();
					System.err.println("需要验证码!!!");
					System.exit(0);
				}
//				 if(content.contains("如果您无法载入任何页面，请检查您计算机的网络连接。")){
//				 downLoader.deleteNowProIp();
//				 downLoader.closeBrower();
//				 downLoader.OpenBrower();
//				 }

				System.out.println("the lenght of content:" + content.length());
				ParseSaveM parseSaveM = new ParseSaveM();
				int flag = parseSaveM.ParseAndSave(url, content);
				if (flag == 0)
					urlManager.parserOver(url);
				List<String> urls = parseSaveM.getUsefulUrl();
				if (urls != null) {
					urlManager.addUrls(urls);
				}
				i--;
			} else {
				System.out.println("not content , url is :" + url);
				urlManager.haveSomeException(url);
			}

			
		}
	}
}
