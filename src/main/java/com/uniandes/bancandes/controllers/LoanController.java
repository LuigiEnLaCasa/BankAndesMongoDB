package com.uniandes.bancandes.controllers;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniandes.bancandes.models.Loan;
import com.uniandes.bancandes.repository.CustomerRepository;
import com.uniandes.bancandes.repository.LoanRepository;
import com.uniandes.bancandes.repository.LogLoanRepository;

@Controller
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LogLoanRepository logLoanRepository;

    @GetMapping("/loans")
    public String loans(Model model) {
        Collection<Loan> loansCollection = loanRepository.getLoans();
        model.addAttribute("loans", loansCollection);
        return "loans";
    }

    @GetMapping("/loans/new")
    public String loanForm(Model model) {
        model.addAttribute("newLoan", new Loan());
        model.addAttribute("customers", customerRepository.getCustomers());
        return "newLoan";
    }

    @PostMapping("/loans/new/save")
    public String loanSave(@ModelAttribute Loan loan) {
        System.out.println(loan.getCustomer().getIdcard());
        loanRepository.insertLoan(loan.getState(), loan.getValueloan(), loan.getInitialfee(),
                loan.getPendingfees(), loan.getPendingvalueloan(), loan.getValuefee(), loan.getInterest(),
                loan.getPayday(), loan.getType(), loan.getCustomer().getIdcard());
        return "redirect:/loans";
    }

    @GetMapping("/loans/{id}/edit")
    public String loanEditForm(@PathVariable("id") Integer id, Model model) {
        Loan loan = loanRepository.findLoanById(id);
        if (loan != null) {
            model.addAttribute("loan", loan);
            model.addAttribute("customers", customerRepository.getCustomers());
            return "editLoan";
        } else {
            return "redirect:/loans";
        }
    }

    @GetMapping("/loans/{id}/make_fee_payment")
    public String putMethodName(@PathVariable int id) {
        Loan loan = loanRepository.findLoanById(id);
        loanRepository.updatePendingValuesByLoanId(loan.getId(), loan.getPendingvalueloan() - loan.getValuefee(),
                loan.getPendingfees() - 1);

        logLoanRepository.insertLogLoan(loan.getValuefee(), "fee_payment", Date.from(Instant.now()), loan.getId());

        return "redirect:/loans";
    }

    @PostMapping("/loans/{id}/make_extra_fee_payment")
    public String makeExtraFeePayment(@PathVariable int id, @RequestParam Double amount) {
        Loan loan = loanRepository.findLoanById(id);
        loanRepository.updateExtraPendingValueLoan(loan.getId(), loan.getPendingvalueloan() - amount);

        logLoanRepository.insertLogLoan(amount, "extra_fee_payment", Date.from(Instant.now()),
                loan.getId());
        return "redirect:/loans";
    }

    @PostMapping("/loans/{id}/edit/save")
    public String loanEditSave(@PathVariable("id") Integer id, @ModelAttribute Loan loan) {
        loanRepository.updateLoanState((long) id, loan.getState());
        return "redirect:/loans";
    }
}
