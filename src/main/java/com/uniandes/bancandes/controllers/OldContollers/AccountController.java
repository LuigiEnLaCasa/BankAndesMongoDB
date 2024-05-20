// package com.uniandes.bancandes.controllers;
// import java.util.Collection;

// import com.uniandes.bancandes.models.LogAccount;
// import com.uniandes.bancandes.repository.LogAccountRepository;
// import com.uniandes.bancandes.repository.OfficeRepository;
// import jakarta.transaction.Transactional;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;
// import java.util.Date;
// import java.text.SimpleDateFormat;
// import java.util.Calendar;
// import java.util.Date;
// import com.uniandes.bancandes.models.Account;
// import com.uniandes.bancandes.repository.AccountRepository;
// import com.uniandes.bancandes.repository.CustomerRepository;

// @Controller
// public class AccountController {

//     @Autowired
//     private  AccountRepository accountRepository;
//     @Autowired
//     private CustomerRepository customerRepository;
//     @Autowired
//     private OfficeRepository officeRepository;
//     @Autowired
//     private LogAccountRepository logAccountRepository;

//     @GetMapping("/accounts")
//     public String accounts(Model model){
//         Collection<Account> accountsCollection = accountRepository.getAccounts();
//         model.addAttribute("accounts", accountsCollection);
//         return "accounts";
//     }
//     @GetMapping("/accounts/new")
//     public String accountForm(Model model){
//         model.addAttribute("newAccount", new Account());
//         model.addAttribute("customers", customerRepository.getCustomers());
//         model.addAttribute("offices", officeRepository.findAllOffices());
//         return "newAccount";
//     }

//     @PostMapping("/accounts/new/save")
//     public String accountEditForm(@ModelAttribute Account account){
//         System.out.println(account.getStatus());
//         System.out.println(account.getOffice());
//         System.out.println(account.getCustomer());
//         accountRepository.insertAccount(account.getBalance(),account.getStatus(),account.getCustomer(), account.getOffice().getId());
//         return "redirect:/accounts";
//     }

//     @GetMapping("/accounts/{id}/edit")
//     public String accountEditForm(@PathVariable("id") Integer id, Model model) {
//         Account account = accountRepository.findAccountById(id);
//         System.out.println(customerRepository.getCustomers());
//         if (account != null) {
//             model.addAttribute("account", account);
//             model.addAttribute("customers", customerRepository.getCustomers());
//             return "editAccount";
//         } else {
//             return "redirect:/accounts";
//         }
//     }

//     @PostMapping("/accounts/{id}/edit/save")
//     public String accountEditSave(@PathVariable("id") Integer id, @ModelAttribute Account account) {
//         accountRepository.updateAccountStatus(((long) id), account.getStatus());
//         return "redirect:/accounts";
//     }


//     @GetMapping("/accounts/{id}/withdraw")
//     public String accountWithdrawMoney(@PathVariable("id") Integer id, Model model) {
//         Account account = accountRepository.findAccountById(id);
//         if (account != null) {
//             model.addAttribute("account", account);
//             return "opWithdraw";
//         } else {
//             return "redirect:/accounts";
//         }
//     }

//     @PostMapping("/accounts/{id}/withdraw/save")
//     @Transactional
//     public String accountWithdrawSave(@PathVariable("id") Integer id, @RequestParam Double value, @ModelAttribute Account account) {

//         Date currentDate = new Date();
//         System.out.println(account.getId());
//         logAccountRepository.insertLogAccount(value,"Retiro",currentDate,account.getId());
//         accountRepository.withdrawMoney(((long) id), value);
//         return "redirect:/accounts";
//     }

//     @GetMapping("/accounts/{id}/deposit")
//     public String accountDepositoney(@PathVariable("id") Integer id, Model model) {
//         Account account = accountRepository.findAccountById(id);
//         if (account != null) {
//             model.addAttribute("account", account);
//             return "opDeposit";
//         } else {
//             return "redirect:/accounts";
//         }
//     }

//     @PostMapping("/accounts/{id}/deposit/save")
//     @Transactional
//     public String accountDepositSave(@PathVariable("id") Integer id, @RequestParam Double value, @ModelAttribute Account account) {

//         Date currentDate = new Date();
//         System.out.println(account.getId());
//         logAccountRepository.insertLogAccount(value,"Consignacion",currentDate,account.getId());
//         accountRepository.depositMoney(((long) id), value);
//         return "redirect:/accounts";
//     }

//     @GetMapping("/accounts/{id}/statement")
//     public String accountStatement(@PathVariable("id") Integer id, Model model ) throws InterruptedException {
//         Account account = accountRepository.findAccountById(id);
//         Date currentDate = new Date();
//         Calendar calendar = Calendar.getInstance();
//         calendar.setTime(currentDate);
//         int year = calendar.get(Calendar.YEAR);
//         int month = calendar.get(Calendar.MONTH) + 1;
//         Collection<LogAccount> statements = logAccountRepository.consultarLogsPorCuentaYMesAnioSerializable(Long.valueOf(id),month,year);
//         Thread.sleep(30000);
//         if (account != null) {
//             model.addAttribute("account", account);
//             model.addAttribute("logs", statements);

//             return "bankStatement";
//         } else {
//             return "redirect:/accounts";
//         }
//     }

// }
