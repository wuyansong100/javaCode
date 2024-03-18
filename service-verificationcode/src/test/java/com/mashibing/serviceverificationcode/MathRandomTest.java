package com.mashibing.serviceverificationcode;

import org.junit.jupiter.api.Test;


public class MathRandomTest {

    @Test
    public void getMathRandom(){
        double mathRandom = (Math.random()*9+1)*Math.pow(10,5);
        System.out.println(mathRandom);
        Integer result = (int) mathRandom;
        System.out.println(result);
    }



}
