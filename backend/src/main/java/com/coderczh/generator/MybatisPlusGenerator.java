package com.coderczh.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MybatisPlusGenerator {
    private static final String URL = "jdbc:mysql://43.137.8.130:3306/llm?characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "080923";
    private static final String AUTHOR = "天天向上";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String ROOT_DIR = "/src/main/java";
    private static final String BASE_DIR = "com.coderczh.backend";
    private static final String MAPPER_DIR = "/src/main/resources/mapper";
    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String TABLE_NAME = "tbl_user_info";

    public static void main(String[] args) {
        System.out.println("=======数据表生成中========");
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig(builder -> builder.author(AUTHOR)
                        .commentDate(DATE_FORMAT)
                        .outputDir(USER_DIR + ROOT_DIR)
                        .disableOpenDir())
                .packageConfig(builder -> builder.parent(BASE_DIR)
                        .entity("entity")
                        .mapper("dao")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, USER_DIR + MAPPER_DIR)))
                .strategyConfig(builder -> builder.addInclude(TABLE_NAME)
                        .addTablePrefix("tbl_")
                        .entityBuilder()
                        .enableLombok()
                        .enableChainModel()
                        .enableFileOverride()
                        .naming(NamingStrategy.underline_to_camel)
                        .columnNaming(NamingStrategy.underline_to_camel)
                        .enableTableFieldAnnotation()
                        .mapperBuilder()
                        .enableFileOverride()
                        .formatMapperFileName("%sDao")
                        .enableBaseResultMap()
                        .serviceBuilder()
                        .disable()
                        .controllerBuilder()
                        .disable())
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
        System.out.println("=======数据表生成成功========");
    }
}
