package com.qa.banking.repository;
import com.qa.banking.model.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AccountRepository extends JpaRepository<Account,Long>{

//		List<Account> findByUsername(String username);
//		List<Account> findByUsernameAndPassword(String username,String password);
	    List<Account> findById(Long id);
	    
}
