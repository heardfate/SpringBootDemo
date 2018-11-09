package com.heardfate.springboot.demo.demo04.dao;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.heardfate.springboot.demo.demo04.dbunit.BaseDBUnit;
import com.heardfate.springboot.demo.demo04.entity.User;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @since: 2018/11/7
 * @author: Mr.HeardFate
 */
public class UserDaoTest extends BaseDBUnit {

    @Autowired
    private UserDao mapper;

    private final String tableName = "user";

    /**
     * 清空数据插入准备的数据
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        try {
            IDataSet dataSet = getXmlDataSet("UserDriver.xml");
            DatabaseOperation.CLEAN_INSERT.execute(getConnection(), dataSet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 检测XML数据插入是否符合预期
     *
     * @throws Exception
     */
    @Test
    public void check_xml_data() {
        verifyXmlDataWithCheckXml(tableName, "UserDriver_check.xml");
    }

    @Test
    public void insert() {
        // 插入数据前先清空所有数据
        clearAndVerifyTable(tableName);

        User bean = new User();
        bean.setName("testUser");
        bean.setAge(20);
        bean.setEmail("testUser@heardfate.com");
        bean.setId(new BigInteger(IdWorker.getIdStr()));

        logger.debug("{}:{}", bean.getClass().getName(), bean);
        assertEquals("insert data error", 1, mapper.insert(bean));
        logger.debug("{} id:{}", bean.getClass().getName(), bean.getId());
        assertNotNull("Id is null", bean.getId());
    }

    @Test
    public void deleteById() {

    }

    @Test
    public void deleteByMap() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void deleteBatchIds() {
    }

    @Test
    public void updateById() {
    }

    @Test
    public void update() {
    }

    @Test
    public void selectById() {
    }

    @Test
    public void selectBatchIds() {
    }

    @Test
    public void selectByMap() {
    }

    @Test
    public void selectOne() {
    }

    @Test
    public void selectCount() {
    }

    @Test
    public void selectList() {
        List<User> list = mapper.selectList(null);
        assertNotNull("Client list is null", list);
        logger.debug("get page row count={}", list.size());
        assertThat("select data size is zero", list.size(), Matchers.greaterThanOrEqualTo(1));
        list.forEach((user) -> logger.debug("user inof: {}", user));
    }

    @Test
    public void selectMaps() {
    }

    @Test
    public void selectObjs() {
    }

    @Test
    public void selectPage() {
    }

    @Test
    public void selectMapsPage() {
    }
}