package com.ds.controller;


import java.util.Date;


import com.ds.domain.JsonData;
import com.ds.domain.User;
import com.ds.mapper.UserMapper;
import com.ds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;
	
	
	/**
	 * 功能描述: user 保存接口
	 * @return
	 */
	@GetMapping("add")
	public Object add(){
		
		User user = new User();
		user.setAge(11);
		user.setCreateTime(new Date());
		user.setName("xdclass");
		user.setPhone("10010000");
		int id = userService.add(user);
		
       return JsonData.buildSuccess(id);
	}
	
	
	
	/**
	 * 功能描述：查找全部用户
	 * @return
	 */
	@GetMapping("findAll")
	public Object findAll(){
		
       return JsonData.buildSuccess(userMapper.getAll());
	}
	
	
	
	@GetMapping("find_by_id")
	public Object findById(long id){
       return JsonData.buildSuccess(userMapper.findById(id));
	}
	
	
	@GetMapping("del_by_id")
	public Object delById(long id){
	userMapper.delete(id);
       return JsonData.buildSuccess();
	}
	
	@GetMapping("update")
	public Object update(String name,int id){
		User user = new User();
		user.setName(name);
		user.setId(id);
		userMapper.update(user);
	    return JsonData.buildSuccess();
	}
	
	
	
	
	
	
	//测试事务
	@GetMapping("add_account")
	public Object addAccount(){
		int id = userService.addAccount();
	    return JsonData.buildSuccess(id);
	}
	
	
	
	
}
