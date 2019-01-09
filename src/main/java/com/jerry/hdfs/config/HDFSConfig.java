package com.jerry.hdfs.config;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jerry.Wu
 * @description:
 * @date 2019/1/8 9:55
 */
@Configuration
public class HDFSConfig {

    @Value("${hdfs.defaultFS}")
    private String defaultFS;

//    @Bean
//    public HDFSServiceImpl getHbaseService(){
//        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
//        conf.set("fs.defaultFS", defaultFS);
//        return new HDFSServiceImpl(conf, defaultFS);
//    }

    @Bean
    public org.apache.hadoop.conf.Configuration getConfiguration() {
        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
        conf.set("fs.defaultFS", defaultFS);
        return conf;
    }

}
