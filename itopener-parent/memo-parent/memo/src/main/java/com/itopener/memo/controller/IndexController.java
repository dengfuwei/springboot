package com.itopener.memo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.framework.ResultMap;

@RestController
public class IndexController {

	@GetMapping("index")
	public ResultMap index(){
		return ResultMap.buildSuccess();
	}
}
