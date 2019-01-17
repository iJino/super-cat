package com.liangjinhai.supercat;

import com.github.pagehelper.PageHelper;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.ServletRegistration;
import java.util.Properties;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
//@EnableDiscoveryClient
//@EnableFeignClients
@MapperScan("com.liangjinhai.supercat.sys.mapper")
public class SupercatApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(SupercatApplication.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(SupercatApplication.class, args);
	}

	@Bean
	public PageHelper getPageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("helperDialect", "mysql");
		properties.setProperty("reasonable", "true");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("params", "count=countSql");
		pageHelper.setProperties(properties);
		return pageHelper;
	}

	/**
	 * 通过propertysource读取自定义yml文件
	 * */
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
//		yaml.setResources(new FileSystemResource("classpath:config/redis.yml"));//File引入
		yaml.setResources(new ClassPathResource("config/redis.yml"));//File引入
//		yaml.setResources(new ClassPathResource("youryml.yml"));//class引入
		configurer.setProperties(yaml.getObject());
		return configurer;
	}

	/**
	 * 注册jersey资源路径
	 */
	@Bean
	public ServletRegistrationBean jersryServlet(){
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new ServletContainer(), "/rest/*");
		servletRegistrationBean.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
		servletRegistrationBean.setName("jersey");
		return servletRegistrationBean;
	}
}
