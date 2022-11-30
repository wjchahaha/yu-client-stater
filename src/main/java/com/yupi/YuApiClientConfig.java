package com.yupi;

import com.yupi.client.YupiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.beans.ConstructorProperties;

/**
* @program: yupi-client-sdk
*
* @description: 
*
* @author: Mr.Wang
*
* @create: 2022-11-30 22:14
**/
@Configuration
@ConfigurationProperties("yuapi.client")
@Data
@ComponentScan
public class YuApiClientConfig {

    private String accessKey;
    private String secretKey;
    @Bean
    public YupiClient yupiClient(){
        return new YupiClient(accessKey,secretKey);
    }
}
