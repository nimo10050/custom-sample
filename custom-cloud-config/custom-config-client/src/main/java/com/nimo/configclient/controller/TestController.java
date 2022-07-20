//package com.nimo.configserver.controller;
//
//import com.nimo.configserver.scope.RefreshScope;
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @auther zgp
// * @desc
// * @date 2022/3/9
// */
//@RestController
//@RequestMapping("test")
//public class TestController implements DisposableBean {
//
//    @Autowired
//    private ConfigurableApplicationContext context;
//
//    @Autowired
//    private RefreshScope scope;
//
//    @RequestMapping("refresh")
//    public String refreshAll() {
//        scope.remove("configController");
//
////        BeanDefinitionRegistry registry = (DefaultListableBeanFactory)context.getBeanFactory();
////        registry.removeBeanDefinition("configController");
////
////        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
////        System.out.println(beanFactory.containsBean("configController"));
//        return "success";
//    }
//
//
//    @Override
//    public void destroy() throws Exception {
//
//    }
//}
