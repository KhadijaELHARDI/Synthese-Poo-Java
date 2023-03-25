package net.elhardy.model;

import net.elhardy.business.BankAccountService;
import net.elhardy.business.BankAccountServiceImpl;
import net.elhardy.exceptions.AccountNotFoundException;

import java.util.List;
import java.util.function.Consumer;

public class Application{
    public static void main(String[] args){
        BankAccountService bankAccountService=new BankAccountServiceImpl();
        bankAccountService.addBankAccount(new CurrentAccount("MAD",43000,100));
        bankAccountService.addBankAccount(new SavingAccount("MAD",12000,3.5));
        BankAccount bankAccount3=new CurrentAccount("MAD",4300,100);
        bankAccount3.setAccountId("CC1");
        bankAccountService.addBankAccount(bankAccount3);
        List<BankAccount> allAccounts=bankAccountService.getAllAccounts();
       /* for(int i=0;i<allAccounts.size();i++){
            System.out.println(allAccounts.get(i).toString());
        }*/
      /*  for(BankAccount bankAccount:allAccounts){
            System.out.println(bankAccount.toString());
        }*/
        /*ancien syntaxe avec un objet anonyme créer de l'interface consumer*/
       /* allAccounts.forEach(new Consumer<BankAccount>() {
            @Override
            public void accept(BankAccount account) {
                System.out.println( account.toString());
            }
        });*/
        /*nouveau syntaxe avec l'expression lambda*/
       /* allAccounts.forEach(account->
            System.out.println( account.toString())
        );*/
        allAccounts.forEach(System.out::println);
        System.out.println("---------------");

        BankAccount accountById = null;
        try {
            accountById = bankAccountService.getAccountById("CC1");
            System.out.println(accountById.toString());
        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        System.out.println( "*************");
        System.out.println("suite du programme");

    }
}
