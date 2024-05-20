package com.uniandes.bancandes.controllers;

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


@Controller
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    //mostrar las cuentas
        @GetMapping("/accounts")
    public String clients(Model model){
        Collection<Account> accountCollection = accountRepository.findAll();
        model.addAttribute("accounts", accountCollection);
        return "accounts";
    }
    


    //agregar una cuenta

    @GetMapping("/accounts/new")
    public String employeeForm(Model model) {
        model.addAttribute("newAccount", new Account());

        System.out.println("new account");
        return "account";
    
    }


        @PostMapping("/users/new/account/save")
    public String employeeSave(@ModelAttribute Account account) {

        accountRepository.save(new Account(
            account.getId(),
            account.getBalance(),
            account.getStatus(),
            account.getType(),
            account.getLog_accounts()
        ));

    
       return "redirect:/accounts";
    }


    //extracto ARREGLAR
    @GetMapping("/accounts/{id}/statement")
    public String accountStatement(@PathVariable("id") ObjectId id, Model model ) throws InterruptedException {

        Account account = accountRepository.findAccountById(id);
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        Collection<LogAccount> statements = accountRepository.consultarLogsPorCuentaYMesAnio(id,month,year);
  
        if (account != null) {
            
            model.addAttribute("account", account);
            model.addAttribute("logs", statements);

            return "bankStatement";
        } else {
            return "redirect:/accounts";
        }
    }
}
