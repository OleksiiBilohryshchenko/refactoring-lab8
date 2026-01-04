package edu.sumdu.refactoring;

public abstract class Customer {

    private String name;
    private String surname;
    private String email;
    private CustomerType customerType;
    private Account account;
    private double companyOverdraftDiscount = 1;
    protected abstract double overdraftMultiplier();

    protected Account getAccount() {
        return account;
    }

    public Customer(String name, String surname, String email, CustomerType customerType, Account account) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.customerType = customerType;
        this.account = account;
    }

    public Customer(String name, String email, Account account, double companyOverdraftDiscount) {
        this.name = name;
        this.email = email;
        this.customerType = CustomerType.COMPANY;
        this.account = account;
        this.companyOverdraftDiscount = companyOverdraftDiscount;
    }

    public void withdraw(double sum, String currency) {
        if (!account.getCurrency().equals(currency)) {
            throw new RuntimeException("Can't extract withdraw " + currency);
        }
        applyWithdraw(sum, overdraftMultiplier());
    }


    private void applyWithdraw(double sum, double overdraftMultiplier) {
        if (account.getMoney() < 0) {
            account.setMoney(
                    (account.getMoney() - sum)
                            - sum * account.overdraftFee() * overdraftMultiplier
            );
        } else {
            account.setMoney(account.getMoney() - sum);
        }
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String printCustomerDaysOverdrawn() {
        String fullName = name + " " + surname + " ";
        String accountDescription =
                "Account: IBAN: " + account.getIban() + ", Days Overdrawn: " + account.getDaysOverdrawn();
        return fullName + accountDescription;
    }

    public String printCustomerMoney() {
        String fullName = name + " " + surname + " ";
        String accountDescription =
                "Account: IBAN: " + account.getIban() + ", Money: " + account.getMoney();
        return fullName + accountDescription;
    }

    public String printCustomerAccount() {
        return "Account: IBAN: " + account.getIban()
                + ", Money: " + account.getMoney()
                + ", Account type: " + account.getType();
    }
}
