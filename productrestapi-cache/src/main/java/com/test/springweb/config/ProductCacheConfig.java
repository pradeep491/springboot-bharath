package com.test.springweb.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductCacheConfig {

    public Config cacheConfig(){

        return new Config().setInstanceName("hazel-instance")
                .addMapConfig(new MapConfig().setName("product-cache")
                        .setTimeToLiveSeconds(3000));
    }
}
