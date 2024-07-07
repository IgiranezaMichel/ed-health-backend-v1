package com.ed_health_backend_v1.tools;

import java.util.Random;

public class RandomPassword {
private static String alphabet="ABCDEFGHIJKLMNPQLSTUVWXYZ0123456789@#$%^*";
public String randomPassword() {
    Random random=new Random();
    
    StringBuilder randomAlphabet=new StringBuilder();
    for(int i=0;i<6;i++){
        int number=random.nextInt(alphabet.length());
        randomAlphabet.append(alphabet.split("")[number]);
    }
   return randomAlphabet.toString();
}
}
