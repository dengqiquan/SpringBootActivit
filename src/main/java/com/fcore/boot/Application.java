package com.fcore.boot;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

import com.fcore.boot.config.UserRealm;

@SpringBootApplication
@RestController
@ServletComponentScan
@EnableScheduling
@MapperScan("com.fcore.boot.domain")  
@EnableTransactionManagement
public class Application {
	
	protected static Logger logger=LoggerFactory.getLogger(Application.class);  
    
    @PostConstruct
    public  void init() {
    	//========初始化开始============
    	logger.info("=================系统初始化===================");
    }
    
    @PreDestroy
	public void  dostory(){
    	logger.info("=================系统关闭注销===================");
	}
    
	public static void main(String[] args) {
		 System.setProperty("spring.devtools.restart.enabled", "false");
		//启动程序
		 ApplicationContext ctx =  (ApplicationContext)SpringApplication.run(Application.class, args);
		/*//获取BeanFactory
		 DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) ctx.getAutowireCapableBeanFactory();
		       
		 //创建bean信息.
		 BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserRealm.class);
		 beanDefinitionBuilder.addPropertyValue("name","张三");
		       
		 //动态注册bean.
		 defaultListableBeanFactory.registerBeanDefinition("testService", beanDefinitionBuilder.getBeanDefinition());
		 defaultListableBeanFactory.removeBeanDefinition("testService");*/
		
	}
}
