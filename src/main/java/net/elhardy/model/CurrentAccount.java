package net.elhardy.model;

public class CurrentAccount extends BankAccount{
    private double overDraft;

    public CurrentAccount() {
        super();

    }

    public CurrentAccount(String c, double b, double overDraft) {
        super(c, b);
        this.overDraft = overDraft;
    }

    @Override
    public String toString() {
        return "Current Account, Overdraft ="+overDraft+super.toString();
    }

    @Override
    public String getType() {
        return "CURRENT_ACCOUNT";
    }

    public double getOverDraft() {
        return overDraft;
    }

    public void setOverDraft(double overDraft) {
        this.overDraft = overDraft;
    }

}
