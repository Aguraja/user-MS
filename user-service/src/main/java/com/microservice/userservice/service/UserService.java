package com.microservice.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.userservice.VO.Department;
import com.microservice.userservice.VO.ResponseTemplateVO;
import com.microservice.userservice.entity.User;
import com.microservice.userservice.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public ResponseTemplateVO getUserWithDepartment(Long userId) {
		ResponseTemplateVO response = new ResponseTemplateVO();
		User user = userRepository.findAllByUserId(userId);
		
		Department department = restTemplate.getForObject("http://localhost:9001/departments/"+ user.getDepartmentId(), Department.class);
		response.setUser(user);
		response.setDepartment(department);
		
		return response;
	}

}
