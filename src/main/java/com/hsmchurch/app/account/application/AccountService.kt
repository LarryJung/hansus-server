package com.hsmchurch.app.account.application

import com.hsmchurch.app.account.domain.Account
import com.hsmchurch.app.account.domain.AccountRepository
import com.hsmchurch.app.common.support.*
import com.hsmchurch.app.common.support.CrudStringFormat.READ_FAIL
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AccountService(
        private val accountRepository: AccountRepository
) {
    companion object {
        private val ENTITY_NAME = "계정"
        val log = logger()
    }

    fun findById(accountId: Long): Account {
        return accountRepository.findByIdOrNull(accountId) ?: run {
            log.error(READ_FAIL(ENTITY_NAME), accountId, "유저를 찾을 수 없습니다.")
            throw RuntimeException("유저를 찾을 수 없습니다.")
        }
    }

    fun findAll(): List<Account> {
        return accountRepository!!.findAll()
    }

}
