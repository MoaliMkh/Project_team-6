package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

public class LoginMenu_V {
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
        StringBuilder builder = new StringBuilder();
        for (String string : strings) {
            builder.append(string + "---");
        }
        //TODO: (message) userName password
    }

    public void registerManagerButtonAction(ActionEvent actionEvent) {

    }

    public void registerSellerButtonAction(ActionEvent actionEvent) {

    }

    //////////////////////////////////////

    private String[] getBuyerOrManagerInfo(){
        String[] strings = new String[4];
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Buyer info");
        inputDialog.getDialogPane().setStyle("-fx-background-color: #eeffff;");

        inputDialog.setHeaderText("Enter your first name");
        inputDialog.showAndWait();
        strings[0] = inputDialog.getResult();

        inputDialog.setHeaderText("Enter your last name");
        inputDialog.showAndWait();
        strings[1] = inputDialog.getResult();

        inputDialog.setHeaderText("Enter your phone Num");
        inputDialog.showAndWait();
        strings[2] = inputDialog.getResult();

        inputDialog.setHeaderText("Enter your email address");
        inputDialog.showAndWait();
        strings[3] = inputDialog.getResult();
        return strings;
    }

    private String[] getSellerInfo(){
        String[] strings = new String[5];
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Seller info");
        inputDialog.getDialogPane().setStyle("-fx-background-color: #ffffee;");

        inputDialog.setHeaderText("Enter your first name");
        inputDialog.showAndWait();
        strings[0] = inputDialog.getResult();

        inputDialog.setHeaderText("Enter your last name");
        inputDialog.showAndWait();
        strings[1] = inputDialog.getResult();

        inputDialog.setHeaderText("Enter your phone Num");
        inputDialog.showAndWait();
        strings[2] = inputDialog.getResult();

        inputDialog.setHeaderText("Enter your email address");
        inputDialog.showAndWait();
        strings[3] = inputDialog.getResult();

        inputDialog.setHeaderText("Enter your chompany name");
        inputDialog.showAndWait();
        strings[4] = inputDialog.getResult();
        return strings;
    }
}
