package net.elhardy;

import net.elhardy.business.BankAccountService;
import net.elhardy.business.BankAccountServiceImpl;
import net.elhardy.business.Condition;
import net.elhardy.exceptions.AccountNotFoundException;
import net.elhardy.exceptions.BalanceNotSufficientException;
import net.elhardy.model.BankAccount;
import net.elhardy.model.CurrentAccount;
import net.elhardy.model.SavingAccount;
import net.elhardy.utils.DataTransformationUtils;

import java.util.List;


public class App3 {
    public static void main(String[] args){
        BankAccountService bankAccountService=new BankAccountServiceImpl();
        bankAccountService.addRandomData(20);
        BankAccount bankAccount1=new CurrentAccount("MAD",43000,100);
        bankAccount1.setAccountId("CC1");
        BankAccount bankAccount2=new SavingAccount("MAD",1000,3.2);
        bankAccount2.setAccountId("CC2");
        bankAccountService.addBankAccount(bankAccount1);
        bankAccountService.addBankAccount(bankAccount2);
        /*bankAccountService.getAllAccounts()
                .stream()
                .map(DataTransformationUtils::toJson)
                .forEach(System.out::println);*/
        BankAccount acc1=null;
        BankAccount acc2=null;
        try{
             acc1 = bankAccountService.getAccountById("CC1");
            acc2 = bankAccountService.getAccountById("CC2");


            System.out.println( DataTransformationUtils.toJson(acc1));
            System.out.println( DataTransformationUtils.toJson(acc2));
            bankAccountService.debit(acc1.getAccountId(),5000);
            System.out.println(DataTransformationUtils.toJson(acc1));
            System.out.println("=============");
            bankAccountService.transfer(acc1.getAccountId(),acc2.getAccountId(),300);
            System.out.println( DataTransformationUtils.toJson(acc1));
            System.out.println( DataTransformationUtils.toJson(acc2));

        } catch (AccountNotFoundException | BalanceNotSufficientException e) {
            System.out.println(e.getMessage());

        }
        System.out.println( "*************************");
        System.out.println( DataTransformationUtils.toJson(acc1));
        System.out.println( DataTransformationUtils.toJson(acc2));
        System.out.println("====================");
        System.out.println("+++++++++++++++++++++++++++++++");
        bankAccountService.getSavingAccounts()
                .stream()
                .map(DataTransformationUtils::toJson)
                .forEach(System.out::println);
        System.out.println("Total Balance:"+bankAccountService.getTotalBalance());
        List<BankAccount> bankAccountList = bankAccountService.searchAccounts(acc -> (acc.getBalance() > 10000));
        bankAccountList.forEach(System.out::println);
        bankAccountList.stream().map(DataTransformationUtils::toJson).forEach(System.out::println );
    }
}
