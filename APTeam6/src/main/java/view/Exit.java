package view;

import controller.ProgramManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Exit extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Label text = new Label("Are you want to exit?");
        Button yes = new Button("yes");
        Button no = new Button("no");

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(text,yes,no);

        stage.setScene(new Scene(vBox,300,150));
        stage.show();

        yes.setOnAction(actionEvent -> {
            ProgramManager.getProgramManagerInstance().saveToFiles();
            System.exit(0);
        });

        no.setOnAction(actionEvent -> {
            stage.close();
        });

    }
}
