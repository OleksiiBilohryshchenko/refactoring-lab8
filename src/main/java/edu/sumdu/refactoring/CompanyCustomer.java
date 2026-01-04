package edu.sumdu.refactoring;

public class CompanyCustomer extends Customer {

    private final double companyOverdraftDiscount;

    public CompanyCustomer(String name, String email, Account account, double companyOverdraftDiscount) {
        super(name, email, account, companyOverdraftDiscount);
        this.companyOverdraftDiscount = companyOverdraftDiscount;
    }

    @Override
    protected double overdraftMultiplier() {
        if (getAccount().isPremium()) {
            return companyOverdraftDiscount / 2;
        }
        return companyOverdraftDiscount;
    }
}
