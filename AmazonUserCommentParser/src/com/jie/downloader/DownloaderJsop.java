package com.jie.downloader;

import com.jie.downloader.imp.DownLoader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by jie on 16-7-24.
 */
public class DownloaderJsop implements DownLoader{
    @Override
    public String downLoad(String url) throws IOException {
        Document document = Jsoup.connect(url).timeout(5000).userAgent("Mozilla").get();
        return document.toString();
    }
}
