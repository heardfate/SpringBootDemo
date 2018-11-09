package com.heardfate.springboot.demo.controller;

import com.heardfate.springboot.demo.properties.MyTestProperties1;
import com.heardfate.springboot.demo.properties.MyTestProperties2;
import com.heardfate.springboot.demo.properties.MyTestRandomProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @since: 2018/10/18
 * @author: Mr.HeardFate
 */
@RestController
public class HelloController {

    private final MyTestProperties1 myTestProperties1;

    @Autowired
    private  MyTestProperties2 myTestProperties2;

    public HelloController(MyTestProperties1 myTestProperties1){
        this.myTestProperties1 = myTestProperties1;
    }

    @Autowired
    private  MyTestRandomProperties myTestRandomProperties;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/pro1")
    public MyTestProperties1 myProperties1() {
        System.out.println("=====================MyTestProperties1=====================");
        System.out.println(myTestProperties1.toString());
        System.out.println("=====================MyTestProperties1=====================");
        return myTestProperties1;
    }

    @RequestMapping("/pro2")
    public MyTestProperties2 myProperties2() {
        System.out.println("=====================MyTestProperties2=====================");
        System.out.println(myTestProperties2.toString());
        System.out.println("=====================MyTestProperties2=====================");
        return myTestProperties2;
    }

    @RequestMapping("/random")
    public MyTestRandomProperties myTestRandomProperties() {
        System.out.println("=====================MyTestRandomProperties=====================");
        System.out.println(myTestRandomProperties.toString());
        System.out.println("=====================MyTestRandomProperties=====================");
        return myTestRandomProperties;
    }
}
