// package com.uniandes.bancandes.controllers;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

// import com.uniandes.bancandes.models.Office;
// // import com.uniandes.bancandes.models.PointService;
// import com.uniandes.bancandes.repository.OfficeRepository;
// import com.uniandes.bancandes.repository.PointServiceRepository;

// @Controller
// @RequestMapping("/pointServices")
// public class PointServiceController {

//     @Autowired
//     private PointServiceRepository pointServiceRepository;

//     @Autowired
//     private OfficeRepository officeRepository;

//     @GetMapping("")
//     public String getPointsService(Model model) {
//         List<PointService> pointServices = new ArrayList<PointService>(pointServiceRepository.findAllPointServices());
//         model.addAttribute("pointServices", pointServices);
//         return "pointServices";
//     }

//     @GetMapping("/new")
//     public String newPointService(Model model) {
//         PointService pointService = new PointService();
//         List<Office> offices = new ArrayList<Office>(officeRepository.findAllOffices());

//         model.addAttribute("pointService", pointService);
//         model.addAttribute("offices", offices);

//         return "newPointService";
//     }

//     @PostMapping("/new/save")
//     public String savePointService(PointService pointService) {

//         pointServiceRepository.savePointService(pointService.getType(), pointService.getAddress(),
//                 pointService.getOffice().getId());
//         return "redirect:/pointServices";
//     }

//     @GetMapping("/delete/{id}")
//     public String getMethodName(@PathVariable("id") int id) {
//         pointServiceRepository.deletePointService(id);
//         return "redirect:/pointServices";
//     }

// }
