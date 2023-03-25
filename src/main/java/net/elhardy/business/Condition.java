package net.elhardy.business;

import net.elhardy.model.BankAccount;

public interface Condition {
    boolean test(BankAccount bankAccount);
}
