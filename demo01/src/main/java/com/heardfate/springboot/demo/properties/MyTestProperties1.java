package com.heardfate.springboot.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @since: 2018/10/21
 * @author: Mr.HeardFate
 */
@Component
@ConfigurationProperties(prefix = "test")
public class MyTestProperties1 {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyTestProperties1{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
