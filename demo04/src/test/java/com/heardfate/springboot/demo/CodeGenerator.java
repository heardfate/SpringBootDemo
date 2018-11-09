package com.heardfate.springboot.demo;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: springboot-demo04
 * @since: 2018/10/23
 * @author: Mr.HeardFate
 */
// 演示例子
public class CodeGenerator {
    /**
     * 包名
     */
    private static final String PACKAGE_NAME = "com.heardfate.springboot.demo";
    /**
     * 模块名称
     */
    private static final String MODULE_NAME = "demo04";
    private static final String NAME = "";
    /**
     * 输出文件的路径
     */
    private static final String OUT_PATH = System.getProperty("user.dir") + NAME + "/src/main/java";
    /**
     * 输出文件的路径
     */
    private static final String OUT_PATH_MAPPING = System.getProperty("user.dir") + NAME + "/src/main/resources/mapper";
    /**
     * 代码生成者
     */
    private static final String AUTHOR = "Heardfate";

    /**
     * JDBC相关配置
     */
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&useSSL=false" +
            "&characterEncoding=UTF-8";
    private static final String USER_NAME = "testuser";
    private static final String PASSWORD = "";

    /**
     * MySQL 生成演示
     */
    public static void main(String[] args) {
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<TableFill>();
        //如 每张表都有一个创建时间、修改时间
        //而且这基本上就是通用的了，新增时，创建时间和修改时间同时修改
        //修改时，修改时间会修改，
        //虽然像Mysql数据库有自动更新几只，但像ORACLE的数据库就没有了，
        //使用公共字段填充功能，就可以实现，自动按场景更新了。
        //如下是配置
        //TableFill createField = new TableFill("gmt_create", FieldFill.INSERT);
        //TableFill modifiedField = new TableFill("gmt_modified", FieldFill.UPDATE);
        //
        //tableFillList.add(createField);
        //tableFillList.add(modifiedField);

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        mpg.setGlobalConfig(
                // 全局配置
                new GlobalConfig().setOutputDir(OUT_PATH)// 输出目录
                        .setFileOverride(true)// 是否覆盖文件
                        .setActiveRecord(true)// 开启 activeRecord 模式
                        .setEnableCache(false)// XML 二级缓存
                        .setBaseResultMap(false)// XML ResultMap
                        .setBaseColumnList(true)// XML columList
                        .setAuthor(AUTHOR)
                        // 自定义文件命名，注意 %s 会自动填充表实体属性！
                        .setXmlName("%sMapper").setMapperName("%sDao")
                        .setSwagger2(true)
                // .setServiceName("MP%sService")
                // .setServiceImplName("%sServiceDiy")
                // .setControllerName("%sAction")
        );

        mpg.setDataSource(
                // 数据源配置
                new DataSourceConfig().setDbType(DbType.MYSQL)// 数据库类型
                        .setDriverName(DRIVER).setUsername(USER_NAME).setPassword(PASSWORD).setUrl(URL).setTypeConvert(new MySqlTypeConvert() {
                    // 自定义数据库表字段类型转换【可选】
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        System.out.println("待转换类型：" + fieldType);
                        // 注意！！！processTypeConvert存在默认类型转换，如果不是你要的效果请自定义返回。
                        String t = fieldType.toLowerCase();
                        if (t.contains("unsigned")) {
                            if (t.contains("bigint")) {
                                return DbColumnType.BIG_INTEGER;
                            }else{
                                System.err.println("I am not know!");
                                return super.processTypeConvert(globalConfig, fieldType);
                            }
                        }else{
                            return super.processTypeConvert(globalConfig, fieldType);
                        }

                    }
                }));
        mpg.setStrategy(
                // 策略配置
                new StrategyConfig()
                        // .setCapitalMode(true)// 全局大写命名
                        //.setTablePrefix(new String[]{"table_", "test_"})// 此处可以修改为您的表前缀
                        .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                        .setColumnNaming(NamingStrategy.underline_to_camel)
                        //.setLogicDeleteFieldName("is_delete") //逻辑删除字段
                        // .setInclude(new String[] {"user"}) // 需要生成的表
                        .setExclude(new String[]{"account"}) // 排除生成的表
                        // 自定义实体，公共字段
                        //.setSuperEntityColumns(new String[]{"gmt_create", "gmt_modified", "create_user",
                        //        "executor", "is_delete", "pass_key"})
                        //.setTableFillList(tableFillList)//和68行对应，设置公共填充字段
                        // 自定义实体父类
                        //.setSuperEntityClass("com.baomidou.demo.base.BsBaseEntity")
                        // // 自定义 mapper 父类
                        // .setSuperMapperClass("com.baomidou.demo.base.BsBaseMapper")
                        // // 自定义 service 父类
                        // .setSuperServiceClass("com.baomidou.demo.base.BsBaseService")
                        // // 自定义 service 实现类父类
                        // .setSuperServiceImplClass("com.baomidou.demo.base.BsBaseServiceImpl")
                        // 自定义 controller 父类
                        // .setSuperControllerClass("com.baomidou.demo.TestController")
                        // 【实体】是否生成字段常量（默认 false）
                        // public static final String ID = "test_id";
                        .setEntityColumnConstant(true)
                        // 【实体】是否为构建者模型（默认 false）
                        // public User setName(String name) {this.name = name; return this;}
                        .setEntityBuilderModel(true)
                        // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                        .setEntityLombokModel(true)
                        // Boolean类型字段是否移除is前缀处理
                        .setEntityBooleanColumnRemoveIsPrefix(true)
                        //生成 @RestController 控制器
                        .setRestControllerStyle(true)
                //驼峰转连字符
                //.setControllerMappingHyphenStyle(true)

        );
        mpg.setPackageInfo(
                // 包配置
                new PackageConfig().setModuleName(MODULE_NAME).setParent(PACKAGE_NAME)// 自定义包路径
                        .setController("controller")// 这里是控制器包名，默认 web
                        .setXml("mapper").setMapper("dao")

        );
        mpg.setCfg(
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                new InjectionConfig() {
                    @Override
                    public void initMap() {
                    }
                }.setFileOutConfigList(
                        Collections.<FileOutConfig>singletonList(new FileOutConfig("/templates/mapper.xml" +
                                ".ftl") {
                            // 自定义输出文件目录
                            @Override
                            public String outputFile(TableInfo tableInfo) {
                                return OUT_PATH_MAPPING + "/" + MODULE_NAME
                                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                            }
                        })));
        mpg.setTemplate(
                // 关闭默认 xml 生成，调整生成 至 根目录
                new TemplateConfig().setXml(null)
                // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
                // .setController("...");
                // .setEntity("...");
                // .setMapper("...");
                // .setXml("...");
                // .setService("...");
                // .setServiceImpl("...");
        );
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 执行生成
        mpg.execute();

    }
}