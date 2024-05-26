package com.uniandes.bancandes.controllers;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.uniandes.bancandes.models.Account;
import com.uniandes.bancandes.models.Client;
import com.uniandes.bancandes.models.Office;
import com.uniandes.bancandes.repository.ClientRepository;
import com.uniandes.bancandes.repository.EmployeeRepository;
import com.uniandes.bancandes.repository.OfficeRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.bson.types.ObjectId;


@Controller
public class OfficeController {

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    //mostrar las Oficinas
    @GetMapping("/offices")
    public String offices(Model model){
        Collection<Office> officesCollection = officeRepository.findAll();
        
        model.addAttribute("offices", officesCollection);
        return "offices";
    }


    @GetMapping("/offices/new")
    public String accountForm(Model model) {
        model.addAttribute("newOffice", new Account());
        model.addAttribute("employees", employeeRepository.findAll()); 

        return "newOffice";
    }

    @PostMapping("/offices/new/save")
    public String saveOffice(Office office) {
        officeRepository.save(
            new Office(office.getId(),office.getName(), office.getAddress(),office.getPoint_services()));
        return "redirect:/offices";
    }



    @GetMapping("/offices/{id}/point_services")
    public String getPointServices(@PathVariable("id") ObjectId id,Model model) throws InterruptedException {
        
        Optional<Office> offices = officeRepository.findById(id);
        
        if (offices.isPresent()) {
            Office office = offices.get();
            model.addAttribute("office", office);
            model.addAttribute("pointServices", office.getPoint_services());
            return "pointServices";
        } else {
            return "redirect:/";
        }

        
    }
    
    
}
