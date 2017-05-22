package ru.elazarev.testtask;

/**
 * Account in OnlineBank.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 22.05.17
 */
public class Account {
    /**
     * bank account number.
     */
    private int accountNumber;
    /**
     * account balance.
     */
    private double value;

    /**
     * Default constructor.
     * @param accountNumber - bank account number.
     */
    public Account(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Getter for value field.
     * @return - value field.
     */
    public double getValue() {
        return value;
    }

    /**
     * Setter for value field.
     * @param value - new value
     */
    public void setValue(double value) {
        this.value = value;
    }
}
