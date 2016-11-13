package com.jie.downloader;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.jie.downloader.imp.DownLoader;

public class HDownloader implements DownLoader {

	@Override
	public String downLoad(String url) throws IOException {
		while (true) {
			try {
				HtmlUnitDownloader hd = new HtmlUnitDownloader();
				String content = hd.downLoad(url);
				if (content != null)
					return content;
			} catch (FailingHttpStatusCodeException e) {
				e.printStackTrace();
				continue;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
