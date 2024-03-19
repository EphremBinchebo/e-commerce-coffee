package com.greencoffee.admin.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greencoffee.admin.user.UserService;

@RestController
public class ProducerRestController {
	
	@Autowired
	private ProducerService service;
	
	@PostMapping("/producers/check_email")
	public String checkDuplicateEmail(@Param("id") Integer id, @Param("email") String email) {
		return service.isEmailUnique(id, email) ? "OK" : "Duplicated";
		
	}

}

