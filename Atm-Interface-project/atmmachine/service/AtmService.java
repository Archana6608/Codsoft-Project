package atmmachine.service;

import atmmachine.model.BankAccount;
import atmmachine.exception.AtmException;

public interface AtmService {
    void registerAccount(String name, String pin, double initialBalance);
    BankAccount login(String accountNumber, String pin) throws AtmException;
    void executeWithdrawal(BankAccount account, double amount) throws AtmException;
    void executeDeposit(BankAccount account, double amount) throws AtmException;
    void executeTransfer(BankAccount sourceAccount, String targetAccountNumber, double amount) throws AtmException;
    String getAuditTrace(int index);
}