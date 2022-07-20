package com.nimo.configserver.controller;

import com.nimo.configserver.scope.RefreshScope;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther zgp
 * @desc 管理配置（CRUD）
 * @date 2022/3/5
 *
 */
@RestController
@RequestMapping("config")
@Scope("refreshScope")
public class ConfigController implements DisposableBean {

    @Autowired
    private ConfigurableApplicationContext context;

    @Autowired
    private Environment environment;

    private static final String CUSTOM_PROPERTY_NAME = "nimo";

    @Value("${nimo.username}")
    private String username;

    @Value("${nimo.password:''}")
    private String password;

    @RequestMapping("/add")
    public String addConfig(@RequestParam  Map<String, Object> params) {
        MutablePropertySources propertySources = context.getEnvironment().getPropertySources();
        propertySources.addLast(new OriginTrackedMapPropertySource(CUSTOM_PROPERTY_NAME, new ConcurrentHashMap()));
        Iterator<PropertySource<?>> iterator = propertySources.iterator();
        while (iterator.hasNext()) {
            PropertySource<?> propertySource = iterator.next();
            if (CUSTOM_PROPERTY_NAME.equals(propertySource.getName())) {
                ConcurrentHashMap source = (ConcurrentHashMap) propertySource.getSource();
                source.putAll(params);
            }
        }

        return "ok";
    }

    @RequestMapping("/get")
    public String getConfig() {
        String property = environment.getProperty("nimo.password");
        System.out.println("@Value：nimo.password" + " : " + password);
        return "Environment: nimo.password " + " : " + property + "\n@Value => nimo.password : " + password;
    }

    @Override
    public void destroy() throws Exception {
    }
}
