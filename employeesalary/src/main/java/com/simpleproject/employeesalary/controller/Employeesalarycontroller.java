package com.simpleproject.employeesalary.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.simpleproject.employeesalary.dao.EmployeesalaryDAO;
import com.simpleproject.employeesalary.model.Employeesalary;



@Controller
public class Employeesalarycontroller {
	
	@Autowired
	private EmployeesalaryDAO employeesalaryDao;
	
	@RequestMapping(value = "/entry", method=RequestMethod.GET)
	public String newResgistration(ModelMap model) {
		
		Employeesalary employeesalary = new Employeesalary();
		model.addAttribute("employeesalary", employeesalary);
		return "entry";
	}
	

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveRegistration(@Valid Employeesalary employeesalary,BindingResult result, ModelMap model,RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			return "entry";
			
		}
		
		employeesalaryDao.save(employeesalary);
		  
		return "redirect:/viewemployeesalarys/1";
	}
	
	@RequestMapping("/viewemployeesalarys")  
    public ModelAndView viewemployeesalarys(){  
        List<Employeesalary> list=employeesalaryDao.getAllEmployeesalarys();
        return new ModelAndView("viewemployeesalarys","list",list);  
    } 
	
	@RequestMapping(value="/viewemployeesalarys/{pageid}")
	public ModelAndView edit(@PathVariable int pageid) {
		
		int total=2;
		
		
		if(pageid==1) {
			
		}else {
			pageid=(pageid-1)*total+1;
		}
		List<Employeesalary> list=employeesalaryDao.getEmployeesalarysByPage(pageid, total);
		return new ModelAndView("viewemployeesalarys", "list", list);
		
	}
	
	@RequestMapping(value="/editemployeesalary/{id}")  
    public String edit(@PathVariable int id,ModelMap model){  
       Employeesalary employeesalary=employeesalaryDao.getEmployeesalaryById(id);  
       model.addAttribute("employeesalary", employeesalary);
		return "editemployeesalary";
        
        
    }
	
	@RequestMapping(value="/editsave", method=RequestMethod.POST)
	public ModelAndView editsave(@ModelAttribute("employeesalary") Employeesalary p) {
		System.out.println("id is"+p.getId());
		employeesalaryDao.update(p);
		return new ModelAndView("redirect:/viewemployeesalarys/1");
	}
	
	@RequestMapping(value="/deleteemployeesalary/{id}", method=RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id) {
		
		employeesalaryDao.delete(id);
		
		return new ModelAndView("redirect:/viewemployeesalarys/1");
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public ModelAndView delete() {
		
		employeesalaryDao.delete();
		
		return new ModelAndView("redirect:/viewemployeesalarys/1");
	}
	
	@ModelAttribute("pageCount")
	public List<String> initializePageCount(){
		
		int total = 2;
		List<String> pageCount = new ArrayList<String>();
		int count = employeesalaryDao.count();
		int result = ((count/total)+ (count%total));
		for(int k=0;k<result;k++) {
			pageCount.add(new Integer(k).toString());
		}
		
		return pageCount;
	}

}
