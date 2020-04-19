package com.skeleton.app.repositories;

import com.skeleton.app.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

  boolean existsByEmail(String email);

  Account findByEmail(String email);
}
