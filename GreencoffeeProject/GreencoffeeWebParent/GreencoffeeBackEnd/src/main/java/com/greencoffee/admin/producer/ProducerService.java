package com.greencoffee.admin.producer;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greencoffee.admin.user.RoleRepository;
import com.greencoffee.admin.user.UserNotFoundException;

import com.greencoffee.common.entity.Producer;
import com.greencoffee.common.entity.Role;



@Service
public class ProducerService {
	
	@Autowired
	private ProducerRepository repo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	public List<Producer> listAll(){
		return (List<Producer>) repo.findAll();
	}
	   
	public List<Role> listRoles(){
		return (List<Role>) roleRepo.findAll();
	}

	public void save(Producer producer) {
		boolean isUpdatingProducer = (producer.getId() != null);
		
		if(isUpdatingProducer) {
			Producer existingProducer = repo.findById(producer.getId()).get();
		    
			repo.save(existingProducer);
			
		
		
		}
		repo.save(producer);
	}
	
	public boolean isEmailUnique(Integer id, String email) {
		Producer producerByEmail = repo.getProducerByEmail(email);
		
		if(producerByEmail == null) return true;
		
		boolean isCreatingNew = (id == null);
		
		if(isCreatingNew) {
			if(producerByEmail != null) return false;
			
		} else {
			if(producerByEmail.getId() != id) {
				return false;
			}
		}
		
		return true;
	}

	public Producer get(Integer id) throws UserNotFoundException {
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

}