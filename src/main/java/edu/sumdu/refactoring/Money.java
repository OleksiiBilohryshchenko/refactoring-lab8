package edu.sumdu.refactoring;

public class Money {

    private double amount;
    private final String currency;

    public Money(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public boolean isNegative() {
        return amount < 0;
    }

    public void subtract(double value) {
        this.amount -= value;
    }

    public void subtractWithFee(double value, double fee) {
        this.amount -= (value + fee);
    }
}
