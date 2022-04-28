package norima.bankaccounts;

import java.util.Scanner;

public class TestCase {

    public class UserInput {
        private static Scanner input = new Scanner(System.in);

        public static int askForNumber(int lowerLimit, int upperLimit, String question) {
            int userSelection;

            while (true) {
                System.out.print(question + ": ");
                try {
                    userSelection = Integer.parseInt(input.nextLine());
                    if (userSelection > upperLimit || userSelection < lowerLimit) {
                        System.out.println("Your input is invalid. Please try again.");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("Your choice is invalid. Please try again.");
                    continue;
                }
                return userSelection;
            }
        }

        public static int askForNumber(String question) {
            while (true) {
                System.out.print(question + ": ");
                try {
                    int userSelection = Integer.parseInt(input.nextLine());
                    if (userSelection >= 1) {
                        return userSelection;
                    } else {
                        System.out.println("Please enter a positive number. Try again.");
                    }

                } catch (Exception e) {
                    System.out.println("Your input is invalid. Please try again.");
                }
            }
        }

        public static Boolean askForConfirmation(String question) {
            String userSelection;
            while (true) {
                System.out.print(question + ": ");
                userSelection = input.nextLine();
                if (userSelection.equalsIgnoreCase("y")) {
                    return true;
                } else if (userSelection.equalsIgnoreCase("n")) {
                    return false;
                } else {
                    System.out.println("Invalid answer. Please try again.");
                }
            }
        }
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("What kind of account do you want to make? ");
            System.out.format("[1] Personal Account ($%.2f minimum balance required)%n", PersonalAcct.MINIMUM_BALANCE);
            System.out.format("[2] Business Account ($%.2f minimum balance required)%n", BusinessAcct.MINIMUM_BALANCE);
            System.out.println("[0] Exit");
            int typeOfAccount = UserInput.askForNumber(0, 2, "Your choice");

            Account account;
            try {
                if (typeOfAccount == 1) {
                    int initialBalance = UserInput.askForNumber("What's your initial deposit?");
                    account = new PersonalAcct(initialBalance, "Test", "User", "30th Street", "Taguig City",
                            "Metro Manila",
                            "1609");
                } else if (typeOfAccount == 2) {
                    int initialBalance = UserInput.askForNumber("What's your initial deposit?");
                    account = new BusinessAcct(initialBalance, "Test", "User", "30th Street", "Taguig City",
                            "Metro Manila",
                            "1609");
                } else {
                    break;
                }

            } catch (RuntimeException e) {
                System.out.format("%s Please try again. %n%n", e.getMessage());
                continue;
            }

            System.out.println("\nAccount is created: ");
            System.out.format("%s %n%n", account.toString());

            while (true) {
                System.out.format("Do you want to withdraw or deposit? Your balance is $%.2f %n", account.getBalance());
                System.out.println("[1] Deposit");
                System.out.println("[2] Withdraw");
                System.out.println("[0] Go back to account creation.");
                int typeOfAction = UserInput.askForNumber(0, 2, "Your choice");

                if (typeOfAction == 3) {
                    break;
                } else if (typeOfAction == 1) {
                    int depositAmount = UserInput.askForNumber("How much do you want to deposit?");
                    account.deposit(depositAmount);
                } else if (typeOfAction == 2) {
                    int withdrawAmount = UserInput.askForNumber("How much do you want to withdraw?");
                    account.withdrawal(withdrawAmount);
                }
            }

        }

        System.exit(0);
        // // Account creation - Personal, balance below minimum.
        // try {
        // PersonalAcct pa1 = new PersonalAcct(90, "Ian", "Letada", "30th Street",
        // "Taguig City", "Metro Manila",
        // "1609");
        // pa1.getBalance();
        // } catch (Exception e) {
        // System.out.println(e.toString());
        // }

        // // Account creation - Business, balance below minimum.
        // try {
        // BusinessAcct pa1 = new BusinessAcct(90, "Ian", "Letada", "30th Street",
        // "Taguig City", "Metro Manila",
        // "1609");
        // pa1.getBalance();
        // } catch (Exception e) {
        // System.out.println(e.toString());
        // }?

        // // Withdrawal - Personal, balance below minimum.
        // PersonalAcct pa1 = new PersonalAcct(150, "Ian", "Letada", "30th Street",
        // "Taguig City", "Metro Manila",
        // "1609");
        // System.out.println("Personal account created: ");
        // System.out.println(pa1.toString());
        // pa1.withdrawal(60);
        // System.out.println(pa1.toString());

        // System.out.println("==========================================================================");

        // // Withdrawal - Business, balance below minimum.
        // BusinessAcct ba1 = new BusinessAcct(550, "Ian", "Letada", "30th Street",
        // "Taguig City", "Metro Manila",
        // "1609");
        // System.out.println("Business account created: ");
        // System.out.println(ba1.cust.toString());
        // ba1.withdrawal(60);
        // System.out.println(ba1.toString());

    }
}
