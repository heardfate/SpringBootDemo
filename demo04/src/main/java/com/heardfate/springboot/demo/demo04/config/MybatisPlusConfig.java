package com.heardfate.springboot.demo.demo04.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @since: 2018/10/23
 * @author: Mr.HeardFate
 */
@Configuration
@MapperScan("com.heardfate.springboot.demo.demo04.dao")
public class MybatisPlusConfig {

}
