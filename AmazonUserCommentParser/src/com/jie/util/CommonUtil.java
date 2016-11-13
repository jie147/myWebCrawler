package com.jie.util;

import java.util.Random;

/**
 * Created by jie on 16-7-26.
 */
public class CommonUtil {
    public static int GetRandomNumber(int initNum,int length){
        Random random = new Random();
        return random.nextInt(length)+initNum;
    }
}
