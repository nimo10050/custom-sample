package com.nimo.configserver.configure;

import com.nimo.configserver.scope.RefreshScope;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther zgp
 * @desc
 * @date 2022/3/9
 */
@Configuration
public class ConfigServerConfigure {

    @Bean
    public RefreshScope scope() {
        return new RefreshScope();
    }

    @Bean
    public CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
        customScopeConfigurer.addScope("refreshScope", scope());
        return customScopeConfigurer;
    }

}
