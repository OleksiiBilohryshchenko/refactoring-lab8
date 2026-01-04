package edu.sumdu.refactoring;

public class PersonCustomer extends Customer {

    public PersonCustomer(String name, String surname, String email, Account account) {
        super(name, surname, email, CustomerType.PERSON, account);
    }

    @Override
    protected double overdraftMultiplier() {
        return 1;
    }
}
