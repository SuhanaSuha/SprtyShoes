package com.controller;

import java.math.BigDecimal;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.*;
import com.service.*;

@RestController
public class AdminController {
	
	@Autowired
    private AdminService adminService; 
	
	@Autowired
	private CategoryService categoryService; 

	@Autowired
	private ProductService productService; 

	@Autowired
	private PurchaseService purchaseService; 

	@Autowired
	private PurchaseItemService purchaseItemService; 
	
	@Autowired
	private UserService userService; 
	
	
	  @RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
	    public String login(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  //map.addAttribute("pageTitle", "ADMIN LOGIN");
	        return "login"; 
	    }
	  
	  @RequestMapping(value = "/adminloginaction", method = RequestMethod.POST)
	    public String loginAction(ModelMap map, javax.servlet.http.HttpServletRequest request, 
	    		 @RequestParam(value="admin_id", required=true) String adminId,
	    		 @RequestParam(value="admin_pwd", required=true) String adminPwd) 
	    {
		  
		  Admin admin = adminService.authenticate(adminId, adminPwd);
		  if (admin == null) { 
			  map.addAttribute("error", "Admin login failed");
			  return "admin/login";
		  }
		  // store admin id in session
		  HttpSession session = request.getSession();
		  session.setAttribute("admin_id", admin.getID());
		  
	        return "admin/dashboard"; 
	    }	  
	    
	  @RequestMapping(value = "/admindashboard", method = RequestMethod.GET)
	    public String dashboard(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		   
		  map.addAttribute("pageTitle", "ADMIN DASHBOARD");
	        return "admin/dashboard"; 
	    }
	  
	   
	  @RequestMapping(value = "/adminchangepassword", method = RequestMethod.GET)
	    public String changePwd(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		  
		  Admin admin = adminService.getAdminById((Long) session.getAttribute("admin_id"));
		  
		  map.addAttribute("admin", admin);
		  map.addAttribute("pageTitle", "ADMIN CHANGE PASSWORD");
	        return "admin/change-password"; 
	    }

	  
	  @RequestMapping(value = "/adminchangepwdaction", method = RequestMethod.POST)
	    public String updatePassword(ModelMap map,  @RequestParam(value="pwd", required=true) String pwd,
	    		 @RequestParam(value="pwd2", required=true) String pwd2, 
	    		 javax.servlet.http.HttpServletRequest request)
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		  
		  
		  if (pwd == null || pwd2 == null || pwd.equals("") || pwd2.equals("")) {
			  map.addAttribute("error", "Error , Incomplete passwords submitted.");
			  return "admin/change-password";
		  }
		  if (!pwd.equals(pwd2)) {
			  map.addAttribute("error", "Error , Passwords do not match.");
			  return "admin/change-password";
		  }
		  Admin admin = adminService.getAdminById((Long) session.getAttribute("admin_id")); 
		  admin.setAdminPwd(pwd);
		  adminService.updatePwd(admin);
		  
