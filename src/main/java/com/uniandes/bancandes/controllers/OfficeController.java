package com.uniandes.bancandes.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.uniandes.bancandes.models.Account;
import com.uniandes.bancandes.models.Office;
import com.uniandes.bancandes.repository.OfficeRepository;

@Controller
public class OfficeController {

    @Autowired
    private OfficeRepository officeRepository;

    //mostrar las Oficinas
    @GetMapping("/offices")
    public String offices(Model model){
        Collection<Office> officesCollection = officeRepository.findAll();
        System.out.println("Offices: " + officesCollection);
        model.addAttribute("offices", officesCollection);
        return "offices";
    }



    
}
