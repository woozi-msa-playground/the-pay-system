package com.practice.banking.application

import com.practice.banking.adapter.axon.command.CreateRegisteredBankAccountAggregateCommand
import com.practice.banking.adapter.out.external.bank.RequestSearchBankAccount
import com.practice.banking.application.port.`in`.BankAccountRegisterCommand
import com.practice.banking.application.port.`in`.BankAccountRegisterEdaUseCase
import com.practice.banking.application.port.`in`.BankAccountRegisterUseCase
import com.practice.banking.application.port.out.RegisterBankAccountPort
import com.practice.banking.application.port.out.ExternalBankAccountPort
import com.practice.banking.application.port.out.MembershipStatusPort
import com.practice.banking.domain.BankAccount
import com.practice.banking.domain.vo.BankAccountAggregateIdentifier
import com.practice.banking.domain.vo.BankAccountNumber
import com.practice.banking.domain.vo.BankName
import com.practice.banking.domain.vo.LinkedStatusIsValid
import com.practice.banking.domain.vo.MembershipId
import com.practice.common.UseCase
import org.axonframework.commandhandling.gateway.CommandGateway

@UseCase
class BankAccountRegisterUseCaseImpl(
    private val commandGateway: CommandGateway,
    private val registerBankAccountPort: RegisterBankAccountPort,
    private val externalBankAccountPort: ExternalBankAccountPort,
    private val requestVerifyMembershipIdPort: MembershipStatusPort
) : BankAccountRegisterUseCase, BankAccountRegisterEdaUseCase {

    override fun registerBankAccount(command: BankAccountRegisterCommand): BankAccount {
        val membershipStatus = requestVerifyMembershipIdPort.membershipStatus(MembershipId(command.membershipId))
        if (!membershipStatus.isValid) {
            throw RuntimeException()
        }
        val externalBankAccount = externalBankAccountPort.bankAccountInfo(
            RequestSearchBankAccount(
                command.bankName,
                command.bankAccountNumber
            )
        )
        if (!externalBankAccount.isValid) {
            throw RuntimeException()
        }
        return registerBankAccountPort.createBankAccount(
            MembershipId(command.membershipId),
            BankName(command.bankName),
            BankAccountNumber(command.bankAccountNumber),
            LinkedStatusIsValid(command.linkedStatusIsValid),
            BankAccountAggregateIdentifier("")
        )
    }

    override fun registerBankAccountEda(command: BankAccountRegisterCommand): Boolean {
        val createRegisteredBankAccountAggregateCommand = CreateRegisteredBankAccountAggregateCommand(
            command.membershipId.toString(), command.bankName, command.bankAccountNumber
        )
        commandGateway.send<String>(createRegisteredBankAccountAggregateCommand).whenComplete { result, throwable ->
            if(throwable != null) {
                throw RuntimeException()
            }
            val membershipStatus = requestVerifyMembershipIdPort.membershipStatus(MembershipId(command.membershipId))
            if (!membershipStatus.isValid) {
                throw RuntimeException()
            }
            val externalBankAccount = externalBankAccountPort.bankAccountInfo(
                RequestSearchBankAccount(
                    command.bankName,
                    command.bankAccountNumber
                )
            )
            if (!externalBankAccount.isValid) {
                throw RuntimeException()
            }
            registerBankAccountPort.createBankAccount(
                MembershipId(command.membershipId),
                BankName(command.bankName),
                BankAccountNumber(command.bankAccountNumber),
                LinkedStatusIsValid(command.linkedStatusIsValid),
                BankAccountAggregateIdentifier(result)
            )
        }
        return true
    }
}
