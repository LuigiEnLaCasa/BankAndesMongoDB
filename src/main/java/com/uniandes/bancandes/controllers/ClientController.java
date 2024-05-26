package com.uniandes.bancandes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.uniandes.bancandes.repository.ClientRepository;
import com.uniandes.bancandes.models.Client;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//ESTE CONTROLADOR CUMPLE CON LOS REQS
//CREAR USUARIO- CLIENTE


@Controller
public class ClientController{


    @Autowired
    private ClientRepository clientRepository;

    //get clients



    @GetMapping("/clients")
    public String clients(Model model){
        Collection<Client> clientCollection = clientRepository.findAll();
        model.addAttribute("clients", clientCollection);
        return "users";
    }

    @GetMapping("/clients/new/client")
    public String clientForm(Model model) {
        
        return "newClient";
    }


    @PostMapping("/clients/new/client/save")
    public String clientSave(@ModelAttribute Client client){
        clientRepository.save(
            new Client(
            client.getId(),
            client.getIDcard(),
            client.getTypeID(),
            client.getName(),
            client.getLogin(),
            client.getPassword(),
            client.getNationality(),
            client.getAddress(),
            client.getEmail(),
            client.getPhone(),
            client.getCountry(),
            client.getDepartment(),
            client.getAccounts()

        ));
        return "redirect:/users";

    }


    

    
}

