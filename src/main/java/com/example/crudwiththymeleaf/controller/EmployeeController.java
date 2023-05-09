package com.example.crudwiththymeleaf.controller;

import com.example.crudwiththymeleaf.model.Employee;
import com.example.crudwiththymeleaf.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    //display list of employee
    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "index";
    }

    /**
     * Show list information employee
     * @param model
     * @return view
     */

    @GetMapping("/showNewEmployeeForm")
    public String showEmployeeForm(Model model){
        //create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    /**
     * Save information employee
     * @param employee
     * @return chuyển hướng về trang chính
     */
    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee){
        //save employee into database
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    /**
     * Show information employee
     * @param id
     * @param model
     * @return view
     */
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable (value = "id") long id, Model model){
        //get employee from service
        Employee employee =  employeeService.getEmployeeById(id);
        //set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    /**
     * Delete employee by id
     * @param id
     * @param model
     * @return view HomePage
     */
    @GetMapping("/deleteEmployee/{id}")
    public String showFormDelete(@PathVariable (value = "id") long id, Model model){
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }
}
