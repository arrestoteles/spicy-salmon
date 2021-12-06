package com.salmon.spicysalmon;

import com.salmon.spicysalmon.controllers.CustomerController;
import com.salmon.spicysalmon.controllers.EmployeeController;
import com.salmon.spicysalmon.models.Customer;
import com.salmon.spicysalmon.models.Menu;

public class LoginMenu {
    String CUSTOMER_HEADING = "Customer Menu: Please choose a valid option.";
    String[] CUSTOMER_OPTIONS = {
            "Log out",
            "View Bank Accounts",
            "Apply for new Bank Account"
    };


    private String[] getLoginInfo(){
        System.out.println("Login.");
        String SSN = Util.readLine("Social Security Number: ");
        String password = Util.readLine("Password: ");
        return new String[]{SSN, password};
    }

    public void loginCustomer(){
        String[] loginInfo = getLoginInfo();
        String SSN = loginInfo[0];
        String password = loginInfo[1];
        CustomerController customerController = new CustomerController();
        Customer loggedInCustomer = customerController.getCustomer(SSN);
        if(loggedInCustomer != null && loggedInCustomer.verifyPassword(password)){
            showCustomerMenu();
        } else{
            System.out.println("Username or password incorrect.");
        }
    }

    private void showCustomerMenu(){
        Menu customerMenu = new Menu(CUSTOMER_HEADING, CUSTOMER_OPTIONS);
        int userInput = 0;
        do{
            System.out.print(customerMenu);
            userInput = customerMenu.getValidOption();
            switch (userInput) {
                case 1 -> System.out.println("View Bank Accounts");
                case 2 -> System.out.println("apply new bank account");
                case 0, default -> {}
            }
        }while(userInput != 0);
    }

    public void loginEmployee(){
        String[] loginInfo = getLoginInfo();
    }
}
