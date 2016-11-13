package com.jie.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jie on 16-7-26.
 */
public class ReadeFile {
    public static List<String> readTxtFile(String filePath) {
        List<String> proxyIp = new ArrayList<>();
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
                    proxyIp.add(tmp[0].trim() + ":" + tmp[1].trim());
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
        return proxyIp;
    }
}
