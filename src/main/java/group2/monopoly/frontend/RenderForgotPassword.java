package group2.monopoly.frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RenderForgotPassword {

    private static final int field_width = 150;
    private static final int field_margin = 60;
    private static final int field_margin_margin = 10;

    public static Group render(HelloApplication app, int width, int height) {

        System.out.println("RenderForgotPassword");

        Group root = new Group();

        int identifierHeight = height / 3;
        Label identifierName = new Label("User Name or e-mail");
        identifierName.setLayoutX(width / 2 - field_width / 2);
        identifierName.setLayoutY(identifierHeight);

        TextField identifierInput = new TextField();
        identifierInput.setPrefWidth(field_width);
        identifierInput.setMaxWidth(field_width);
        identifierInput.setLayoutX(width / 2 - field_width / 2);
        identifierInput.setLayoutY(identifierHeight + field_margin / 2);

        Button signUp = new Button("Send mail!");
        signUp.setLayoutX(width / 2 - field_width / 2);
        signUp.setLayoutY(identifierHeight + field_margin_margin + field_margin + field_margin_margin + field_margin);
        signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    app.executeForgotPassword(identifierInput.getText());
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Send the password forgot mail if such user exists");
                    a.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                catch (RuntimeException e) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Could not send forgot mail");
                    a.show();
                }
                app.endGame();
            }
        });


        root.getChildren().add(identifierName);
        root.getChildren().add(identifierInput);


        root.getChildren().add(signUp);

        return root;
    }
}
