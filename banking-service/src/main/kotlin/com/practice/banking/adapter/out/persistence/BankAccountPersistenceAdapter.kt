package com.practice.banking.adapter.out.persistence

import com.practice.banking.application.port.out.FindBankAccountEdaPort
import com.practice.banking.application.port.out.RegisterBankAccountPort
import com.practice.banking.domain.BankAccount
import com.practice.banking.domain.vo.BankAccountNumber
import com.practice.banking.domain.vo.BankName
import com.practice.banking.domain.vo.LinkedStatusIsValid
import com.practice.banking.domain.vo.MembershipId
import com.practice.banking.domain.vo.BankAccountId
import com.practice.banking.application.port.out.FindBankAccountPort
import com.practice.banking.application.port.out.ModifyBankAccountPort
import com.practice.banking.domain.vo.BankAccountAggregateIdentifier
import com.practice.common.PersistenceAdapter
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

@PersistenceAdapter
class BankAccountPersistenceAdapter(
    private val springDataBankAccountRepository: SpringDataBankAccountRepository
) : RegisterBankAccountPort, FindBankAccountPort, FindBankAccountEdaPort, ModifyBankAccountPort {

    @Transactional
    override fun createBankAccount(
        membershipId: MembershipId,
        bankName: BankName,
        bankAccountNumber: BankAccountNumber,
        linkedStatusIsValid: LinkedStatusIsValid,
        bankAccountAggregateIdentifier: BankAccountAggregateIdentifier,
    ): BankAccount = BankAccountMapper.mapToEntityDomain(
        springDataBankAccountRepository.save(
            BankAccountJpaEntity(
                membershipId.membershipId.toLong(),
                bankName.bankName,
                bankAccountNumber.bankAccountNumber,
                linkedStatusIsValid.linkedStatusIsValid,
                bankAccountAggregateIdentifier.bankAccountAggregateIdentifier
            )
        )
    )

    @Transactional(readOnly = true)
    override fun findBankAccount(bankAccountId: BankAccountId): BankAccount =
        BankAccountMapper.mapToEntityDomain(
            springDataBankAccountRepository.findByIdOrNull(bankAccountId.BankAccountId.toLong())
                ?: throw EntityNotFoundException()
        )

    @Transactional
    override fun modifyBankAccount(
        bankAccountId: BankAccountId,
        membershipId: MembershipId,
        bankName: BankName,
        bankAccountNumber: BankAccountNumber,
        linkedStatusIsValid: LinkedStatusIsValid
    ): BankAccount {
        val registeredBankAccountJpaEntity =
            springDataBankAccountRepository.findByIdOrNull(bankAccountId.BankAccountId.toLong())
                ?: throw EntityNotFoundException()
        registeredBankAccountJpaEntity.bankName = bankName.bankName
        registeredBankAccountJpaEntity.bankAccountNumber = bankAccountNumber.bankAccountNumber
        registeredBankAccountJpaEntity.linkedStatusIsValid = linkedStatusIsValid.linkedStatusIsValid
        return BankAccountMapper.mapToEntityDomain(
            springDataBankAccountRepository.saveAndFlush(registeredBankAccountJpaEntity)
        )
    }

    @Transactional(readOnly = true)
    override fun findBankAccountEda(membershipId: MembershipId): BankAccount =
        springDataBankAccountRepository.findByMembershipId(membershipId.membershipId)
            ?.let(BankAccountMapper::mapToEntityDomain)
            ?: throw EntityNotFoundException()
}
