package com.ds.service.impl;

import com.ds.domain.User;
import com.ds.mapper.UserMapper;
import com.ds.service.SpringTestUserService;
import com.ds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
@Service
public class SpringTestUserServiceImpl implements SpringTestUserService {

	@Override
	public int add(User user) {
		return  11;
	}


}
