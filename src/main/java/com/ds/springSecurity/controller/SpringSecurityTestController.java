package com.ds.springSecurity.controller;

import com.ds.domain.User;
import com.ds.service.SpringTestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//测试http协议的get请求
@RestController
public class SpringSecurityTestController {
	private Map<String,Object> params = new HashMap<>();

	//获取用户权限信息
	@PostMapping(value ="/info")
	//开启跨域
	// [普通跨域]
	//@CrossOrigin
	//[spring security 跨域]
//    @CrossOrigin(allowCredentials="true",allowedHeaders="*")
	public Object info() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return principal;
	}

	@PostMapping(value ="/testinfo")
	public Object testinfo(String username, String password, String rememberMe){
		params.clear();
		params.put("id", username);
		params.put("pwd", password);
		return params;
	}

	@PostFilter("filterObject.lastIndexOf('2')!=-1")
	public List<String> getAllUser() {
		List<String> users = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			users.add("javaboy:" + i);
		}
		return users;
	}

	//获取用户
	@RequestMapping(value ="/getid/{id}")
	//开启跨域
	// [普通跨域]
	//@CrossOrigin
	//[spring security 跨域]
//	@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
	@ResponseBody
	public Map<String, Object> getid(@PathVariable String id) {
		System.out.println("参数为==" + id);
		Map<String, Object> map = new HashMap<>();
		map.put("data", "参数为==" + id);
		return map;
	}
}
