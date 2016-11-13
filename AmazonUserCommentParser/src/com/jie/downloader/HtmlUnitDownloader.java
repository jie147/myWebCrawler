package com.jie.downloader;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.jie.downloader.imp.DownLoader;

public class HtmlUnitDownloader {
	public static BrowserVersion browser[] = {BrowserVersion.FIREFOX_38,BrowserVersion.FIREFOX_45,BrowserVersion.BEST_SUPPORTED,BrowserVersion.CHROME,BrowserVersion.EDGE};
	public static List<String> proxyIp=new ArrayList<String>();
	WebClient webClient;
	int num = 10;
	
	public HtmlUnitDownloader() {
		webClient = GetOneBrowser();
	}

	static {
		readTxtFile("resource/proxyIp.txt");
	}
	
	private static void readTxtFile(String filePath) {
		int j = 0;
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					String tmp[] = lineTxt.split("\t");
					proxyIp.add(tmp[0] + ":" + tmp[1]);
					j++;
				}
				read.close();
			} else {
				System.out.println("not find file!!");
			}
		} catch (Exception e) {
			System.out.println("read file err!!");
			e.printStackTrace();
		}

	}
	
	public String downLoad(String url) throws IOException {
		HtmlPage page1 = webClient.getPage(url);
		return page1.asXml();
	}
	
	
	public WebClient GetOneBrowser(){
		Random random = new Random();
		int bro = random.nextInt(browser.length-1);
		int ip = random.nextInt(proxyIp.size()-1);
		String ipPro[]=proxyIp.get(ip).split(":");
		System.out.println();
		webClient = new WebClient(browser[bro],ipPro[0],Integer.parseInt(ipPro[1]));
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		return webClient;
	}
	
	public void closeWindows(){
		webClient.close();
	}
}
