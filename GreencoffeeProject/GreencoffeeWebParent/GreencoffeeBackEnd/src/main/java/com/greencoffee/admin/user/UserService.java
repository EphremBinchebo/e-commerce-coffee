package com.greencoffee.admin.user;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greencoffee.common.entity.Role;
import com.greencoffee.common.entity.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
	
	public static final int USERS_PER_PAGE = 4;
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private RoleRepository roleRepo;
	/*
	 * @Autowired(required=false) private PasswordEncoder passwordEncoder;
	 */
	
	public List<User> listAll(){
		return (List<User>) repo.findAll();
	}
	   
	public List<Role> listRoles(){
		return (List<Role>) roleRepo.findAll();
	}

	public User save(User user) {
		boolean isUpdatingUser = (user.getId() != null);
		
		if(isUpdatingUser) {
			User existingUser = repo.findById(user.getId()).get();
		    
			repo.save(existingUser);
					
		}
		//encodePassword(user);
		return repo.save(user);
	}
	
	/*
	 * public void encodePassword(User user) { String encodedPassword =
	 * passwordEncoder.encode(user.getPassword());
	 * user.setPassword(encodedPassword); }
	 */
	public Page<User> listByPage(int pageNum){
		Pageable pageable = PageRequest.of(pageNum - 1, USERS_PER_PAGE);
		return repo.findAll(pageable);
	}
	public boolean isEmailUnique(Integer id, String email) {
		User userByEmail = repo.getUserByEmail(email);
		
		if(userByEmail == null) return true;
		
		boolean isCreatingNew = (id == null);
		
		if(isCreatingNew) {
			if(userByEmail != null) return false;
			
		} else {
			if(userByEmail.getId() != id) {
				return false;
			}
		}
		
		return true;
	}

	public User get(Integer id) throws UserNotFoundException {
		try {
			return repo.findById(id).get();
		}catch (NoSuchElementException ex) {
			throw new UserNotFoundException("Could not find any user with this ID");
		
		}
		
		
	}
	
	public void delete(Integer id) throws UserNotFoundException {
		Long countById = repo.countById(id);
		
		if(countById == null|| countById == 0) {
			throw new UserNotFoundException("Could not find any user with this ID");
		}
		
		repo.deleteById(id);
	}
	
	public void updateUserEnabledStatus(Integer id, boolean enabled) {
		repo.updateEnabledStatus(id, enabled);
		
	}

}
