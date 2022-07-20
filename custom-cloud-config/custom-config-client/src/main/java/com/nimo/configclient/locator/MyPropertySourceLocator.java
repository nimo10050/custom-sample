package com.nimo.configclient.locator;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

import java.util.Collection;

/**
 * @auther zgp
 * @desc
 * @date 2022/3/10
 */
public class MyPropertySourceLocator implements PropertySourceLocator {
    @Override
    public PropertySource<?> locate(Environment environment) {
        System.out.println("=========");
        return null;
    }

    @Override
    public Collection<PropertySource<?>> locateCollection(Environment environment) {
        System.out.println("locateCollection");
        return PropertySourceLocator.super.locateCollection(environment);
    }
}
