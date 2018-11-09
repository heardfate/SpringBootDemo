package com.heardfate.springboot.demo;

import com.heardfate.springboot.demo.demo04.dao.UserDao;
import com.heardfate.springboot.demo.demo04.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @since: 2018/10/23
 * @author: Mr.HeardFate
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userDao.selectList(null);
        Assert.assertTrue("size error", userList.size() > 0);
        userList.forEach(System.out::println);
    }
}
