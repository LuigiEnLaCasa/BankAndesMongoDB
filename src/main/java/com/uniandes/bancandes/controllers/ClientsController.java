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
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ClientsController{


    @Autowired
    private ClientRepository clientRepository;

    //get clients
    @PostMapping("/clients")
    public String clients(Model model){
        Collection<Client> clientCollection = clientRepository.findAll();
        model.addAttribute("clients", clientCollection);
        return "clients";
    }

    @GetMapping("/clients/new/client")
    public String clientForm(Model model) {
        System.out.println("new client");
        return "newClient";
    }


    @PostMapping("/clients/new/client/save")
    public String clientSave(@ModelAttribute Client client){
        clientRepository.save(
            new Client(
            client.getId(),
            client.getIdcard(),
            client.getTypeid(),
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

