package com.r2s.demo.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.demo.Model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

}
