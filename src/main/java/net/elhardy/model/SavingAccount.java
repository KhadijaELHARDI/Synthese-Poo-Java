package net.elhardy.model;

public class SavingAccount extends BankAccount{
    private double interstRate;

    public SavingAccount() {
        super();
    }

    @Override
    public String getType() {
        return "SAVING_ACCOUNT";
    }

    public SavingAccount(String c, double b, double interstRate) {
        super(c, b);
        this.interstRate = interstRate;
    }

    public double getInterstRate() {
        return interstRate;
    }

    public void setInterstRate(double interstRate) {
        this.interstRate = interstRate;
    }
}
