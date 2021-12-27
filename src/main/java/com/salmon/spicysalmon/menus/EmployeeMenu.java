package com.salmon.spicysalmon.menus;

import com.salmon.spicysalmon.Util;
import com.salmon.spicysalmon.controllers.AccountRequestController;
import com.salmon.spicysalmon.controllers.AuthenticationController;
import com.salmon.spicysalmon.controllers.CustomerController;
import com.salmon.spicysalmon.controllers.EmployeeController;
import com.salmon.spicysalmon.models.*;
import java.util.ArrayList;

public class EmployeeMenu {
    String EMPLOYEE_HEADING = "Employee Menu: Please choose a valid option.";
    String[] EMPLOYEE_OPTIONS = {
            "Log out",
            "Menu for Customer handling",
            "Menu for Application handling",
    };
    String EMPLOYEE_HEADING2 = "Customer handling menu: Please choose a valid option.";
    String[] EMPLOYEE_OPTIONS2 = {
            "Go back",
            "Create customer",
            "Delete customer",
            "Delete bank account",
            "Print all registered customers.",
            // is these 2 options (7, 8) agreed upon functions of the employee?
            "Deposit money into bank account ", // this function is used when a customer meets with an employee in person and has cash that they want to deposit
            "Withdraw money from bank account", // this function is used when a customer meets with an employee in person and has cash that they want to withdraw
            ""
            // Continue to add more options here and to the switch case as you see fit, it's ok to create submenus if anyone want to do that,
            // just make a switch case inside the switch case (or make a seperate method that is called inside the switch case)
    };
    String EMPLOYEE_HEADING3 = "Account request handling menu: Please choose a valid option.";
    String[] EMPLOYEE_OPTIONS3 = {
            "Go back",
            "Review specific customer account request",
            "Review specific bank account request",
            "List all customer account request",
            "List all bank account request"
    };

    // the first menu the employee will see, this then branches of into a Customer and a Account request Menu
    public void show(String SSN){
        AccountRequestController accountRequestController = new AccountRequestController();
        CustomerController customerController = new CustomerController();
        EmployeeController employeeController = new EmployeeController();
        AuthenticationController authenticationController = new AuthenticationController();

        Menu employeeMenu = new Menu(EMPLOYEE_HEADING, EMPLOYEE_OPTIONS);
        int userInput = 0;
        do {
            System.out.println(employeeMenu);
            userInput = employeeMenu.getValidOption();
            switch (userInput) {
                case 0 -> System.out.println("goodbye");
                case 1 -> showCustomerMenu(customerController, authenticationController);
                case 2 -> showAccountRequestMenu(accountRequestController, customerController);
            }
        }while (userInput != 0);
    }
    // Customer menu that handles all the functionality were the Employee directly interacts with customers
    public void showCustomerMenu(CustomerController customerController, AuthenticationController authenticationController){
        Menu employeeCustomerMenu = new Menu(EMPLOYEE_HEADING2, EMPLOYEE_OPTIONS2);
        System.out.println(employeeCustomerMenu);
        int userInput = employeeCustomerMenu.getValidOption();
        do {
            switch (userInput){
                case 1: // create customer
                    createCustomer(customerController);
                    break;
                case 2: // remove customer
                    removeCustomer(customerController);
                    break;
                case 3: // delete a bank account
                    deleteBankAccount(customerController);
                    break;
                case 4: // print all customers
                    printAllCustomers(customerController);
                    break;
                case 5:

                    break;
            }
            break;
        }while (userInput != 0);
    }

    // Account request menu that handles all the functionality were the Employee directly interacts with Account Requests
    public void showAccountRequestMenu(AccountRequestController accountRequestController, CustomerController customerController){
        Menu employeeAccountRequestMenu = new Menu(EMPLOYEE_HEADING3,EMPLOYEE_OPTIONS3);
        System.out.println(employeeAccountRequestMenu);
        int userInput = employeeAccountRequestMenu.getValidOption();
        switch (userInput){
            case 1: // approve/deny customer application
                try {
                    specificCustomerAccountRequest(accountRequestController);
                }catch (Exception IOException){
                    System.out.println(IOException.getMessage());
                }
                break;
            case 2: // approve/deny bank application
                try {
                    specificBankBankAccountRequest(accountRequestController);
                }catch (Exception IOException){
                    System.out.println(IOException.getMessage());
                }
                break;
            case 3: // look att all the customer account requests, then pick one you want to approve/deny
                try {
                    listAllCustomerAccountRequests(accountRequestController);
                }catch (Exception IOException){
                    System.out.println(IOException.getMessage());
                }
                break;
            case 4: // look att all the bank account requests, then pick one you want to approve/deny
                try {
                    listAllBankAccountRequests(accountRequestController);
                }catch (Exception IOException){
                    System.out.println(IOException.getMessage());
                }
                break;
        }
    }

    // the employee logs into a customer account, for use when the customer is next to the employee
    public void goToCustomer(AuthenticationController authenticationController){
        System.out.println("type in the login info of the customer you want to access");
        authenticationController.customerLogin();
    }

