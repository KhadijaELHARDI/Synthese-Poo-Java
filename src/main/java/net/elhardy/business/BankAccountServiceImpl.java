package net.elhardy.business;

import net.elhardy.exceptions.AccountNotFoundException;
import net.elhardy.exceptions.BalanceNotSufficientException;
import net.elhardy.model.AccountStatus;
import net.elhardy.model.BankAccount;
import net.elhardy.model.CurrentAccount;
import net.elhardy.model.SavingAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class BankAccountServiceImpl implements BankAccountService{
 private List <BankAccount>bankAccountsList=new ArrayList<>();
    @Override
    public BankAccount addBankAccount(BankAccount account) {
        bankAccountsList.add(account);
        return account;
    }

    @Override
    public List<BankAccount> getAllAccounts() {
        return bankAccountsList;
    }

    @Override
    public BankAccount getAccountById(String id) throws AccountNotFoundException {
       return bankAccountsList.stream()
                .filter(acc -> acc.getAccountId().equals(id))
                .findFirst().orElseThrow(()->new AccountNotFoundException("BankAccount not found"));

       /*ancien for(BankAccount bankAccount:bankAccountsList){
            if(bankAccount.getAccountId().equals(id)){
                return bankAccount;
            }
        }

            throw new AccountNotFoundException("BankAccount not found");
        */

    }

    @Override
    public void addRandomData(int size) {
        AccountStatus[] values = AccountStatus.values();
        Random random=new Random();
        for (int i=0;i<size;i++){
            BankAccount bankAccount;
            if(Math.random()>0.5){
                //expression ternaire
                bankAccount=new CurrentAccount(Math.random()>0.5?"MAD":"USD",Math.random()*1000000,Math.random()*50000);
                bankAccount.setStatus(values[random.nextInt(values.length)]);
            }else{
                bankAccount=new SavingAccount(Math.random()>0.5?"MAD":"USD",Math.random()*1000000,3+Math.random()*7);
                bankAccount.setStatus(values[random.nextInt(values.length)]);

            }
            bankAccountsList.add(bankAccount);
        }

    }

    @Override
    public void credit(String accountId, double amount) throws AccountNotFoundException {
        BankAccount accountById = getAccountById(accountId);
        accountById.setBalance(accountById.getBalance()+amount);

    }

    @Override
    public void debit(String accountId, double amount) throws AccountNotFoundException,BalanceNotSufficientException {

        BankAccount accountById = getAccountById(accountId);
       if(amount>accountById.getBalance()) throw new BalanceNotSufficientException("Balance not suu");
        accountById.setBalance(accountById.getBalance()-amount);
    }

    @Override
    public void transfer(String accountSource, String accountDestination, double amount) throws AccountNotFoundException, BalanceNotSufficientException {
        debit(accountSource,amount);
        credit(accountDestination,amount);

    }

    @Override
    public List<BankAccount> getSavingAccounts() {

        List<BankAccount> collect = bankAccountsList
                .stream()
                .filter(acc -> acc instanceof SavingAccount)
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<BankAccount> getCurrentsAccounts() {
        List<BankAccount> collect = bankAccountsList
                .stream()
                .filter(acc -> acc.getType().equals("SAVING_ACCOUNT"))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public double getTotalBalance() {


        return bankAccountsList
                .stream()
                .map(acc->acc.getBalance())
                .reduce(0.0,(a,v)->a+v);
    }

    @Override
    public List<BankAccount> searchAccounts(Condition condition) {
        List<BankAccount> result=new ArrayList<>();
        for(BankAccount acc:bankAccountsList){
            if(condition.test(acc)){
                result.add(acc);
            }
        }

        return result;
    }
}
