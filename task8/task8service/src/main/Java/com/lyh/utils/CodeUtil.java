package com.lyh.utils;

import java.util.Random;

public class CodeUtil {
    public static  String getRandNumberCode (int length)    {
        Random random = new Random();
        String result="";
        for(int i=0;i<length;i++){
            result+=random.nextInt(10);
        }
        return result;
    }
}