    // creates a new customer
    public void createCustomer(CustomerController customerController){
        System.out.println("You have chosen: Create a customer.");
        String socialSecurityNumber = Util.readLine("What is your SSN?: ");
        String password = passwordCreation();
        String firstName = Util.readLine("What is your first name?: ");
        String lastName = Util.readLine("What is your last name?: ");
        int salary = Util.readInt("What is your salary?: ");
        String residentalArea = Util.readLine("Where do you live?: ");
        String occupation = Util.readLine("What is your occupation?: ");
        customerController.createCustomer(socialSecurityNumber,password, firstName,lastName, salary, residentalArea, occupation);
    }
    public String passwordCreation(){
        String password = "";
        do {
            password = Util.readLine("Create a new password, it has to have:"
                    + Util.EOL + "Both upper-case and lower-case letters"
                    + Util.EOL + "One number"
                    + Util.EOL + "Longer than 8 characters" + Util.EOL);

            if(password.equals(password.toLowerCase()))System.out.println("Your password did not have a uppercase Character");
            if(password.equals(password.toUpperCase()))System.out.println("Your password did not have a lowercase Character");
            if(!password.matches(".*[1234567890].*"))System.out.println("Your password did not have a number");
            if(password.length() < 8)System.out.println("Your password was not longer than 8 characters");
            System.out.println();
        }while (!(password.length() > 8
                && !password.equals(password.toLowerCase())
                && !password.equals(password.toUpperCase())
                && password.matches(".*[1234567890].*")));
        return password;
    }

    // removes a customer with the specified SSN
    public void removeCustomer(CustomerController customerController){
        System.out.println("You have chosen: Remove a customer.");
        String remove = Util.readLine("What customer do you wish to remove? Enter SSN: ");
        System.out.println(customerController.removeCustomer(remove));
    }
    // deletes a bank account with the specified accountNumber
    public void deleteBankAccount(CustomerController customerController){
        String accountNumber = Util.readLine("Type in the account number of the bank account do you want to remove");
        customerController.deleteBankAccount(accountNumber);
    }
    // prints all customers
    public void printAllCustomers(CustomerController customerController){
        System.out.println("You have chosen: Print all registered customers.");
        System.out.println(customerController.printAllCustomers());
    }
    // goes to a specific customers customer account requests
    public void specificCustomerAccountRequest(AccountRequestController accountRequestController) throws Exception {
        String SSN = Util.readLine("Which customers request do you want to look at?");
        CustomerAccountRequest CAR = accountRequestController.getCustomerAccountRequest(SSN); // CAR = Customer Account Request
        System.out.println(CAR.toString());
        approveDenyCustomerAccountRequest(CAR, accountRequestController);
    }
    // goes to a specific customers bank account requests
    public void specificBankBankAccountRequest(AccountRequestController accountRequestController) throws Exception {
        String SSN = Util.readLine("Which customers request do you want to look at?");
        ArrayList<BankAccountRequest> BARs = accountRequestController.getSpecificCustomerBankAccountRequests(SSN); // BARs = Bank Account Requests
        for (BankAccountRequest BAR : BARs){
            System.out.println(BAR.toString());
        }
        if (BARs.size() == 1){
            approveDenyBankAccountRequest(BARs.get(0), accountRequestController);
        }else {
            int userInput = (Util.readInt("Which request do you want to look at?")) - 1;
            BankAccountRequest BAR = BARs.get(userInput);
            System.out.println(BAR);
            approveDenyBankAccountRequest(BAR, accountRequestController);
        }
    }

    // lists all customer account requests, then allows the employee to approve/deny that request
    public void listAllCustomerAccountRequests(AccountRequestController accountRequestController) throws Exception {
        System.out.println(accountRequestController.printAllCustomerAccountRequests());
        int requestNum = (Util.readInt("Which request do you want to look at? type 0 to exit")) - 1;
        if (requestNum != 0){
            CustomerAccountRequest request = accountRequestController.getSpecificCustomerAccountRequestFromList(requestNum);
            System.out.println(request.toString());
            approveDenyCustomerAccountRequest(request, accountRequestController);
        }
    }
    // lists all bank account requests, then allows the employee to approve/deny that request
    public void listAllBankAccountRequests(AccountRequestController accountRequestController) throws Exception {
        System.out.println(accountRequestController.printAllBankAccountRequests());
        int requestNum = (Util.readInt("Which request do you want to check out? type 0 to exit")) -1;
        if (requestNum != 0){
            BankAccountRequest request = accountRequestController.getSpecificBankAccountRequestFromList(requestNum);
            System.out.println(request.toString());
            approveDenyBankAccountRequest(request, accountRequestController);
        }

    }
    // approves or denies a customer account request, denial needs a accompanied message as to why it was denied
    public void approveDenyCustomerAccountRequest(CustomerAccountRequest request, AccountRequestController accountRequestController) throws Exception {
        String stringUserInput = "";
        do {
            stringUserInput = Util.readLine("Please type in: approve or deny");
            if (stringUserInput.equals("approve")) {
                //Util.readLine("Please type in the reason for the approval:"); do we need message for approval?
                String message = Util.readLine("Please type in the reason for the approval:");
                accountRequestController.approveCustomerAccountRequest(request, message);
            } else if (stringUserInput.equals("deny")) {
                String message = Util.readLine("Please type in the reason for the denial:");
                accountRequestController.denyAccountRequest(request, message);
            }
        }while (!(stringUserInput.equals("approve") || stringUserInput.equals("deny")));
    }
    // approves or denies a bank account request, denial needs a accompanied message as to why it was denied
    public void approveDenyBankAccountRequest(BankAccountRequest request, AccountRequestController accountRequestController){
        String stringUserInput = "";
        do {
            stringUserInput = Util.readLine("Please type in: approve or deny");
            if (stringUserInput.equals("approve")) {
                //Util.readLine("Please type in the reason for the approval:"); do we need message for approval?
                String message = Util.readLine("Please type in the reason for the approval:");
                accountRequestController.approveBankAccountRequest(request, message);
            } else if (stringUserInput.equals("deny")) {
                String message = Util.readLine("Please type in the reason for the denial:");
                accountRequestController.approveBankAccountRequest(request, message);
                accountRequestController.denyAccountRequest(request, message);
            } else {
                System.out.println("please input a valid option (approve/deny)");
            }
        }while (!(stringUserInput.equals("approve") || stringUserInput.equals("deny")));
    }


}

