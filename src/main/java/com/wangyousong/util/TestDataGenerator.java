package com.wangyousong.util;

import net.andreinc.mockneat.MockNeat;

public class TestDataGenerator {
    private static final MockNeat mock = MockNeat.threadLocal();
    
    public static void main(String[] args) {
        // 生成随机姓名
        String name = mock.names().get();
        
        // 生成随机邮箱
        String email = mock.emails().get();
        
        // 生成随机年龄(18-65岁)
        int age = mock.ints().range(18, 65).get();
        
        // 生成随机城市
        String city = mock.cities().capitals().get();
        
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
    }
}
