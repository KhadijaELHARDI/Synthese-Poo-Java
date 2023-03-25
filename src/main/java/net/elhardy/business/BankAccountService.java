package net.elhardy.business;

import net.elhardy.exceptions.AccountNotFoundException;
import net.elhardy.exceptions.BalanceNotSufficientException;
import net.elhardy.model.BankAccount;

import java.util.List;

public interface BankAccountService {
     BankAccount addBankAccount(BankAccount account);
     List<BankAccount> getAllAccounts();
     BankAccount getAccountById(String id) throws AccountNotFoundException;
      void addRandomData(int size);
      void credit(String accountId,double amount) throws AccountNotFoundException;
    void debit(String accountId,double amount) throws AccountNotFoundException, BalanceNotSufficientException;
    void transfer(String accountSource,String accountDestination,double amount) throws AccountNotFoundException, BalanceNotSufficientException;
    List<BankAccount> getSavingAccounts();
    List<BankAccount> getCurrentsAccounts();
    double getTotalBalance();
    List<BankAccount> searchAccounts(Condition condition);
}
