package com.heardfate.springboot.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @since: 2018/10/21
 * @author: Mr.HeardFate
 */
@Component
@PropertySource("classpath:testProperties.properties")
@ConfigurationProperties(prefix = "com.heardfate")
public class MyTestProperties2 {
    private int age;
    private String name;
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "MyTestProperties2{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
