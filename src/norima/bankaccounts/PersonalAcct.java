package norima.bankaccounts;

public class PersonalAcct extends Account {
    public static final double MINIMUM_BALANCE = 100;
    public static final double MINIMUM_BALANCE_PENALTY = 2;

    public PersonalAcct(double bal, String fName, String lName, String str, String city, String st, String zip) {
        super(bal, fName, lName, str, city, st, zip);
        balance = bal;
        if (balance < MINIMUM_BALANCE) {
            throw new RuntimeException("Minimum initial balance for a personal account is $" + MINIMUM_BALANCE + ".");
        }
        cust = new Customer(fName, lName, str, city, st, zip);
    }

    public void checkMinimumBalance() {
        if (balance < MINIMUM_BALANCE) {
            System.out.println("Balance went below the minimum required. A penalty has been deducted.");
            balance -= MINIMUM_BALANCE_PENALTY;
        }
    }

    @Override
    public void withdrawal(double amt) {
        double previousBalance = balance;
        super.withdrawal(amt);
        if (Double.compare(previousBalance, balance) != 0) {
            checkMinimumBalance();
        }
    }
}
