package com.greencoffee.admin;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greencoffee.admin.user.UserNotFoundException;
import com.greencoffee.admin.user.UserService;
import com.greencoffee.common.entity.Role;
import com.greencoffee.common.entity.User;



@Controller
public class UserController {
  
  @Autowired
  private UserService service;
  
  @GetMapping("/users")
  public String listAll(Model model) {
	  List<User> listUsers = service.listAll();
	 model.addAttribute("listUsers", listUsers);
	  return "user";
	  
  }
  
  @GetMapping("/users/page/{pageNum}")
  public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model) {
	  Page<User> page = service.listByPage(pageNum);
	  List<User> listUsers = page.getContent();
	  
	  long startCount = (pageNum - 1) * UserService.USERS_PER_PAGE + 1;
	  long endCount = startCount + UserService.USERS_PER_PAGE - 1;
	  
	  if(endCount > page.getTotalElements()) {
		  endCount = page.getTotalElements();
	  }
	  
	  
	  model.addAttribute("startCount", startCount);
	  model.addAttribute("endCount", endCount);
	  model.addAttribute("totalItems", page.getTotalElements());
	  model.addAttribute("listUsers", listUsers);
	  
	  return "user";
	  
  }
  
  
  @GetMapping("/users/new")
  public String newUser(Model model) {
	  
	  List<Role> listRoles = service.listRoles();
	  User user = new User();
	  user.setEnabled(true);
	  model.addAttribute("user", user);
	  model.addAttribute("listRoles", listRoles);
	  model.addAttribute("pageTitle","Create New User");
	  return "user_form";
  }
  
  @PostMapping("/users/save")
  public String saveUser(User user, RedirectAttributes redirectAttribute,
		  @RequestParam("image") MultipartFile multipartFile) throws IOException {
	  
	    if(!multipartFile.isEmpty()) {
	    	System.out.println(multipartFile.getOriginalFilename()); String fileName =
	        StringUtils.cleanPath(multipartFile.getOriginalFilename());
	    	user.setPhotos(fileName);
	    	User savedUser = service.save(user);
	        String uploadDir = "user-photos/" + savedUser.getId();
	    	
	        FileUploadUtil.cleanDir(uploadDir);
	        
	        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	    }
		
			  
		redirectAttribute.addFlashAttribute("message", "The user has been saved successfully.");
	 
	  return "redirect:/users";
	  
  }
  
  @GetMapping("/users/edit/{id}")
  public String editUser(@PathVariable(name="id") Integer id,
		  Model model,
		  RedirectAttributes redirectAttribute) {
	try {
		  User user = service.get(id);
		  List<Role> listRoles = service.listRoles();
		  model.addAttribute("user", user);		  
		  model.addAttribute("pageTitle","Edit User(ID: " + id + ")");
		  model.addAttribute("listRoles", listRoles);
		  return "user_form";
	}catch(UserNotFoundException ex){
		 redirectAttribute.addFlashAttribute("message", ex.getMessage());
		 return "redirect:/users";
	}
  }
   
  
  @GetMapping("/users/delete/{id}")
  public String deleteUser(@PathVariable(name="id") Integer id,
		  Model model,
		  RedirectAttributes redirectAttribute) {
	  try {
		  service.delete(id);

		  redirectAttribute.addFlashAttribute("message", "The User ID" + id + " has been deleted succesfully");
		  
		  
	}catch(UserNotFoundException ex){
		 redirectAttribute.addFlashAttribute("message", ex.getMessage());
		
	}
	  
	  return "redirect:/users";
  }
  
   @GetMapping("/users/{id}/enabled/{status}")
   public String updateUserEnabledStatus(@PathVariable("id") Integer id,
		   @PathVariable("status") boolean enabled, RedirectAttributes redirectAttributes) {
	   
	   service.updateUserEnabledStatus(id, enabled);
	   
	   String status = enabled ? "enabled" : "disabled";
	   String message = "The User ID " + id + " has been " + status;
	   redirectAttributes.addFlashAttribute("message", message);
	   return "redirect:/users";
	   
   }
}

  

