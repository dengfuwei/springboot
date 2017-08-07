package com.itopener.demo.druid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
@SpringBootApplication
@MapperScan("com.itopener.demo.druid.mapper")
public class Application {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
