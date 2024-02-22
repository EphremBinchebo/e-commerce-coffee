package com.greencoffee.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greencoffee.admin.producer.ProducerService;
import com.greencoffee.admin.user.UserNotFoundException;

import com.greencoffee.common.entity.Producer;
import com.greencoffee.common.entity.Role;


@Controller
public class ProducerController {
  
  @Autowired
  private ProducerService service;
  
  @GetMapping("/producers")
  public String listAll(Model model) {
	  List<Producer> listProducers = service.listAll();
	 model.addAttribute("listProducers", listProducers);
	  return "producer";
	  
  }
  
  @GetMapping("/producers/new")
  public String newProducer(Model model) {
	  
	  List<Role> listRoles = service.listRoles();
	  Producer producer = new Producer();
	  producer.setEnabled(true);
	  model.addAttribute("producer", producer);
	  model.addAttribute("listRoles", listRoles);
	  model.addAttribute("pageTitle","Create New Producer");
	  return "producer_form";
  }
  
  @PostMapping("/producers/save")
  public String saveUser(Producer producer, RedirectAttributes redirectAttribute) {
	  System.out.println(producer);
	  service.save(producer);
	  
	  redirectAttribute.addFlashAttribute("message", "The user has been saved successfully.");
	  return "redirect:/producers";
	  
  }
  
  @GetMapping("/producers/edit/{id}")
  public String editProducer(@PathVariable(name="id") Integer id,
		  Model model,
		  RedirectAttributes redirectAttribute) {
	try {
		  Producer producer = service.get(id);
		  List<Role> listRoles = service.listRoles();
		  model.addAttribute("producer", producer);		  
		  model.addAttribute("pageTitle","Edit User(ID: " + id + ")");
		  model.addAttribute("listRoles", listRoles);
		  return "producer_form";
	}catch(UserNotFoundException ex){
		 redirectAttribute.addFlashAttribute("message", ex.getMessage());
		 return "redirect:/producers";
	}
  }
   
  
  @GetMapping("/producers/delete/{id}")
  public String deleteUser(@PathVariable(name="id") Integer id,
		  Model model,
		  RedirectAttributes redirectAttribute) {
	  try {
		  service.delete(id);

		  redirectAttribute.addFlashAttribute("message", "The User ID" + id + " has been deleted succesfully");
		  
		  
	}catch(UserNotFoundException ex){
		 redirectAttribute.addFlashAttribute("message", ex.getMessage());
		
	}
	  
	  return "redirect:/producers";
  }
}
