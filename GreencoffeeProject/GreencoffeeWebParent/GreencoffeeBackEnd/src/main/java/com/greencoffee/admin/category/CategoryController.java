package com.greencoffee.admin.category;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.greencoffee.admin.FileUploadUtil;
import com.greencoffee.common.entity.Category;



@Controller
public class CategoryController {
    
	@Autowired
	private CategoryService service;
	
	@GetMapping("/categories")
	public String listAll(Model model) {
		List<Category> listCategories = service.listAll();
		model.addAttribute("listCategories", listCategories);		
		return "categories";
	}
	
	@GetMapping("/categories/new")
	public String newCategory(Model model) {
	List<Category> listCategories = service.listCategoriesUsedInForm();
		model.addAttribute("category", new Category());
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("pageTitle", "Create New Category");
		return "category_form";
	}
	
	@PostMapping("categories/save")
	public String saveCategory(Category category, @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
	   String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	   category.setImage(fileName);
	   Category savedCategory = service.save(category);
	   String uploadDir = "../category-images/" + savedCategory.getId();
	   FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		
	   return "redirect:/categories";
	}
}
