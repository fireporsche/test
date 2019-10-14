package com.haishu.test;

import com.haishu.common.util.RandomUtil;

public class Test {

    @org.junit.Test
    public void test(){
        System.out.println(RandomUtil.nextHexString(6));
    }
}
