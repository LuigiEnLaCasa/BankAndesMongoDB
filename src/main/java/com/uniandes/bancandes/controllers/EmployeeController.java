package com.uniandes.bancandes.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.uniandes.bancandes.models.Client;
import com.uniandes.bancandes.models.Employee;
import com.uniandes.bancandes.repository.ClientRepository;
import com.uniandes.bancandes.repository.EmployeeRepository;

import org.springframework.ui.Model;

//ESTE CONTROLADOR CUMPLE CON LOS REQS
//CREAR USUARIO- EMPLEADO


@Controller
public class EmployeeController{

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ClientRepository customerRepository;


    // @GetMapping("/employees")
    // public String employees(Model model) {
    //     Collection<Employee> employeeCollection = employeeRepository.findAll();
    //     Collection<Client> customerCollection = customerRepository.findAll();
    //     model.addAttribute("employees", employeeCollection);
    //     model.addAttribute("customers", customerCollection);
    //     return "employees";

    // }


    @GetMapping("/users/new/employee")
    public String employeeForm(Model model) {

        System.out.println("new employee");
        return "users";
    
    }

    @PostMapping("/users/new/employee/save")
    public String employeeSave(@ModelAttribute Employee employee) {

        employeeRepository.save(new Employee(
            employee.getId(),
            employee.getIdcard(),
            employee.getTypeid(),
            employee.getName(),
            employee.getLogin(),
            employee.getPassword(),
            employee.getNationality(),
            employee.getAddress(),
            employee.getEmail(),
            employee.getPhone(),
            employee.getCountry(),
            employee.getDepartment(),
            employee.getType(),
            employee.getSalary(),
            employee.getOffices()
        ));

    
       return "redirect:/users";
    }
    
}

