package com.greencoffee.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greencoffee.common.entity.Role;
import com.greencoffee.common.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	public List<User> listAll(){
		return (List<User>) repo.findAll();
	}
	   
	public List<Role> listRoles(){
		return (List<Role>) roleRepo.findAll();
	}

	public void save(User user) {
		// TODO Auto-generated method stub
		repo.save(user);
	}

}
