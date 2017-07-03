package com.itopener.demo.druid.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import com.itopener.druidstat.spring.boot.autoconfigure.DruidStatAutoConfiguration;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
@Profile({"dev", "test"})
@Configuration
@Import(DruidStatAutoConfiguration.class)
public class DruidStatConfiguration {

}
