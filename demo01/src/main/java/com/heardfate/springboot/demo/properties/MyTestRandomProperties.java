package com.heardfate.springboot.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @since: 2018/10/21
 * @author: Mr.HeardFate
 */
@Component
@ConfigurationProperties(prefix = "com.heardfate.random")
public class MyTestRandomProperties {
    // # 随机字符串
    private String secret;
    // # 随机int
    private int number;
    // # 随机long
    private long bignumber;
    // # UUID值
    private String uuid;
    // # 10以内的随机数
    private int lessThan;
    // # 10-100的随机数
    private int inRange;
    // # 100前面和1000后面可以是 -，(，[ 等任意字符 int.inRange: ${random.int-100,1000-}
    private int inRange2;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getBignumber() {
        return bignumber;
    }

    public void setBignumber(long bignumber) {
        this.bignumber = bignumber;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getLessThan() {
        return lessThan;
    }

    public void setLessThan(int lessThan) {
        this.lessThan = lessThan;
    }

    public int getInRange() {
        return inRange;
    }

    public void setInRange(int inRange) {
        this.inRange = inRange;
    }

    public int getInRange2() {
        return inRange2;
    }

    public void setInRange2(int inRange2) {
        this.inRange2 = inRange2;
    }

    @Override
    public String toString() {
        return "MyTestRandomProperties{" +
                "secret='" + secret + '\'' +
                ", number=" + number +
                ", bignumber=" + bignumber +
                ", uuid='" + uuid + '\'' +
                ", lessThan=" + lessThan +
                ", inRange=" + inRange +
                ", inRange2=" + inRange2 +
                '}';
    }
}
