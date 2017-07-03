package com.itopener.demo.jsonp.jackson.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

@Configuration
@ControllerAdvice
public class JsonpSupportAdvice extends AbstractJsonpResponseBodyAdvice {

	public JsonpSupportAdvice() {  
        //参数包含callback的时候 使用jsonp的反馈形式  
        super("callback");  
    } 
}
