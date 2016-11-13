package com.jie.util;

import java.util.Random;

public class RamdomNumber {

	public static int GetRandomNumber(int init,int lenght){
		Random random = new Random();
		return random.nextInt(lenght)+init;
	}
	
}
