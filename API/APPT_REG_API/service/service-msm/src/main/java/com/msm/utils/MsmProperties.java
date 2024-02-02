package com.msm.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "rly.sms") // 该注解是取配置文件中读取指定路径下的配置信息，封装成对象,在封装过程中，会用到成员变量的set方法
public class MsmProperties implements InitializingBean { //继承该类，实现该类的afterPropertiesSet方法，该方法就是在配置文件属性被封装完成后才会调用的
 
    //将配置文件中的配置定义为成员变量，驼峰命名可以自动匹配
    private String accountId;
    private String accountToken;
    private String appId;
    private String serverIp;
    private String serverPort;
 
    //定义静态变量，将上述参数值赋值给这些静态变量，后续通过类名来获取，赋值的前提是配置文件已经读取完毕并且已经封装到上述成员变量中
    public static String ACCOUNT_ID;
    public static String ACCOUNT_TOKEN;
    public static String APP_ID;
    public static String SERVER_IP;
    public static String SERVER_PORT;
 
    //该方法就是在配置文件属性被封装完成后才会被调用
    @Override
    public void afterPropertiesSet() throws Exception {
        ACCOUNT_ID = accountId;
        ACCOUNT_TOKEN = accountToken;
        APP_ID = appId;
        SERVER_IP = serverIp;
        SERVER_PORT = serverPort;
    }
}