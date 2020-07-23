package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

public class LoginMenu_V extends GeneralController_V{
    public TextField userNameText;
    public TextField passwordText;
    public Button loginButton;
    public Button logoutButton;
    public Button registerManagerButton;

    public void backButtonAction(ActionEvent actionEvent) {
        //TODO: (message) -
    }

    public void logoutButtonAction(ActionEvent actionEvent) {
        //TODO: (message) -
    }

    public void loginButtonAction(ActionEvent actionEvent) {
        //TODO: (message) userName password
    }

    public void registerBuyerButtonAction(ActionEvent actionEvent) {
        String[] strings = getBuyerOrManagerInfo();
        StringBuilder builder = new StringBuilder(userNameText.getText() + "---" + passwordText.getText());
        for (String string : strings) {
            builder.append("---").append(string);
        }
        //TODO: (message) buyer builder.toString();
    }

    public void registerManagerButtonAction(ActionEvent actionEvent) {
        String[] strings = getBuyerOrManagerInfo();
        StringBuilder builder = new StringBuilder(userNameText.getText() + "---" + passwordText.getText());
        for (String string : strings) {
            builder.append("---").append(string);
        }
        //TODO: (message) manager builder.toString();
    }

    public void registerSellerButtonAction(ActionEvent actionEvent) {
        String[] strings = getSellerInfo();
        StringBuilder builder = new StringBuilder(userNameText.getText() + "---" + passwordText.getText());
        for (String string : strings) {
            builder.append("---").append(string);
        }
        //TODO: (message) seller builder.toString();
    }

    //////////////////////////////////////

    private String[] getBuyerOrManagerInfo(){
        String[] strings = new String[4];
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Buyer info");
        inputDialog.getDialogPane().setStyle("-fx-background-color: #eeffff;");

        inputDialog.setHeaderText("Enter your first name");
        do {
            inputDialog.showAndWait();
            strings[0] = inputDialog.getResult();
        }while (!strings[0].matches("[a-zA-Z0-9]+"));

        inputDialog.setHeaderText("Enter your last name");
        do {
            inputDialog.showAndWait();
            strings[1] = inputDialog.getResult();
        }while (!strings[1].matches("[a-zA-Z0-9]+"));

        inputDialog.setHeaderText("Enter your phone Num");
        do {
            inputDialog.showAndWait();
            strings[2] = inputDialog.getResult();
        }while (!strings[2].matches("[0-9]+"));

        inputDialog.setHeaderText("Enter your email address");
        do {
            inputDialog.showAndWait();
            strings[3] = inputDialog.getResult();
        }while (!strings[3].matches("[a-zA-Z0-9@.]+"));

        return strings;
    }

    private String[] getSellerInfo(){
        String[] strings = new String[5];
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Seller info");
        inputDialog.getDialogPane().setStyle("-fx-background-color: #ffffee;");

        inputDialog.setHeaderText("Enter your first name");
        do {
            inputDialog.showAndWait();
            strings[0] = inputDialog.getResult();
        }while (!strings[0].matches("[a-zA-Z0-9]+"));

        inputDialog.setHeaderText("Enter your last name");
        do {
            inputDialog.showAndWait();
            strings[1] = inputDialog.getResult();
        }while (!strings[1].matches("[a-zA-Z0-9]+"));

        inputDialog.setHeaderText("Enter your phone Num");
        do {
            inputDialog.showAndWait();
            strings[2] = inputDialog.getResult();
        }while (!strings[2].matches("[0-9]+"));

        inputDialog.setHeaderText("Enter your email address");
        do {
            inputDialog.showAndWait();
            strings[3] = inputDialog.getResult();
        }while (!strings[3].matches("[a-zA-Z0-9@.]+"));

        inputDialog.setHeaderText("Enter your chompany name");
        do {
            inputDialog.showAndWait();
            strings[4] = inputDialog.getResult();
        }while (!strings[4].matches("[a-zA-Z0-9]+"));

        return strings;
    }
}