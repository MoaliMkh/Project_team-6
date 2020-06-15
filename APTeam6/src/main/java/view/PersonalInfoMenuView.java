package view;

import controller.ProgramManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.account.Account;
import model.account.Buyer;
import model.account.Manager;
import model.account.Seller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PersonalInfoMenuView extends Application {

    public PersonalInfoMenuView() {
        Account currentUser = ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUser();
        System.out.println("Your personal info is as follows:" +
                "\n\tUsername: " + currentUser.getUsername() +
                "\n\tFirst name: " + currentUser.getFirstName() +
                "\n\tLast name: " + currentUser.getLastName() +
                "\n\tEmail address: " + currentUser.getEmailAddress() +
                "\n\tTelephone num: " + currentUser.getPhoneNumber() +
                "\n\tRole: " + currentUser.getRole());
        if (currentUser.getRole() == 2) {
            System.out.println("\tCredit: " + ((Seller) currentUser).getCredit());
        } else if (currentUser.getRole() == 1) {
            System.out.println("\tCredit: " + ((Buyer) currentUser).getCredit());
        }
    }

    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.equals("help")) {
                showHelp();
            } else if (command.equals("back")) {
                return command;
            } else if (command.matches("edit (password|firstName|lastName|phoneNumber|email)")) {
                return command;
            } else {
                System.out.println("Invalid command");
            }
        }
    }

    private void showHelp() {
        System.out.println("List of commands:\n\tedit [password/firstName/lastName/phoneNumber/email]\n\tback");
    }

    public String getNewValueForField(String field) {
        System.out.println("Enter your new " + field);
        return Input.getInput();
    }

    public void manageUsers() {
        Stage stage = new Stage();
        Button viewUser = new Button("View user");
        Button deleteUser = new Button("Delete user");
        Button createManager = new Button("Create manager");
        Button back = new Button("Back");

        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(viewUser, deleteUser, createManager, back);

        Scene scene = new Scene(vBox, 250, 450);
        stage.setScene(scene);
        stage.show();

        viewUser.setOnAction(actionEvent -> {
            stage.close();
            viewUser();
        });

        deleteUser.setOnAction(actionEvent -> {
            stage.close();
            deleteUser();
        });

        createManager.setOnAction(actionEvent -> {
            stage.close();
            try {
                createManager();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            try {
                new Exit().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        back.setOnAction(actionEvent -> {
            stage.close();
            try {
                this.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void createManager() throws FileNotFoundException {
        Stage window = new Stage();
        window.setTitle("Create manager");
        window.getIcons().add(new Image(new FileInputStream("src/main/java/view/pictures/icon.png")));
        VBox pane = new VBox(11);
        pane.setAlignment(Pos.CENTER);

        Label usernameLabel = new Label("This username already exist!");
        Label usernameLabel2 = new Label("write a username here");
        usernameLabel.setTextFill(Color.RED);
        usernameLabel2.setTextFill(Color.RED);
        usernameLabel.setVisible(false);
        usernameLabel2.setVisible(false);
        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Username");

        Label passwordLabel = new Label("please fill this field ");
        passwordLabel.setTextFill(Color.RED);
        passwordLabel.setVisible(false);
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Label firstNameLabel = new Label("write a first name here");
        firstNameLabel.setTextFill(Color.RED);
        firstNameLabel.setVisible(false);
        TextField firstNameTextField = new TextField();
        firstNameTextField.setPromptText("FirstName");

        Label lastNameLabel = new Label("write a last name here");
        lastNameLabel.setTextFill(Color.RED);
        lastNameLabel.setVisible(false);
        TextField lastNameTextField = new TextField();
        lastNameTextField.setPromptText("LastName");

        Label emailAddressLabel = new Label("write an email here");
        emailAddressLabel.setTextFill(Color.RED);
        emailAddressLabel.setVisible(false);
        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Email");

        Label phoneNumberLabel = new Label("write a PhoneNumber");
        phoneNumberLabel.setTextFill(Color.RED);
        phoneNumberLabel.setVisible(false);
        TextField phoneNumberTextField = new TextField();
        phoneNumberTextField.setPromptText("e.g. 09123456789");

        Button create = new Button("Create manager");
        pane.getChildren().addAll(usernameLabel, usernameLabel2, usernameTextField, passwordLabel, passwordField, firstNameLabel, firstNameTextField, lastNameLabel, lastNameTextField, emailAddressLabel, emailTextField, phoneNumberLabel, phoneNumberTextField, create);
        window.setScene(new Scene(pane, 350, 550));
        window.show();

        window.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            try {
                new Exit().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        create.setOnAction(actionEvent -> {
            //check field data:
            usernameLabel2.setVisible(usernameTextField.getText().equals(""));
            passwordLabel.setVisible(passwordField.getText().equals(""));
            firstNameLabel.setVisible(firstNameTextField.getText().equals(""));
            lastNameLabel.setVisible(lastNameTextField.getText().equals(""));
            emailAddressLabel.setVisible(emailTextField.getText().equals(""));
            phoneNumberLabel.setVisible(phoneNumberTextField.getText().equals(""));
            phoneNumberLabel.setVisible(!phoneNumberTextField.getText().matches("[0-9]+"));
            //----------------

            if (!(usernameTextField.getText().equals("") || passwordField.getText().equals("") || firstNameTextField.getText().equals("") || lastNameTextField.getText().equals("") || emailTextField.getText().equals("") || phoneNumberTextField.getText().equals("") || !phoneNumberTextField.getText().matches("[0-9]+"))) {

                new Manager(usernameTextField.getText(), passwordField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(), phoneNumberTextField.getText());

                try {
                    new Alert().showAlert("Account created!", "Ok", 3);
                    window.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }


    public void deleteUser() {
        Stage stage = new Stage();
        TextField username = new TextField();
        username.setPromptText("Username");
        Button delete = new Button("Delete");
        Button back = new Button("Back");

        VBox vBox = new VBox(7);
        vBox.setAlignment(Pos.BOTTOM_CENTER);
        vBox.getChildren().addAll(username, delete, back);

        Scene scene = new Scene(vBox, 300, 450);
        stage.setScene(scene);
        stage.show();

        delete.setOnAction(actionEvent -> {
            if (username.getText().equals("")) {
                try {
                    new Alert().showAlert("Please fill username text field", "Ok", 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                if (ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(username.getText())) {
                    stage.close();
                    ProgramManager.getProgramManagerInstance().deleteAccount(username.getText());
                    try {
                        new Alert().showAlert("Deleted!", "Ok", 3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        new Alert().showAlert("There is no user with this username. Please select another", "Ok", 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            try {
                new Exit().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        back.setOnAction(actionEvent -> {
            stage.close();
            manageUsers();
        });
    }

    public void viewUser() {
        Stage stage = new Stage();
        TextField username = new TextField();
        username.setPromptText("Username");
        Button view = new Button("view");
        Button back = new Button("Back");

        VBox vBox = new VBox(6);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(username, view, back);

        Scene scene = new Scene(vBox, 250, 450);
        stage.setScene(scene);
        stage.show();

        view.setOnAction(actionEvent -> {
            if (username.getText().equals("")) {
                try {
                    new Alert().showAlert("Please fill username text field", "Ok", 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                if (ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(username.getText())) {
                    stage.close();
                    try {
                        viewUserWithUsername(username.getText());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        new Alert().showAlert("There is no user with this username. Please search another", "Ok", 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            try {
                new Exit().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        back.setOnAction(actionEvent -> {
            stage.close();
            manageUsers();
        });
    }

    public void viewUserWithUsername(String username) throws FileNotFoundException {
        Stage stage = new Stage();
        stage.setTitle(username + " Information");
        stage.getIcons().add(new Image(new FileInputStream("src/main/java/view/pictures/icon.png")));
        VBox pane = new VBox();
        pane.setAlignment(Pos.CENTER);

        Account user = ProgramManager.getProgramManagerInstance().getAccountByUsername(username);

        Label credit = new Label("Credit:");
        Label credit2 = new Label("");
        if (2 == user.getRole()) {
            Seller seller = (Seller) user;
            credit2.setText("  $ " + seller.getCredit());
        } else if (user.getRole() == 1) {
            Buyer buyer = (Buyer) user;
            credit2.setText("  $ " + buyer.getCredit());
        } else {
            credit.setVisible(false);
            credit2.setVisible(false);
        }

        Label company = new Label("Company:");
        Label company2 = new Label();
        if (user.getRole() == 2) {
            Seller seller = (Seller) user;
            company2.setText(seller.getCompanyName());
        } else {
            company.setVisible(false);
            company2.setVisible(false);
        }


        Label role = new Label("role is : ");
        Label role2 = new Label("");
        if (user.getRole() == 1) {
            role2.setText("Buyer");
        } else if (user.getRole() == 2) {
            role2.setText("Seller");
        } else {
            role2.setText("Manager");
        }


        Label usernameLabel = new Label("Username");
        TextField usernameTextField = new TextField();
        usernameTextField.setEditable(false);
        usernameTextField.setText(user.getUsername());

        Label password = new Label("Password");
        TextField passwordField = new TextField();
        passwordField.setEditable(false);
        passwordField.setText(user.getPassword());

        Label firstName = new Label("FirstName");
        TextField firstNameTextField = new TextField();
        firstNameTextField.setEditable(false);
        firstNameTextField.setText(user.getFirstName());

        Label lastName = new Label("LastName");
        TextField lastNameTextField = new TextField();
        lastNameTextField.setEditable(false);
        lastNameTextField.setText(user.getLastName());

        Label email = new Label("Email");
        TextField emailTextField = new TextField();
        emailTextField.setEditable(false);
        emailTextField.setText(user.getEmailAddress());

        Label phone = new Label("PhoneNumber");
        TextField phoneNumberTextField = new TextField();
        phoneNumberTextField.setEditable(false);
        phoneNumberTextField.setText(user.getPhoneNumber());

        Button back = new Button("Back");


        if (user.getRole() == 1) {
            pane.getChildren().addAll(role, role2, credit, credit2, usernameLabel, usernameTextField,
                    password, passwordField, firstName, firstNameTextField, lastName,
                    lastNameTextField, email, emailTextField,
                    phone, phoneNumberTextField, back);
        } else if (user.getRole() == 2) {
            pane.getChildren().addAll(role, role2, usernameLabel, usernameTextField,
                    password, passwordField, firstName, firstNameTextField, lastName,
                    lastNameTextField, company, company2, email, emailTextField,
                    phone, phoneNumberTextField, back);
        } else if (user.getRole() == 3) {
            pane.getChildren().addAll(role, role2, credit, credit2, usernameLabel, usernameTextField,
                    password, passwordField, firstName, firstNameTextField, lastName,
                    lastNameTextField, email, emailTextField, phone,
                    phoneNumberTextField, back);
        }


        stage.setScene(new Scene(pane, 350, 600));
        stage.show();

        back.setOnAction(actionEvent -> {
            stage.close();
            try {
                this.manageUsers();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            try {
                new Exit().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Personal Information");
        stage.getIcons().add(new Image(new FileInputStream("src/main/java/view/pictures/icon.png")));
        VBox pane = new VBox();
        pane.setAlignment(Pos.CENTER);

        Account currentlyLoggedInUser = ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUser();

        Label credit = new Label("Credit");
        Label credit2 = new Label();
        if (currentlyLoggedInUser.getRole() == 2) {
            Seller seller = (Seller) currentlyLoggedInUser;
            credit2.setText(" $ " + seller.getCredit());
        } else if (currentlyLoggedInUser.getRole() == 1) {
            Buyer buyer = (Buyer) currentlyLoggedInUser;
            credit2.setText(" $ " + buyer.getCredit());
        } else {
            credit.setVisible(false);
            credit2.setVisible(false);
        }

        Label company = new Label("Company");
        Label company2 = new Label();
        if (currentlyLoggedInUser.getRole() == 2) {
            Seller seller = (Seller) currentlyLoggedInUser;
            company2.setText(seller.getCompanyName());
        } else {
            company.setVisible(false);
            company2.setVisible(false);
        }


        Label role = new Label("your role is : ");
        Label role2 = new Label();
        if (currentlyLoggedInUser.getRole() == 1) {
            role2.setText("Buyer");
        } else if (currentlyLoggedInUser.getRole() == 2) {
            role2.setText("Seller");
        } else {
            role2.setText("Manager");
        }


        Label username = new Label("Username");
        Label usernameLabel = new Label("This username already exist!");
        Label usernameLabel2 = new Label("write your username here");
        usernameLabel.setVisible(false);
        usernameLabel2.setVisible(false);
        TextField usernameTextField = new TextField();
        usernameTextField.setEditable(false);
        usernameTextField.setPromptText("Username");
        usernameTextField.setText(currentlyLoggedInUser.getUsername());

        Label password = new Label("Password");
        Label passwordLabel = new Label("please fill this field");
        passwordLabel.setVisible(false);
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setText(currentlyLoggedInUser.getPassword());

        Label firstName = new Label("FirstName");
        Label firstNameLabel = new Label("write your first name here");
        firstNameLabel.setVisible(false);
        TextField firstNameTextField = new TextField();
        firstNameTextField.setPromptText("FirstName");
        firstNameTextField.setText(currentlyLoggedInUser.getFirstName());

        Label lastName = new Label("LastName");
        Label lastNameLabel = new Label("write your last name here");
        lastNameLabel.setVisible(false);
        TextField lastNameTextField = new TextField();
        lastNameTextField.setPromptText("LastName");
        lastNameTextField.setText(currentlyLoggedInUser.getLastName());

        Label email = new Label("Email");
        Label emailAddressLabel = new Label("write your email here");
        emailAddressLabel.setVisible(false);
        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Email");
        emailTextField.setText(currentlyLoggedInUser.getEmailAddress());

        Label phone = new Label("PhoneNumber");
        Label phoneNumberLabel = new Label("write a PhoneNumber");
        phoneNumberLabel.setVisible(false);
        TextField phoneNumberTextField = new TextField();
        phoneNumberTextField.setPromptText("e.g. 09123456789");
        phoneNumberTextField.setText(currentlyLoggedInUser.getPhoneNumber());

        Button change = new Button("Change Information");
        Button close = new Button("Back");

        Button openCart = new Button("Go to cart");
        Button openBuyHistory = new Button("Go to buy history");
        Button showDiscountCode = new Button("Show my discount code");

        if (currentlyLoggedInUser.getRole() != 1) {
            openCart.setVisible(false);
            openBuyHistory.setVisible(false);
            showDiscountCode.setVisible(false);
        }

        openCart.setOnAction(actionEvent -> {
            //TODO
        });

        openBuyHistory.setOnAction(actionEvent -> {
            //TODO
        });

        showDiscountCode.setOnAction(actionEvent -> {
            //TODO
        });


        Button manageUsers = new Button("Manage users");
        Button manageAllProduct = new Button("Manage all product");
        Button createDiscountCode = new Button("Create discount code");
        Button viewDiscountCode = new Button("View Discount code");
        Button manageRequest = new Button("Manage request");
        Button manageCategories = new Button("Manage categories");

        if (currentlyLoggedInUser.getRole() != 3) {
            manageAllProduct.setVisible(false);
            manageRequest.setVisible(false);
            manageUsers.setVisible(false);
            createDiscountCode.setVisible(false);
            viewDiscountCode.setVisible(false);
            manageCategories.setVisible(false);
        }

        manageUsers.setOnAction(actionEvent -> {
            manageUsers();
            stage.close();
        });

        manageAllProduct.setOnAction(actionEvent -> {
            //TODO
        });

        createDiscountCode.setOnAction(actionEvent -> {
            //TODO
        });

        viewDiscountCode.setOnAction(actionEvent -> {
            //TODO
        });

        manageRequest.setOnAction(actionEvent -> {
            //TODO
        });

        manageCategories.setOnAction(actionEvent -> {
            //TODO
        });


        Button viewCompanyInfo = new Button("View company information");
        Button viewSalesHistory = new Button("View sales history");
        Button manageProducts = new Button("Manage products");
        Button addProduct = new Button("Add product");
        Button removeProduct = new Button("Remove Product");
        Button showCategories = new Button("Show categories");
        Button viewOffs = new Button("View offs");
        Button viewBalance = new Button("View balance");

        if (currentlyLoggedInUser.getRole() != 2) {
            viewBalance.setVisible(false);
            viewCompanyInfo.setVisible(false);
            viewSalesHistory.setVisible(false);
            manageProducts.setVisible(false);
            addProduct.setVisible(false);
            removeProduct.setVisible(false);
            showCategories.setVisible(false);
            viewOffs.setVisible(false);
        }

        viewBalance.setOnAction(actionEvent -> {
            //TODO
        });

        viewCompanyInfo.setOnAction(actionEvent -> {
            //TODO
        });

        viewSalesHistory.setOnAction(actionEvent -> {
            //TODO
        });

        manageProducts.setOnAction(actionEvent -> {
            //TODO
        });

        addProduct.setOnAction(actionEvent -> {
            //TODO
        });

        removeProduct.setOnAction(actionEvent -> {
            //TODO
        });

        showCategories.setOnAction(actionEvent -> {
            //TODO
        });

        viewOffs.setOnAction(actionEvent -> {
            //TODO
        });

        if (currentlyLoggedInUser.getRole() == 1) {
            pane.getChildren().addAll(role, role2, credit, credit2, username, usernameLabel, usernameLabel2, usernameTextField,
                    password, passwordLabel, passwordField, firstName, firstNameLabel, firstNameTextField, lastName,
                    lastNameLabel, lastNameTextField, email, emailAddressLabel, emailTextField,
                    phone, phoneNumberLabel, phoneNumberTextField, openCart, openBuyHistory, showDiscountCode, change, close);
        } else if (currentlyLoggedInUser.getRole() == 2) {
            pane.getChildren().addAll(role, role2, username, usernameLabel, usernameLabel2, usernameTextField,
                    password, passwordLabel, passwordField, firstName, firstNameLabel, firstNameTextField, lastName,
                    lastNameLabel, lastNameTextField, company, company2, email, emailAddressLabel, emailTextField,
                    phone, phoneNumberLabel, phoneNumberTextField, viewBalance, viewCompanyInfo, viewSalesHistory,
                    manageProducts, addProduct, removeProduct, showCategories, viewOffs, change, close);
        } else if (currentlyLoggedInUser.getRole() == 3) {
            pane.getChildren().addAll(role, role2, credit, credit2, username, usernameLabel, usernameLabel2, usernameTextField,
                    password, passwordLabel, passwordField, firstName, firstNameLabel, firstNameTextField, lastName,
                    lastNameLabel, lastNameTextField, email, emailAddressLabel, emailTextField, phone, phoneNumberLabel,
                    phoneNumberTextField, manageAllProduct, manageRequest, manageUsers, createDiscountCode, viewDiscountCode,
                    manageCategories, change, close);
        }


        stage.setScene(new Scene(pane, 400, 750));
        stage.show();

        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            try {
                new Exit().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        close.setOnAction(actionEvent -> {
            stage.close();
            try {
                MainScreenView.getMainScreenViewInstance().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        change.setOnAction(actionEvent -> {

            //check field data:
            //usernameLabel2.setVisible(usernameTextField.getText().equals(""));
            //usernameLabel.setVisible(ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(usernameTextField.getText()));
            passwordLabel.setVisible(passwordField.getText().equals(""));
            firstNameLabel.setVisible(firstNameTextField.getText().equals(""));
            lastNameLabel.setVisible(lastNameTextField.getText().equals(""));
            emailAddressLabel.setVisible(emailTextField.getText().equals(""));
            phoneNumberLabel.setVisible(phoneNumberTextField.getText().equals(""));
            phoneNumberLabel.setVisible(!phoneNumberTextField.getText().matches("[0-9]+"));
            //----------------

            if (!(usernameTextField.getText().equals("") || passwordField.getText().equals("") || firstNameTextField.getText().equals("") || lastNameTextField.getText().equals("") || emailTextField.getText().equals("") || phoneNumberTextField.getText().equals("") || !phoneNumberTextField.getText().matches("[0-9]+"))) {

                if (ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(usernameTextField.getText())) {

                    currentlyLoggedInUser.setPassword(passwordField.getText());
                    currentlyLoggedInUser.setEmailAddress(emailTextField.getText());
                    currentlyLoggedInUser.setFirstName(firstNameTextField.getText());
                    currentlyLoggedInUser.setLastName(lastNameTextField.getText());
                    currentlyLoggedInUser.setPhoneNumber(phoneNumberTextField.getText());

                    try {
                        new Alert().showAlert("Information Changed", "Ok", 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.err.println("!!");
                }


            }

        });
    }
}
