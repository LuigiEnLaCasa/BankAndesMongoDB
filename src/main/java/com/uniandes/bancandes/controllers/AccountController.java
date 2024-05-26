package com.uniandes.bancandes.controllers;

import java.io.Console;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.Instant;

import com.uniandes.bancandes.models.Account;
import com.uniandes.bancandes.models.Employee;
import com.uniandes.bancandes.models.LogAccount;
import com.uniandes.bancandes.models.Office;
import com.uniandes.bancandes.repository.AccountRepository;
import com.uniandes.bancandes.repository.ClientRepository;
import com.uniandes.bancandes.repository.OfficeRepository;


@Controller
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private OfficeRepository officeRepository;


    private Integer superId =4 ;
    //mostrar las cuentas
        @GetMapping("/accounts")
    public String clients(Model model){
        Collection<Account> accountCollection = accountRepository.findAll();
        model.addAttribute("accounts", accountCollection);
        return "accounts";
    }
    


    //Create una cuenta

    @GetMapping("/accounts/new")
    public String accountForm(Model model) {
        model.addAttribute("newAccount", new Account());
        model.addAttribute("customers", clientRepository.findAll());
        model.addAttribute("offices", officeRepository.findAll());

        return "newAccount";
    
    }


    @PostMapping("/accounts/new/save")
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


    Optional<Account> accounts = accountRepository.findById(id);
    
    //Collection<LogAccount> statements = accountRepository.consultarLogsPorCuenta(id);

    if (accounts.isPresent()) {
        Account account = accounts.get();
        List<LogAccount> logs = account.getLog_accounts();
        model.addAttribute("logs", logs);
        model.addAttribute("accountId", id); // Pasar el ID de la cuenta
        return "bankStatement";
    } else {
        // Manejo de la cuenta no encontrada
        return "accounts"; // Nombre de tu plantilla Thymeleaf
        
    }
    
}




//UPDATE
    @GetMapping("/accounts/{id}/edit")
    public String accountEditForm(@PathVariable("id") ObjectId id, Model model) {
        
        Account account = accountRepository.findAccountById(id);
        if (account != null) {
            model.addAttribute("account", account);
            model.addAttribute("customers", clientRepository.findAll());
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


    @GetMapping("/accounts/{id}/withdraw")
    public String accountWithdrawMoney(@PathVariable("id") ObjectId id, Model model) {
        Account account = accountRepository.findAccountById(id);
        if (account != null && account.getBalance() >= 0) {
            model.addAttribute("account", account);
            return "opWithdraw";
        } else {
            return "redirect:/accounts";
        }
    }

    @PostMapping("/accounts/{id}/withdraw/save")
    public String accountWithdrawSave(
        @PathVariable("id") ObjectId id, 
        @RequestParam Double value, 
        @ModelAttribute Account account) {
        
       

        //int newLogId = generateNewLogId(account.getLog_accounts()); 
        accountRepository.addLogToAccount(id, this.superId ,value, Instant.now(), "Retiro");
        this.superId +=1;
        value *=-1;
        accountRepository.withdrawMoney(id, value);
        return "redirect:/accounts";
    }


    @GetMapping("/accounts/{id}/deposit")
    public String accountDepositoney(@PathVariable("id") ObjectId id, Model model) {
        Account account = accountRepository.findAccountById(id);
        if (account != null) {
            model.addAttribute("account", account);
            return "opDeposit";
        } else {
            return "redirect:/accounts";
        }
    }

    @PostMapping("/accounts/{id}/deposit/save")
    public String accountDepositSave(
        @PathVariable("id") ObjectId id, 
        @RequestParam Double value, 
        @ModelAttribute Account account) {

       

        
        accountRepository.addLogToAccount(id, this.superId ,value, Instant.now(), "Depósito");
        this.superId +=1;
        accountRepository.depositMoney(id, value);
        return "redirect:/accounts";
    }


    @GetMapping("/accounts/transfer/{id}")
    public String transferMoney(@PathVariable("id") ObjectId id, Model model){
        
        Account account = accountRepository.findAccountById(id);

        if (account != null) {
            model.addAttribute("account", account);
            model.addAttribute("customers", accountRepository.findAll());
            return "opTransfer";
        } else {
            return "redirect:/accounts";
        }

    }

    @PostMapping("accounts/transfer/{id}/save")
    public String transferMoneySave(
        @PathVariable("id") ObjectId senderId,
        @RequestParam Double value,
        @RequestParam("recieverId") ObjectId recieverId) {

        // Account sender = accountRepository.findAccountById(senderId);
        // Account reciever = accountRepository.findAccountById(senderId);
        Account sender = accountRepository.findAccountById(senderId);
        Account receiver = accountRepository.findAccountById(recieverId);

        if (sender != null && receiver!= null ) {
            
            sender.setBalance(sender.getBalance() - value);
            receiver.setBalance(receiver.getBalance() + value);

            // Save the updated accounts
            accountRepository.save(sender);
            accountRepository.save(receiver);

            // Log the transaction (assuming you have a method for this)
            accountRepository.addLogToAccount(senderId, 1, value, Instant.now(), "transfer out");
            accountRepository.addLogToAccount(recieverId, 1, value, Instant.now(), "transfer in");


            return "redirect:/accounts";
        } else {
            return "redirect:/accounts";
        }
    }


}





