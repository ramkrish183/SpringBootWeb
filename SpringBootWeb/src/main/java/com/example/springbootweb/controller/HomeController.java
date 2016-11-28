package com.example.springbootweb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springbootweb.bean.UserForm;
import com.example.springbootweb.service.UserService;

@Controller
public class HomeController {

	private UserService userService;
	@Autowired
	public HomeController(UserService userService) {
		this.userService = userService;
	}

	

	@RequestMapping("/hello")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		System.out.println("controller inside greeting mapping");
		return "index";
	}

	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String hello(Model model) {
		model.addAttribute("user", new UserForm());
		return "pages/userregistration";
	}

	@RequestMapping(value="/showUser",method=RequestMethod.POST)
	public String showUser(@ModelAttribute(value="user") UserForm userForm, Model model) throws Exception{
		System.out.println("controller inside greeting mapping");
	/*	userForm.setUserId("1");
		userForm.setDepartment("Physics");
		userForm.setUserName("Rameshkrishnan ");*/
		/*if(userForm!=null){
			return "error";
		}*/
		model.addAttribute("userId", userForm.getUserId());
		model.addAttribute("userName", userForm.getUserName());
		model.addAttribute("department", userForm.getDepartment());
		List<UserForm> listUserForm =new ArrayList<UserForm>();
		if(userService.getListUserForm()!=null){
			userService.getListUserForm().add(userForm);
			System.out.println("if loop data here");
		}
		else{
			listUserForm.add(userForm);
			userService.setListUserForm(listUserForm);
			System.out.println("updating data here");
		}
		model.addAttribute("userList",userService.getListUserForm());
		//listUserForm.add(userForm);
		//userService.setListUserForm(listUserForm);
		return "pages/result";
	}
	
	/*// Total control - setup a model and return the view name yourself. Or consider
	  // subclassing ExceptionHandlerExceptionResolver (see below).
	  @ExceptionHandler(Exception.class)
	  public ModelAndView handleError(HttpServletRequest req, Exception exception) {
	    //logger.error("Request: " + req.getRequestURL() + " raised " + exception);

	    ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", exception);
	    mav.addObject("url", req.getRequestURL());
	    mav.setViewName("error");
	    
	    return mav;
	  }*/
}
