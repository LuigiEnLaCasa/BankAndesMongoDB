package com.uniandes.bancandes.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import com.uniandes.bancandes.models.PointService;
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
        model.addAttribute("newOffice", new Office());
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


        //Delete & add point Service

        @GetMapping("/point_services/delete/{id}/{officeId}")
        public String deletePointService(@PathVariable("id") Integer idPoint,@PathVariable("officeId") ObjectId idOffice) {
            officeRepository.deletePointServiceFromOffice(idOffice, idPoint);
            return "redirect:/offices";
        
    }


    @GetMapping("/pointServices/new")
    public String newPointService(Model model) {
        PointService pointService = new PointService();
        List<Office> offices = officeRepository.findAll();

        model.addAttribute("newPoint", pointService);
        model.addAttribute("offices", offices);

        return "newPointService";
    }

    @PostMapping("/pointServices/new/save")
    public String addPointService(
        @RequestParam("officeId") ObjectId officeId,
        @RequestParam("idPoint") Integer idPoint,
        @RequestParam("type") String type) 
        {
 
        PointService pointService = new PointService();
        pointService.setIdPoint(idPoint);
        pointService.setType(type);
        
        officeRepository.addPointServiceToOffice(officeId, idPoint, type);
        return "redirect:/offices";
    
}





}
    



