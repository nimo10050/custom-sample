package com.nimo.configserver.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther zgp
 * @desc
 * @date 2022/3/9
 */
public class RefreshScope implements Scope {

    private ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Object obj = map.get(name);
        if (obj == null) {
            obj = objectFactory.getObject();
            map.put(name, obj);
        }
        return obj;
    }

    @Override
    public Object remove(String name) {
        return map.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        System.out.println("registerDestructionCallback");
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return UUID.randomUUID().toString();
    }
}
