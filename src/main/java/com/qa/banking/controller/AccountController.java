package com.qa.banking.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.qa.banking.model.Account;
import com.qa.banking.repository.AccountRepository;
@RestController
@RequestMapping("api/")
public class AccountController 
{
	@Autowired
	private AccountRepository accountRepository;
	@RequestMapping(value = "account/{id}", method = RequestMethod.GET)
    public Account get(@PathVariable Long id){
		return accountRepository.findOne(id);
		
    }
	
	@RequestMapping(value = "account", method = RequestMethod.GET)
    public List<Account> list(){
		return accountRepository.findAll();
		
    }
	
	
	
	
	@RequestMapping(value = "account", method = RequestMethod.POST)
    public Account create(@RequestBody Account account){
        return accountRepository.saveAndFlush(account);
    }
	
	
	@RequestMapping(value = "account/{id}", method = RequestMethod.DELETE)
    public Account delete(@PathVariable Long id){
		Account existingUser = accountRepository.findOne(id);
		accountRepository.delete(existingUser);
        return existingUser;
    }
	
	@RequestMapping(value = "account/alter/{idAccount}", method = RequestMethod.PUT)
    public Account update(@PathVariable Long idAccount, @RequestBody Account account){
		Account existingAccount = accountRepository.findOne(idAccount);
		account.setBalance(existingAccount.getBalance()+account.getBalance());
        //BeanUtils.copyProperties(account, existingAccount);
        return accountRepository.saveAndFlush(account);
    }
	@RequestMapping(value = "account/check/{id}", method = RequestMethod.GET)
	public int checkAccount(@PathVariable Long id){
	   if (get(id)==null) {
		   return 0;
	   }else {
		   return 1;
	   } 
	}
	@RequestMapping(value = "account/transfer/{recipientId}", method = RequestMethod.PUT)
	public Account transferBalance(@PathVariable Long recipientId,@RequestBody Account account){
		Account sender = accountRepository.findOne(account.getId());
		Account recipient = accountRepository.findOne(recipientId);
		sender.setBalance(sender.getBalance()-account.getBalance());
		recipient.setBalance(recipient.getBalance()+account.getBalance());
		accountRepository.saveAndFlush(recipient);
		return accountRepository.saveAndFlush(sender);
	}
	  
	
}
