package com.uniandes.bancandes.controllers;

import java.io.Console;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.uniandes.bancandes.models.Account;
import com.uniandes.bancandes.models.Employee;
import com.uniandes.bancandes.models.LogAccount;
import com.uniandes.bancandes.repository.AccountRepository;
import com.uniandes.bancandes.repository.ClientRepository;


@Controller
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientsRepository;

    //mostrar las cuentas
        @GetMapping("/accounts")
    public String clients(Model model){
        Collection<Account> accountCollection = accountRepository.findAll();
        model.addAttribute("accounts", accountCollection);
        return "accounts";
    }
    


    //Craete una cuenta

    @GetMapping("/accounts/new")
    public String accountForm(Model model) {
        model.addAttribute("newAccount", new Account());
        System.out.println("new account");
        return "newAccount";
    
    }


    @PostMapping("/users/new/account/save")
    public String accountSave(@ModelAttribute Account account) {

        accountRepository.save(new Account(
            account.getId(),
            account.getBalance(),
            account.getStatus(),
            account.getType(),
            account.getLog_accounts()
        ));

    
       return "redirect:/accounts";
    }


    //get extracto ARREGLAR
    @GetMapping("/accounts/{id}/statement")
    public String accountStatement(@PathVariable("id") ObjectId id, Model model) throws InterruptedException {

    System.out.println("Start of accountStatement method");
    
    Account account = accountRepository.findAccountById(id);
    System.out.println("Account retrieved: " + account);
    
    //Date currentDate = new Date();
    //Calendar calendar = Calendar.getInstance();
    //calendar.setTime(currentDate);
    //int year = calendar.get(Calendar.YEAR);
    //int month = calendar.get(Calendar.MONTH) + 1;
    Collection<LogAccount> statements = accountRepository.consultarLogsPorCuenta(id);


    if (account != null) {
        model.addAttribute("account", account);
        model.addAttribute("logs", statements);
        
        System.out.println("Account and logs added to model");
        return "bankStatement";
    } else {
        System.out.println("Account not found, redirecting to /accounts");
        return "redirect:/accounts";
    }
}
 




//UPDATE
    @GetMapping("/accounts/{id}/edit")
    public String accountEditForm(@PathVariable("id") ObjectId id, Model model) {
        
        Account account = accountRepository.findAccountById(id);
        if (account != null) {
            model.addAttribute("account", account);
            return "editAccount";
        } else {
            return "redirect:/accounts";
        }
    }

    @PostMapping("/accounts/{id}/edit/save")
    public String accountEditSave(@PathVariable("id") ObjectId id, @ModelAttribute Account account) {
        accountRepository.updateAccountStatus(id, account.getStatus());
        return "redirect:/accounts";
    }






}




//UPDATE
