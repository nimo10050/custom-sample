package com.nimo.configclient.configure;

import com.nimo.configclient.locator.MyPropertySourceLocator;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther zgp
 * @desc
 * @date 2022/3/9
 */
@Configuration
public class ConfigClientConfigure {

    @Bean
    public PropertySourceLocator propertySourceLocator() {
        return new MyPropertySourceLocator();
    }

}
