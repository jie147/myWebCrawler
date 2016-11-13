package com.jie.downloader;

import com.jie.util.CommonUtil;
import com.jie.util.ReadeFile;

import getKuaidailiProxyIp.GetProxyIp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by jie on 16-7-26.
 */
public class WebDriverDownloader {
	WebDriver driver;

	static List<String> proxyIp = new ArrayList<String>();
	int index = 0;

	static {
		try {
			List<String> l = GetProxyIp.getProxyIp(5);
			System.out.println("proxy ip size is :"+l.size());
			proxyIp.addAll(l);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(proxyIp.size()==0) {
			System.err.println("downloader init err!!");
			System.exit(0);
		}
	}

	public void OpenBrower() {
		driver = new FirefoxDriver();
	}

	public void OpenBrowerWithProxy() {
		if(proxyIp.size()<5){
			GetProxyIp pi= new GetProxyIp();
			try {
				proxyIp.addAll(pi.getProxyIp(5));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(proxyIp.size()<1){
					System.err.println(" not proxy ip can use!!");
					System.exit(0);
				}
			}
		}
		index = CommonUtil.GetRandomNumber(0, proxyIp.size());
		String pip[] = proxyIp.get(index).split(":");
		String ip = pip[0];
		int port = Integer.parseInt(pip[1]);

		ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profile = allProfiles.getProfile("default");
		profile.setPreference("network.proxy.type", 1);
		profile.setPreference("network.proxy.http", ip);
		profile.setPreference("network.proxy.http_port", port);
		profile.setPreference("network.proxy.ssl", ip);
		profile.setPreference("network.proxy.ssl_port", port);
		profile.setPreference("permissions.default.image", 2);
		profile.setPreference("network.proxy.share_proxy_settings", true);

		driver = new FirefoxDriver(profile);
		driver.manage().timeouts().pageLoadTimeout(27, TimeUnit.SECONDS);
	}

	public void deleteNowProIp() {
		proxyIp.remove(index);
	}

	public String download(String url) {
		while (true) {
			try {
				driver.get(url);
				String code = driver.getPageSource();
				if (code != null && !code.equals("")) {
					return code;
				}
			} catch (Exception e) {
				deleteNowProIp();
				driver.close();
				OpenBrowerWithProxy();
				e.printStackTrace();
			}
		}
	}

	public void closeBrower() {
		driver.close();
	}

	public void quitBrower() {
		driver.quit();
	}

}
