package com.weimob.common.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author  Curry
 * @date    2019/6/25 16:55
 */
public class Test {
    public static void main(String[] args) {
        Integer integer = recursiveAdd(5);
        System.out.println(integer);
        System.out.println(recursiveMix(5));
        System.out.println(fun(3, new String[]{"111","222","333"}));

        String requests_url="http://%s.consortium.n.dev.weimob.com";
        String format = String.format(requests_url, 2528);
        System.out.println(format);
        //testFilter();
        testMap();
    }

    private static void testMap(){
        HashMap<Integer,String> map=new HashMap<>();
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.keySet().forEach(x->{
            System.out.println(x);
            System.out.println(map.get(x));
        });
    }


    private static void testFilter(){
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.stream().filter(x -> x.equals(1)).count();
        List<Integer> list1=new ArrayList<>();
        list.stream().peek(list1::add).forEach(System.out::println);
    }

    // 递归求和1+2+3+.....+n
    private static Integer recursiveAdd(Integer n){
        if (n > 0) {
            return n + recursiveAdd(n - 1);
        } else {
            return 0;
        }
    }

    //递归阶乘n! = n * (n-1) * (n-2) * ...* 1(n>0)
    private static Integer recursiveMix(Integer n){
        if (n == 1) {
            return 1;
        } else {
            return n * recursiveMix(n - 1);
        }
    }
    // 判定一系列字符串中是否有相同的内容
    private  static  Boolean fun(int n,String[] a){
        boolean b = false;
        if(n == a.length){
            b = true;
        }else{
            for(int i = n; i < a.length-1; i++){
                System.out.println(n+"    "+(i+1));
                if(a[n].equals(a[i+1])){
                    return false;
                }
            }
            n++;
            fun(n,a);
        }
        return b;
    }

    //约瑟夫环问题:编号为 1-N 的 N 个士兵围坐在一起形成一个圆圈，从编号为 1 的士兵开始依次报数（1，2，3…这样依次报），数到 m 的 士兵会被杀死出列，
    // 之后的士兵再从 1 开始报数。直到最后剩下一士兵，求这个士兵的编号。
    int f(int n, int m){
        return n == 1 ? n : (f(n - 1, m) + m - 1) % n + 1;
    }
}

