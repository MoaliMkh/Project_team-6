package controller;

import model.account.Account;
import model.account.Buyer;
import model.account.Manager;
import model.account.Seller;
import view.LoginMenuView;

public class LoginMenu {
    private static LoginMenu loginMenuInstance = null;

    public static LoginMenu getLoginMenuInstance() {
        if (loginMenuInstance == null)
            loginMenuInstance = new LoginMenu();
        return loginMenuInstance;
    }

    ///////////////////////////////////////////////////
    LoginMenuView view;

    public void start() {
        view = new LoginMenuView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.matches("login \\S+ \\S+")) {
                String[] splitCommand = command.split("\\s");
                login(splitCommand[1], splitCommand[2]);
            }
            else if (command.matches("create account (buyer|seller|manager) \\S+")) {
                String[] splitCommand = command.split("\\s");
                if (splitCommand[2].equals("manager"))
                    System.out.println("You can't make managers here. get away");
                else
                    register(splitCommand[3], splitCommand[2]);
            }
            else if (command.equals("logout")) {
                logout();
            }
            else if (command.equals("back")) {
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to LoginMenu by view");
            }
        }
    }

    public void login(String username, String password) {
        if (!ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(username)) {
            view.giveOutput("This username does not exist");
            return;
        }
        Account tempAccount = ProgramManager.getProgramManagerInstance().getAccountByUsername(username);
        if (!tempAccount.checkPassword(password)) {
            view.giveOutput("Wrong password");
            return;
        }
        ProgramManager.getProgramManagerInstance().loginSuccessful(tempAccount);
        view.giveOutput("Welcome " + username + ".");
    }

    public void register(String username, String role) {
        if (ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(username)) {
            view.giveOutput("This username is already occupied");
            return;
        }
        String[] userData = null;
        userData = view.getUserUsualData();

        if (role.equals("buyer")) {
            System.out.println("here now1");
            new Buyer(username, userData[4], userData[0], userData[1], userData[3], userData[2]);
        }
        else if (role.equals("seller")) {
            String company = view.getSellerCompany();
            new Seller(username, userData[4], userData[0], userData[1], userData[3], userData[2], company);
        }
        view.giveOutput("Registered successfully.");
    }

    public void logout() {
        ProgramManager.getProgramManagerInstance().logoutSuccessful();
    }
}
