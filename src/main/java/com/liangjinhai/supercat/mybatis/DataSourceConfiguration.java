package com.liangjinhai.supercat.mybatis;

//import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan("com.liangjinhai.supercat.sys.mapper")
public class DataSourceConfiguration {
    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Bean(name="dataSource")
    public DruidDataSource creataDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(jdbcDriver);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        //关闭连接后不自动commit
        dataSource.setDefaultAutoCommit(false);
        return dataSource;
    }

//    @Bean(name="dataSource")
//    public ComboPooledDataSource creatDataSource() throws PropertyVetoException {
//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        dataSource.setDriverClass(jdbcDriver);
//        dataSource.setJdbcUrl(jdbcUrl);
//        dataSource.setUser(jdbcUsername);
//        dataSource.setPassword(jdbcPassword);
//        dataSource.setAutoCommitOnClose(false);
//        return  dataSource;
//    }



}
