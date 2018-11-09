package com.heardfate.springboot.demo.demo04.dbunit;

import org.dbunit.Assertion;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.*;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.*;
import java.sql.SQLException;

/**
 * @since: 2018/11/7
 * @author: Mr.HeardFate
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional("transactionManager")
@Rollback
public class BaseDBUnit extends AbstractTransactionalJUnit4SpringContextTests {
    protected static final Logger logger = LoggerFactory.getLogger(BaseDBUnit.class);
    @Autowired
    private DataSource dataSource;

    private IDatabaseConnection conn;

    private File tempFile;

    public static final String ROOT_URL = System.getProperty("user.dir") + "/src/test/resources/";

    @Before
    public void setup() throws Exception {
        logger.debug("Get DataBaseSourceConnection!");
        // get DataBaseSourceConnection
        conn = new DatabaseConnection(DataSourceUtils.getConnection(dataSource));

        logger.debug("Config database as MySql");
        // config database as MySql
        DatabaseConfig dbConfig = conn.getConfig();
        dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());

    }

    /**
     * @return
     * @Title: getConnection
     */
    protected IDatabaseConnection getConnection() {
        return conn;
    }

    /**
     * @param name
     * @return
     * @throws DataSetException
     * @throws IOException
     * @Title: getXmlDataSet
     */
    protected IDataSet getXmlDataSet(String name) throws DataSetException, IOException {
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        return builder.build(new FileInputStream(new File(ROOT_URL + name)));
    }

    /**
     * Get DB DataSet
     *
     * @return
     * @throws SQLException
     * @Title: getDBDataSet
     */
    protected IDataSet getDBDataSet() throws SQLException {
        return conn.createDataSet();
    }

    /**
     * Get Query DataSet
     *
     * @return
     * @Title: getQueryDataSet
     */
    protected QueryDataSet getQueryDataSet() {
        return new QueryDataSet(conn);
    }

    /**
     * Get Excel DataSet
     *
     * @param name
     * @return
     * @throws DataSetException
     * @throws IOException
     * @Title: getXlsDataSet
     */
    protected XlsDataSet getXlsDataSet(String name) throws DataSetException, IOException {
        InputStream is = new FileInputStream(new File(ROOT_URL + name));
        return new XlsDataSet(is);
    }

    /**
     * backup the whole DB
     *
     * @throws Exception
     * @Title: backupAll
     */
    protected void backupAll() throws Exception {
        // create DataSet from database.
        IDataSet ds = conn.createDataSet();

        // create temp file
        tempFile = File.createTempFile("temp", "xml");

        // write the content of database to temp file
        FlatXmlDataSet.write(ds, new FileWriter(tempFile), "UTF-8");
    }

    /**
     * back specified DB table
     *
     * @param tableName
     * @throws Exception
     * @Title: backupCustom
     */
    protected void backupCustom(String... tableName) throws Exception {
        // back up specific files
        QueryDataSet qds = new QueryDataSet(conn);
        for (String str : tableName) {
            qds.addTable(str);
        }
        tempFile = File.createTempFile("temp", "xml");
        FlatXmlDataSet.write(qds, new FileWriter(tempFile), "UTF-8");

    }

    /**
     * back specified DB table
     *
     * @param tableName
     * @throws Exception
     * @Title: backupCustomAud
     */
    protected void backupCustomAud(String... tableName) throws Exception {
        // back up specific files
        QueryDataSet qds = new QueryDataSet(conn);
        qds.addTable("revinfo");
        for (String str : tableName) {
            qds.addTable(str);
            qds.addTable(str + "_aud");
        }
        tempFile = File.createTempFile("temp", "xml");
        FlatXmlDataSet.write(qds, new FileWriter(tempFile), "UTF-8");

    }

    /**
     * rollback database
     *
     * @throws Exception
     * @Title: rollback
     */
    protected void rollback() throws Exception {
        logger.debug("rollback database Start!");
        logger.debug("get the temp file");
        // get the temp file
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        IDataSet ds = builder.build(new FileInputStream(tempFile));

        logger.debug("recover database");
        // recover database
        DatabaseOperation.CLEAN_INSERT.execute(conn, ds);
        logger.info("rollback database success!");
    }

    /**
     * Clear data of table
     *
     * @param tableName
     * @throws Exception
     */
    protected void clearTable(String tableName) throws Exception {
        logger.debug("clear table");
        DefaultDataSet dataset = new DefaultDataSet();
        dataset.addTable(new DefaultTable(tableName));
        DatabaseOperation.DELETE_ALL.execute(conn, dataset);
    }

    /**
     * Clear data of table And verify Table is Empty
     *
     * @param tableName
     * @throws Exception
     */
    protected void clearAndVerifyTable(String tableName) {
        try {
            clearTable(tableName);
            verifyTableEmpty(tableName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * verify Table is Empty
     *
     * @param tableName
     * @throws DataSetException
     * @throws SQLException
     */
    protected void verifyTableEmpty(String tableName) throws DataSetException, SQLException {
        logger.debug("verify table is empty");
        Assert.assertEquals(0, conn.createDataSet().getTable(tableName).getRowCount());
    }

    /**
     * verify Table is not Empty
     *
     * @param tableName
     * @throws DataSetException
     * @throws SQLException
     * @Title: verifyTableNotEmpty
     */
    protected void verifyTableNotEmpty(String tableName) throws DataSetException, SQLException {
        logger.debug("verify table is not empty");
        Assert.assertNotEquals(0, conn.createDataSet().getTable(tableName).getRowCount());
    }

    /**
     * @param dataSet
     * @return
     * @Title: createReplacementDataSet
     */
    protected ReplacementDataSet createReplacementDataSet(IDataSet dataSet) {
        ReplacementDataSet replacementDataSet = new ReplacementDataSet(dataSet);

        // Configure the replacement dataset to replace '[NULL]' strings with
        // null.
        replacementDataSet.addReplacementObject("[null]", null);

        return replacementDataSet;
    }

    protected void verifyXmlDataWithCheckXml(String tableName, String checkDriverXml) {
        try {
            ITable dbTable = getDBDataSet().getTable(tableName);
            logger.debug("dbTable {}",dbTable);
            // 预期结果
            IDataSet expectedDataSet = getXmlDataSet(checkDriverXml);
            ReplacementDataSet replacementDataSet = createReplacementDataSet(expectedDataSet);
            replacementDataSet.addReplacementObject("[NULL]", null);
            ITable expectedTable = replacementDataSet.getTable(tableName);
            logger.debug("expectedTable {}",expectedTable);
            Assertion.assertEquals(dbTable, expectedTable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
