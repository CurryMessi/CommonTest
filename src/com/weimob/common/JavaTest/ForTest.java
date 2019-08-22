package com.weimob.common.JavaTest;

import java.util.Random;
import java.util.Scanner;

public class ForTest {
	
	private static Scanner in;

	public static void main(String[] args) {
		//打印乘法表
		for (int i = 1; i <=5; i++) {
			for (int j = 1; j <=i; j++) {
				//System.out.print(j+"*"+i+"="+i*j+"  ");
			}
			//System.out.println("");
		}
		Random random=new Random();
		System.out.println(random.nextInt(8));
		//count();
		//Demo1();
		//Demo2();
	}
	
	/**
	 * 题目：求1+2!+3!+...+20!的和   
	 * 
	 */
	public static void count(){
		long sum = 0;
        long fac = 1;
        for (int i = 1; i <= 20; i++) {
        	//每次2！比前面的都多乘一位
            fac = fac * i;
            sum += fac;
        }
        System.out.println(sum);
        System.out.println(5);
    }
	
	/**
	 * 题目：利用递归方法求5!  
	 * 程序分析：递归公式：f(n)=f(n-1)*4!
	 */
	public static long fac(int n){
		long value = 0;
        if (n == 1 || n == 0) {
            value = 1;
        } else if (n > 1) {
            value = n * fac(n - 1);
        }
        return value;
    }
	
	/**
	 * 求100之内的素数
	 */
	public static void Demo1(){
		int sum, i;
        for (sum = 2; sum <= 100; sum++) {
            for (i = 2; i <= sum / 2; i++) {
                if (sum % i == 0)
                    break;
            }
            if (i > sum / 2)
                System.out.println(sum + "是素数");
        }
    }
	
	/**
	 * 有n个人围成一圈，顺序排号。从第一个人开始报数（从1到3报数），凡报到3的人退出圈子，问最后留下的是原来第几号的那位
	 */
	public static void Demo2(){
		System.out.println("请输入人数：");
        in = new Scanner(System.in);
        int[] a = new int[in.nextInt()];
        for (int i = 0; i < a.length; i++) {
            a[i] = 1;
        }
        int left = a.length;
        int j = 0;
        int num = 0;
        while (left > 1) {
            if (a[j] == 1) {
                num++;
            }
            if (num == 3) {
                a[j] = 0;
                num = 0;
                left--;
            }
            j++;
            if (j == a.length) {
                j = 0;
            }
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 1) {
                System.out.println("最后留下的人是"+ (i + 1) + "号");
                break;
            }
        }
    }
	
	
	
}
