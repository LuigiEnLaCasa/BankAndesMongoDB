// package com.uniandes.bancandes.controllers;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

// import com.uniandes.bancandes.models.Office;
// import com.uniandes.bancandes.repository.EmployeeRepository;
// import com.uniandes.bancandes.repository.OfficeRepository;

// @Controller()
// @RequestMapping("/offices")
// public class OfficeController {

//     @Autowired
//     private OfficeRepository officeRepository;

//     @Autowired
//     private EmployeeRepository employeeRepository;

//     @GetMapping("")
//     public String getOffices(Model model) {
//         List<Office> offices = new ArrayList<Office>(officeRepository.findAllOffices());
//         model.addAttribute("offices", offices);
//         return "offices";
//     }

//     @GetMapping("/new")
//     public String newOffice(Model model) {
//         model.addAttribute("newOffice", new Office());
//         model.addAttribute("employees", employeeRepository.findEmployeesWithoutOffice());
//         return "newOffice";
//     }

//     @PostMapping("/new/save")
//     public String saveOffice(Office office) {
//         officeRepository.saveOffice(office.getName(), office.getAddress(), office.getEmployee().getIdcard());
//         return "redirect:/offices";
//     }

// }
