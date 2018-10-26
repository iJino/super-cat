package com.liangjinhai.supercat.common.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @Author: liangJinHai
 * @Date: 2018/10/26 13:25
 * @Description: MongoDB连接配置
 */
@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
    @Value("${spring.data.mongodb.host}")
    private String mongoHost;
    @Value("${spring.data.mongodb.port}")
    private String mongoPort;
    @Value("${spring.data.mongodb.database}")
    private String mongoDataBaseName;

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(mongoHost,Integer.valueOf(mongoPort));
    }

    @Override
    protected String getDatabaseName() {
        return mongoDataBaseName;
    }
}
