package edu.sumdu.refactoring;

public class Account {

    private String iban;
    private boolean premium;
    private int daysOverdrawn;
    private Money balance;
    private Customer customer;

    public Account(boolean premium, int daysOverdrawn) {
        this.premium = premium;
        this.daysOverdrawn = daysOverdrawn;
    }

    public double bankcharge() {
        double result = 4.5;
        result += overdraftCharge();
        return result;
    }

    private double overdraftCharge() {
        if (premium) {
            double result = 10;
            if (getDaysOverdrawn() > 7)
                result += (getDaysOverdrawn() - 7) * 1.0;
            return result;
        } else
            return getDaysOverdrawn() * 1.75;
    }

    public double overdraftFee() {
        if (premium) {
            return 0.10;
        } else {
            return 0.20;
        }
    }

    public int getDaysOverdrawn() {
        return daysOverdrawn;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isPremium() {
        return premium;
    }

    public String printCustomer() {
        return customer.getName() + " " + customer.getEmail();
    }

    public Money getBalance() {
        return balance;
    }


    public void withdraw(double sum, double overdraftMultiplier) {
        if (balance.isNegative()) {
            balance.subtractWithFee(
                    sum,
                    sum * overdraftFee() * overdraftMultiplier
            );
        } else {
            balance.subtract(sum);
        }
    }

    public void setMoney(double money) {
        if (this.balance == null) {
            this.balance = new Money(money, null);
        } else {
            this.balance = new Money(money, this.balance.getCurrency());
        }
    }

    public double getMoney() {
        return balance.getAmount();
    }

    public void setCurrency(String currency) {
        if (this.balance == null) {
            this.balance = new Money(0, currency);
        } else {
            this.balance = new Money(this.balance.getAmount(), currency);
        }
    }

}
