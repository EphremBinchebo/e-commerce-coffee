package com.greencoffee.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.greencoffee.common.entity.Role;
import com.greencoffee.common.entity.User;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE )
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userEphrem = new User("ephremgensa1@gmail.com", "eph2024", "Ephrem", "Binchebo");
		userEphrem.addRole(roleAdmin);
		User savedUser = repo.save(userEphrem);
		assertThat(savedUser.getId()).isGreaterThan(0);
		
	}
	
	@Test
	public void testCreateNewUserWithTwoRoles() {
		User userSelam = new User("selam@gmail.com", "sel2025", "Selam", "Kebede");
//		userSelam.addRole(new Role());
	    Role roleEditor = new Role(5);
	    Role roleAssistant = new Role(4);
	   
	    userSelam.addRole(roleEditor);
	    userSelam.addRole(roleAssistant);
	
	    User savedUser = repo.save(userSelam);
	
	    assertThat(savedUser.getId()).isGreaterThan(0);
	 }
	
	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));	
	}
	
	@Test
	public void testUpdateUserDetails() {
		User userNam = repo.findById(1).get();
		userNam.setEnabled(true);
		userNam.setEmail("jephremg@gmail.com");
		
		repo.save(userNam);
	}
	
	@Test
	public void testGetUserById() {
		User userNam = repo.findById(1).get();
		System.out.println(userNam);
	    assertThat(userNam).isNotNull();
	}
	
	
	@Test
	public void testUpdateUserRole() {
		User userEphrem = repo.findById(12).get();
		Role roleEditor = new Role(4);
		Role roleSalesperson = new Role(7);
		
		userEphrem.getRoles().remove(roleEditor);
		userEphrem.addRole(roleSalesperson);
		
		repo.save(userEphrem);
		
	}
	
	@Test
	public void testDelateUser() {
		Integer userId = 1;
		repo.deleteById(userId);
	}
	
	@Test
	public void testGetUserByEmail() {
		String email = "selam@gmail.com";
		User user = repo.getUserByEmail(email);
		
		assertThat(user).isNotNull();
		
	}
	
}
