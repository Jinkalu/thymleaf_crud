package com.emp.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.emp.main.model.Employee2;
import com.emp.main.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService es;

	@GetMapping("/")
	public ModelAndView  getAll() {
		
		return findPagination(1,"firstname","acs");
		
	}
	
	@GetMapping("/newform")
	public ModelAndView empReg() {
		ModelAndView mv=new ModelAndView();
		mv.addObject("head","Register Employee");
		mv.addObject("e",new Employee2());
		mv.setViewName("regform");
		
		return mv;

	}
	@PostMapping("/saveemp")
	public ModelAndView saveEmp(@ModelAttribute("e") Employee2 e) {
		
		es.saveEmp(e);
		
		return new ModelAndView("redirect:/");
		
	}
	@GetMapping("/gete/{id}")
	public ModelAndView getById(@PathVariable long id){
		ModelAndView mv=new ModelAndView();
		mv.addObject("head","Update Employee");
		mv.addObject("e",es.getById(id));
		mv.setViewName("regform");
		return mv;
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable long id){

		es.delete(id);
		return new ModelAndView("redirect:/");
	}

	//pagination and sorting
	@GetMapping("/page/{pageno}")
	public ModelAndView findPagination(@PathVariable int pageno,
									   @RequestParam("sfeild") String sfeild,
									   @RequestParam("sdir")String sdir) {
		int pageSize=4;
		Page<Employee2> page=es.findPageIn(pageno, pageSize,sfeild,sdir);
		List<Employee2> list=page.getContent();
		ModelAndView mv=new ModelAndView();
		//pagination
		mv.addObject("currentPage",pageno);
		mv.addObject("totalpages",page.getTotalPages());// no of pages
		mv.addObject("totalitems",page.getTotalElements());//no of rows

		//sorting
		mv.addObject("sfeild",sfeild);
		mv.addObject("sdir",sdir);
		mv.addObject("revs",sdir.equals("asc")?"desc":"asc");

		mv.addObject("el",list);
		mv.setViewName("index");
		return mv;
	}

}
