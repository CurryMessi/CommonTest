package com.weimob.common.test;

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

    //
}

