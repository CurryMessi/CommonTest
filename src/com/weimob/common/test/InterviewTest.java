package com.weimob.common.test;

/**
 * @author Curry
 * @date 2019/4/20 12:50
 * @description 笔试题
 */
public class InterviewTest {

    public static void main(String[] args) {
        test1();
    }

    //String str="How are you";写一段程序控制台打印出you are How;
    private static void test1() {
        String str="How are you";
        String[] split = str.split(" ");
        System.out.println(split[2]+" "+split[1]+" "+split[0]);
    }

    //给定一个数组 String[] before=new String[]("a","b","a");





}
