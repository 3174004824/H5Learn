package com.h5education.demo.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFiler(){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        Map<String,String> map = new LinkedHashMap<>();

        map.put("/root/*","perms[root]");
        map.put("/upload/*","perms[root]");

        bean.setFilterChainDefinitionMap(map);
        bean.setUnauthorizedUrl("/After/notRoot");
        bean.setLoginUrl("/root");
        bean.setSecurityManager(getwebSecurityManager());
        return bean;
    }

    @Bean
    public DefaultWebSecurityManager getwebSecurityManager(){
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        webSecurityManager.setRealm(userRealm());
        return webSecurityManager;
    }


    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
    @Bean
    //整合ShiroDialect:用来整合Shiro和thymeleaf
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
