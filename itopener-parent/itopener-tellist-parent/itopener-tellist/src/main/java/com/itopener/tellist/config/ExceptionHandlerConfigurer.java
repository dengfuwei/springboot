package com.itopener.tellist.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itopener.framework.ResultMap;
import com.itopener.framework.base.BaseRuntimeException;

@ControllerAdvice
public class ExceptionHandlerConfigurer {

	@ExceptionHandler(value = BaseRuntimeException.class)
    @ResponseBody
    public ResultMap jsonErrorHandler(HttpServletRequest request, BaseRuntimeException e) {
		return ResultMap.buildFailed(e.getMessage());
    }
}
