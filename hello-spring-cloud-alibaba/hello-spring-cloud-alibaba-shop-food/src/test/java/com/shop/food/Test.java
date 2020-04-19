package com.shop.food;

import com.google.common.collect.Range;

public class Test {
    public static void main(String[] args) {

        System.out.println(Range.closed(1,3).contains(2));
        System.out.println(Range.closed(1,3).contains(5 ));


    }
}
