package com.weimob.common.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Curry
 * @date 2019/7/1 11:07
 * @description 入口数字5 出口数字8 用程序找出正确的路线
 */
public class IntTest {

    public static void main(String[] args) {
        test1();
    }
    // mapToInt，mapToLong和mapToDouble。这三个方法也比较好理解，
    // 比如mapToInt就是把原始Stream转换成一个新的Stream，这个新生成的Stream中的元素都是int类型。
    // 之所以会有这样三个变种方法，可以免除自动装箱/拆箱的额外消耗；
    private static void test1(){
        User user1 = new User(10, 1,new BigDecimal("10"));
        User user2 = new User(20, 2,new BigDecimal("20"));
        List<User> list=new ArrayList<>();
        list.add(user1);
        list.add(user2);
        int sumAge = list.stream().mapToInt(User::getAge).sum();
        BigDecimal sumMoney = list.stream().map(User::getMoney).reduce(BigDecimal.ONE, BigDecimal::add);
        System.out.println("sumAge="+sumAge+" sumMoney="+sumMoney);

        List<User> collect = list.stream().filter(x -> x.getStatus().equals(3)).collect(Collectors.toList());
        int sumAge1 = collect.stream().mapToInt(User::getAge).sum();
        BigDecimal sumMoney1 = collect.stream().map(User::getMoney).reduce(BigDecimal.ONE, BigDecimal::add);
        System.out.println("sumAge1="+sumAge1+" sumMoney1="+sumMoney1);

        double average = list.stream().mapToInt(User::getAge).average().orElse(0);
        System.out.println("average="+average);

        double average1 = collect.stream().mapToInt(User::getAge).average().orElse(0);
        System.out.println("average1="+average1);

        System.out.println("dev int test修改");
    }
}
