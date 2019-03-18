package com.hsmchurch.app.security.account.entity;

import com.hsmchurch.app.security.account.entity.value.AccountOrigin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, QuerydslPredicateExecutor<Account> {

    Optional<Account> findBySocialIdAndAccountOrigin(String socialId, AccountOrigin accountOrigin);

}
