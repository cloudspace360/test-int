package com.se.wp.library.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import net.sf.ehcache.CacheManager;


@Configuration
public class CacheConfig {
   
    @Bean
    public CacheManager ehCacheManager() {
        return new net.sf.ehcache.CacheManager();
    }
}