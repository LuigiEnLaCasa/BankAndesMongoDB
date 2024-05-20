// package com.uniandes.bancandes.controllers;

// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;

// import com.uniandes.bancandes.models.Account;
// import com.uniandes.bancandes.models.Customer;
// import com.uniandes.bancandes.models.Employee;
// import com.uniandes.bancandes.models.Loan;
// import com.uniandes.bancandes.models.User;
// import com.uniandes.bancandes.repository.AccountRepository;
// import com.uniandes.bancandes.repository.CustomerRepository;
// import com.uniandes.bancandes.repository.EmployeeRepository;
// import com.uniandes.bancandes.repository.LoanRepository;
// import com.uniandes.bancandes.repository.UserRepository;

// @Controller
// public class UserController {

//     @Autowired
//     private UserRepository userRepository;
//     @Autowired
//     private CustomerRepository customerRepository;
//     @Autowired
//     private EmployeeRepository employeeRepository;
//     @Autowired
//     private AccountRepository accountRepository;
//     @Autowired
//     private LoanRepository loanRepository;

//     @GetMapping("/users")
//     public String users(Model model) {
//         Collection<User> usersCollection = userRepository.findAllUsers();
//         model.addAttribute("users", usersCollection);
//         return "users";
//     }

//     @GetMapping("/users/new/customer")
//     public String customerForm(Model model) {
//         model.addAttribute("newUser", new User());
//         model.addAttribute("isCustomer", true);
//         return "newUser";
//     }

//     @PostMapping("/users/new/customer/save")
//     public String customerSave(@ModelAttribute User user) {
//         userRepository.saveUser(user.getIdcard(), user.getTypeid(), user.getName(), user.getLogin(), user.getPassword(),
//                 user.getNationality(), user.getAddress(), user.getEmail(), user.getPhone(), user.getCountry(),
//                 user.getDepartment());
//         customerRepository.insertCustomer(user.getIdcard(), user.getIdcard());
//         return "redirect:/users";
//     }

//     @GetMapping("/users/new/employee")
//     public String employeeForm(Model model) {
//         model.addAttribute("newUser", new User());
//         model.addAttribute("newEmployee", new Employee());
//         model.addAttribute("isCustomer", false);
//         return "newUser";
//     }

//     @PostMapping("/users/new/employee/save")
//     public String employeeSave(@ModelAttribute User user, @ModelAttribute Employee employee) {
//         userRepository.saveUser(user.getIdcard(), user.getTypeid(), user.getName(), user.getLogin(), user.getPassword(),
//                 user.getNationality(), user.getAddress(), user.getEmail(), user.getPhone(), user.getCountry(),
//                 user.getDepartment());
//         // TODO: Posible error por el getType enumeration de Employee!!!!!!!!!!!!!!!
//         employeeRepository.addEmployee(user.getIdcard(), String.valueOf(employee.getType()), employee.getSalary(),
//                 user.getIdcard());
//         return "redirect:/users";
//     }

//     @GetMapping("/customers/{id}")
//     public String getCustomer(@PathVariable("id") String id, Model model) {
//         Customer customer = customerRepository.getCustomerByUserId(id);
//         User user = userRepository.findUserByIdCard(id);
//         List<Account> accounts = new ArrayList<Account>(accountRepository.findAccountsByCustomerIDCard(id));
//         List<Loan> loans = new ArrayList<Loan>(loanRepository.findLoansByCustomerIDCard(id));

//         model.addAttribute("customer", customer);
//         model.addAttribute("user", user);
//         model.addAttribute("accounts", accounts);
//         model.addAttribute("loans", loans);

//         return "customer";
//     }

// }
