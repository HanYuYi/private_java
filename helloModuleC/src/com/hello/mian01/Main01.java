package com.hello.main01;

import com.hello.a1.HelloA1;
import com.hello.a3.HelloA3;

public class Main01 {
    public static void main(String [] args) {
        new HelloA1().say();
        new HelloA3().say();

        System.out.println(new HelloA1());
    }
}
