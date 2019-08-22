package com.weimob.common.JavaTest;

import java.util.Arrays;


public class SortTest {
	public static void main(String[] args) {
		//排序算法
		Demo1();
		Demo2();
	}
	
	//冒泡排序
	public static void Demo1(){
		int[] a={10,5,2,18,7,3,20,1};
		int temp=0;
		for (int i = 0; i < a.length-1; i++) {
			for (int j = 0; j < a.length-i-1; j++) {
				if(a[j]>a[j+1]){
					temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
		}
		for (int i : a) {
			System.out.println(i);
		}
	}
	
	//二分法排序
	//对前面的0～i-1元素进行折半，先跟他们中间的那个元素比，如果小，则对前半再进行折半，否则对后半进行折半，直到left>right，
	//然后再把第i个元素前1位与目标位置之间的所有元素后移，再把第i个元素放在目标位置上。 
	public static void Demo2(){
		int[] a={10,5,2,18,7,3,20,1};
		for (int i = 0; i < a.length; i++) {
            int low = 0, mid = 0, high = i;
            int temp = a[i];
            while (low <= high) {
                mid = (low + high) / 2;
                if (a[mid] < temp) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            // 找到要插入的位置，然后将这个位置以后的所有元素向后移动
            for (int j = i - 1; j > high; j--)
            {
                a[j + 1] = a[j];
            }
            a[high + 1] = temp;
        }
		
		System.out.println(Arrays.toString(a));
	}
	
	
}
