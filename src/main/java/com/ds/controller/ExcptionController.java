package com.ds.controller;

import java.util.Date;


import com.ds.domain.MyException;
import com.ds.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述：异常测试
 */
@RestController
public class ExcptionController {

	@RequestMapping(value = "/api/v1/test_ext")  
	public Object index() {
		int i= 1/0;
		return new User(11, "sfsfds", "1000000", new Date());
	}


	/**
	 * 功能描述：模拟自定义异常
	 * @return
	 */
	@RequestMapping("/api/v1/myext")
	public Object myexc(){

		throw new MyException("499", "my ext异常");

	}
	
}
