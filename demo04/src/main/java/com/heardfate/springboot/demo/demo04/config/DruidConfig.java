package com.heardfate.springboot.demo.demo04.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @since: 2018/10/25
 * @author: Mr.HeardFate
 */
@Configuration
public class DruidConfig {
    /**
     * 主要实现WEB监控的配置处理
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        // 现在要进行druid监控的配置处理操作
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
                new StatViewServlet(), "/druid/*");
        // 白名单
        //servletRegistrationBean.addInitParameter("allow", "127.0.0.1,192.168.1.159");
        // 黑名单
        // servletRegistrationBean.addInitParameter("deny", "192.168.1.200");
        // 用户名
        servletRegistrationBean.addInitParameter("loginUsername", "user");
        // 密码
        servletRegistrationBean.addInitParameter("loginPassword", "test");
        // 是否可以重置数据源
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean ;
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean() ;
        filterRegistrationBean.setFilter(new WebStatFilter());
        // 所有请求进行监控处理
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.css,/druid/*");
        return filterRegistrationBean ;
    }
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }
}
