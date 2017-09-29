package com.itopener.memo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.itopener.framework.ResultMap;
import com.itopener.memo.vo.MemoParams;

@RestController
@RequestMapping("memo")
public class MemoController {
	
	private final Logger logger = LoggerFactory.getLogger(MemoController.class);

	@PutMapping
	public ResultMap save(@RequestBody MemoParams params) {
		logger.info(JSON.toJSONString(params));
		return ResultMap.buildSuccess();
	}
}
