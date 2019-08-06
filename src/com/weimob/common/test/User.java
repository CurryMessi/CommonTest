package com.weimob.common.test;

import java.math.BigDecimal;

/**
 * @author Curry
 * @date 2019/7/3 13:50
 */
public class User{

    private Integer age;

    private Integer status;

    private BigDecimal money;

    public User(Integer age,Integer status,BigDecimal money){
        this.age=age;
        this.status=status;
        this.money=money;
    }


    public User(){
        this.age=age;
        this.status=status;
        this.money=money;
    }

    public Integer getAge(){
        return this.age;
    }

    public Integer getStatus(){
        return this.status;
    }

    public BigDecimal getMoney(){
        return this.money;
    }
}
