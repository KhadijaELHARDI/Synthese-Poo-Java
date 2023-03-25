package net.elhardy;

import net.elhardy.model.BankAccount;
import net.elhardy.model.CurrentAccount;
import net.elhardy.model.SavingAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App2 {
    public static void main(String[] args) {
        BankAccount [] accounts=new BankAccount[4];
         accounts[0]=new CurrentAccount("USD",5400,500);
        accounts[1]=new CurrentAccount("MAD",1298,4300);
        accounts[2]=new SavingAccount("USD",3200,4.6);
        accounts[3]=new SavingAccount("MAD",5400,4.3);
       for(BankAccount acc:accounts){
           //System.out.println(acc.getBalance());
           System.out.println(acc.getType());
           if(acc instanceof SavingAccount){
               System.out.println("Rate="+((SavingAccount)acc).getInterstRate());
           }
           if(acc instanceof CurrentAccount){
               System.out.println("Overdraft="+((CurrentAccount)acc).getOverDraft());
           }
        }
        System.out.println("listes");
       List <BankAccount> bankAccountList=new ArrayList<>();

        bankAccountList.add(new CurrentAccount("MAD",5400,4500));
        bankAccountList.add(new CurrentAccount("MAD",4300,4300));
        bankAccountList.add(new SavingAccount("MAD",5400,3));
        bankAccountList.add(new SavingAccount("MAD",234,4));
        bankAccountList.add(new CurrentAccount("MAD",65,2));
        System.out.printf("========Map===========");
        Map<String,BankAccount> bankAccountMap=new HashMap<>();

        bankAccountMap.put("cc1",new CurrentAccount("MAD",5400,4500));
        bankAccountMap.put("cc2",new SavingAccount("MAD",4300,400));
        bankAccountMap.put("cc3",new CurrentAccount("MAD",400,3));
        bankAccountMap.put("cc4",new SavingAccount("MAD",4300,2));
        BankAccount acc=bankAccountMap.get("cc2");
        System.out.println(acc.toString());
        for(String key:bankAccountMap.keySet()){
            System.out.println(bankAccountMap.get(key));
        }
        for(BankAccount ba:bankAccountMap.values()){
            System.out.println(ba);
        }

    }
}