	        return "admin/dashboard";  
	    }		  

	  
	  @RequestMapping(value = "/adminproducts", method = RequestMethod.GET)
	    public String products(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		  List<Product> list = productService.getAllProducts();

		  // use a MAP to link category names to each product in list  
		  HashMap<Long, String> mapCats = new HashMap<Long, String>();
		  
		  for(Product product: list) {
			  Category category = categoryService.getCategoryById(product.getCategoryId());
			  if (category != null)
				  mapCats.put(product.getID(), category.getName());
		  }
		  map.addAttribute("list", list);
		  map.addAttribute("mapCats", mapCats);

		  map.addAttribute("pageTitle", "ADMIN SETUP PRODUCTS");
	        return "admin/products"; 
	    }
	  
	  @RequestMapping(value = "/admincategories", method = RequestMethod.GET)
	    public String categories(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  } 
		  
		  List<Category> list = categoryService.getAllCategories();
		  map.addAttribute("list", list);
		  map.addAttribute("pageTitle", "ADMIN SETUP PRODUCT CATEGORIES");
	        return "admin/categories"; 
	    }
	  
	  
	  @RequestMapping(value = "/adminmembers", method = RequestMethod.GET)
	    public String members(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		  List<User> list = userService.getAllUsers();
		  
		  map.addAttribute("list", list);
		  map.addAttribute("pageTitle", "ADMIN BROWSE MEMBERS");
	        return "admin/members"; 
	    }
	  @RequestMapping(value = "/adminpurchases", method = RequestMethod.GET)
	    public String purchases(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		  
		  List<Purchase> list = purchaseService.getAllItems();
		  
		  BigDecimal total = new BigDecimal(0.0);
		  
		  for(Purchase purchase: list) {
			  total = total.add(purchase.getTotal());
		  }
		  
		  // use MAPs to mape users to each purchase and item names to each purchase item row
		  HashMap<Long, String> mapItems = new HashMap<Long, String>();
		  HashMap<Long, String> mapUsers = new HashMap<Long, String>();
		  
		  for(Purchase purchase: list) {
			  total = total.add(purchase.getTotal());
			  User user = userService.getUserById(purchase.getUserId());
			  if (user != null)
				  mapUsers.put(purchase.getID(), user.getFname() + " " + user.getLname());
			  
			  List<PurchaseItem> itemList = purchaseItemService.getAllItemsByPurchaseId(purchase.getID());
			  StringBuilder sb = new StringBuilder(""); 
			  for(PurchaseItem item: itemList) {
				  Product product = productService.getProductById(item.getProductId());
				  if (product != null)
					  sb.append(product.getName() + ", " + 
						  	item.getQty() + " units @" + item.getRate() + " = " 
						  	+ item.getPrice() + "<br>");
			  }
			  mapItems.put(purchase.getID(), sb.toString());
		  }		  
		  map.addAttribute("totalAmount", total); 
		  map.addAttribute("list", list);
		  map.addAttribute("mapItems", mapItems);
		  map.addAttribute("mapUsers", mapUsers);
		  map.addAttribute("pageTitle", "ADMIN PURCHASES REPORT");
	        return "admin/purchases"; 
	    }	  

	  @RequestMapping(value = "/admindeletecat",  method = RequestMethod.GET)
	    public String deleteCategory(ModelMap map,  @RequestParam(value="id", required=true) String id,
	    		javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		  long idValue = Long.parseLong(id);
		  Category category = new Category();
		  if (idValue > 0) {
			  categoryService.deleteCategory(idValue);
		  }
		  return "redirect:admincategories";
	    }	
	  
	  
	  @RequestMapping(value = "/admineditcat",  method = RequestMethod.GET)
	    public String editCategory(ModelMap map,  @RequestParam(value="id", required=true) String id,
	    		javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		  long idValue = Long.parseLong(id);
		  Category category = new Category();
		  if (idValue > 0) {
			  category = categoryService.getCategoryById(idValue);
		  } else {
			  category.setID(0);
		  }
		  map.addAttribute("category", category);
		  map.addAttribute("pageTitle", "ADMIN EDIT PRODUCT CATEGORY");
	        return "admin/edit-category"; 
	    }		  

	  
	  @RequestMapping(value = "/admineditcataction", method = RequestMethod.POST)
	    public String updateCategory(ModelMap map,  @RequestParam(value="id", required=true) String id,
	    		 @RequestParam(value="name", required=true) String name, 
	    		 javax.servlet.http.HttpServletRequest request)
	    {
		  long idValue = Long.parseLong(id); 
		  
		  if (name == null || name.equals("") ) { 
			  map.addAttribute("error", "Error, A category name must be specified");
			  return "redirect:admineditcat?id="+id;
		  }
		  	Category category = new Category();
		  	category.setID(idValue); 
		  	category.setName(name);
		  	
		  	categoryService.updateCategory(category); 
		  	
	        return "redirect:admincategories";  
	    }
	  
	  @RequestMapping(value = "/admindeleteproduct",  method = RequestMethod.GET)
	    public String deleteProduct(ModelMap map,  @RequestParam(value="id", required=true) String id,
	    		javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		  long idValue = Long.parseLong(id);
		  Product product = new Product();
		  if (idValue > 0) {
			  productService.deleteProduct(idValue);
		  }
		  return "redirect:adminproducts";
	    }	
	  

	  
	  @RequestMapping(value = "/admineditproduct",  method = RequestMethod.GET)
	    public String editProduct(ModelMap map,  @RequestParam(value="id", required=true) String id,
	    		javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		  
		  long idValue = Long.parseLong(id);
		  Product product = new Product();
		  if (idValue > 0) {
			  product = productService.getProductById(idValue);
		  } else {
			  product.setID(0);
			  product.setCategoryId(0);
		  }
		  String dropDown = categoryService.getCategoriesDropDown(product.getCategoryId());
		  map.addAttribute("product", product);
		  map.addAttribute("catDropdown", dropDown);
		  map.addAttribute("pageTitle", "ADMIN EDIT PRODUCT");
	        return "admin/edit-product"; 
	    }		  
	  @RequestMapping(value = "/admineditproductaction", method = RequestMethod.POST)
	    public String updateProduct(ModelMap map, javax.servlet.http.HttpServletRequest request,
	    		 @RequestParam(value="id", required=true) String id,
	    		 @RequestParam(value="name", required=true) String name,
	    		 @RequestParam(value="price", required=true) String price,
	    		 @RequestParam(value="category", required=true) String categoryId) 
	    {
		  long idValue = Long.parseLong(id); 
		  long categoryIdValue = Long.parseLong(categoryId);
		  BigDecimal priceValue = new BigDecimal(0.0);
		  
		  if (name == null || name.equals("") ) { 
			  map.addAttribute("error", "Error, A product name must be specified");
			  return "redirect:admineditproduct?id="+id;
		  }
		  try {
			  priceValue = new BigDecimal(price);
			  
		  } catch (Exception ex) {
			  map.addAttribute("error", "Error, Price is invalid");
			  return "redirect:admineditproduct?id="+id;
		  }
		  
		  	Product product = new Product();
		  	product.setID(idValue); 
		  	product.setCategoryId(Long.parseLong(categoryId));
		  	product.setName(name);
		  	product.setPrice(priceValue);
		  	
		  	productService.updateProduct(product); 
		  	
	        return "redirect:adminproducts"; 
	    }	
	   
	  
	  
	  @RequestMapping(value = "/adminlogout", method = RequestMethod.GET)
	    public String logout(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  	HttpSession session = request.getSession();
		  	session.invalidate();
		  	
	        return "admin/login"; 
	    }
}

