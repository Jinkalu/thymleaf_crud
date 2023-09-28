package com.emp.main.controller;

import com.emp.main.model.Employee2;
import com.emp.main.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
	

	private final EmployeeService es;

	@GetMapping("/")
	public ModelAndView  getAll() {
		
		return findPagination(1,"firstname","acs");
		
	}
	
	@GetMapping("/new-form")
	public ModelAndView empReg() {
		ModelAndView mv=new ModelAndView();
		mv.addObject("head","Register Employee");
		mv.addObject("e",new Employee2());
		mv.setViewName("regform");
		
		return mv;

	}
	@PostMapping("/save-emp")
	public ModelAndView saveEmp(@ModelAttribute("e") Employee2 e) {
		
		es.saveEmp(e);
		
		return new ModelAndView("redirect:/");
		
	}
	@GetMapping("/get/{id}")
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
	@GetMapping("/page/{pageNo}")
	public ModelAndView findPagination(@PathVariable int pageNo,
									   @RequestParam("sortField") String sortField,
									   @RequestParam("sortDir")String sortDir) {
		int pageSize=1;
		Page<Employee2> page=es.findPageIn(pageNo, pageSize, sortField, sortDir);
		List<Employee2> list=page.getContent();
		ModelAndView mv=new ModelAndView();
		//pagination
		mv.addObject("currentPage", pageNo);
		mv.addObject("totalPages",page.getTotalPages());// no of pages
		mv.addObject("totalItems",page.getTotalElements());//no of rows

		//sorting
		mv.addObject("sortField", sortField);
		mv.addObject("sortDir", sortDir);
		mv.addObject("revs", sortDir.equals("asc")?"desc":"asc");

		mv.addObject("el",list);
		mv.setViewName("index");
		return mv;
	}

}
