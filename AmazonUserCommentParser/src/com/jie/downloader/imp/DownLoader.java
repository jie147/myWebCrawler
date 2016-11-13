package com.jie.downloader.imp;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by jie on 16-7-18.
 */
public interface DownLoader {
    public String downLoad(String url) throws IOException;
}
