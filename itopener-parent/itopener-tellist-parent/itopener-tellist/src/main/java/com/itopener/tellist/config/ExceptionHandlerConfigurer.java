package com.itopener.tellist.config;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itopener.framework.ResultMap;
import com.itopener.framework.base.BaseRuntimeException;

@ControllerAdvice
public class ExceptionHandlerConfigurer {
	
	private final Logger logger = LoggerFactory.getLogger(ExceptionHandlerConfigurer.class);

	@ExceptionHandler(value = BaseRuntimeException.class)
    @ResponseBody
    public ResultMap baseRuntimeExceptionHandler(HttpServletRequest request, BaseRuntimeException e) {
		logger.error(e.getMessage());
		return ResultMap.buildFailed(e.getMessage());
    }
}
