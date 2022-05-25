package group2.monopoly.frontend;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RenderSignUp {

    private static final int field_width = 150;
    private static final int field_margin = 60;
    private static final int field_margin_margin = 10;

    public static Group render(HelloApplication app, int width, int height) {

        System.out.println("RenderSignUp");

        Group root = new Group();

        int userNameHeight = height / 3;
        Label userName = new Label("User Name");
        userName.setLayoutX(width / 2 - field_width / 2);
        userName.setLayoutY(userNameHeight);

        TextField userNameInput = new TextField();
        userNameInput.setPrefWidth(field_width);
        userNameInput.setMaxWidth(field_width);
        userNameInput.setLayoutX(width / 2 - field_width / 2);
        userNameInput.setLayoutY(userNameHeight + field_margin / 2);

        int passwordHeight = userNameHeight + field_margin_margin + field_margin;
        Label password = new Label("Password");
        password.setLayoutX(width / 2 - field_width / 2);
        password.setLayoutY(passwordHeight);

        TextField passwordInput = new TextField();
        passwordInput.setPrefWidth(field_width);
        passwordInput.setMaxWidth(field_width);
        passwordInput.setLayoutX(width / 2 - field_width / 2);
        passwordInput.setLayoutY(passwordHeight + field_margin / 2);

        int passwordHeight2 = passwordHeight + field_margin_margin + field_margin;
        Label password2 = new Label("Password Confirmation");
        password2.setLayoutX(width / 2 - field_width / 2);
        password2.setLayoutY(passwordHeight2);

        TextField passwordInput2 = new TextField();
        passwordInput2.setPrefWidth(field_width);
        passwordInput2.setMaxWidth(field_width);
        passwordInput2.setLayoutX(width / 2 - field_width / 2);
        passwordInput2.setLayoutY(passwordHeight2 + field_margin / 2);

        int emailHeight = passwordHeight2 + field_margin_margin + field_margin;
        Label email = new Label("Email");
        email.setLayoutX(width / 2 - field_width / 2);
        email.setLayoutY(emailHeight);

        TextField emailInput = new TextField();
        emailInput.setPrefWidth(field_width);
        emailInput.setMaxWidth(field_width);
        emailInput.setLayoutX(width / 2 - field_width / 2);
        emailInput.setLayoutY(emailHeight + field_margin / 2);

        Button signUp = new Button("Sign Up");
        signUp.setLayoutX(width / 2 - field_width / 2);
        signUp.setLayoutY(emailHeight + field_margin_margin + field_margin + field_margin_margin + field_margin);
        signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    app.executeSignUp(userNameInput.getText(), passwordInput.getText(), passwordInput2.getText(), emailInput.getText());
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Successfully created user!");
                    a.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                catch (RuntimeException e) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Could not create user");
                    a.show();
                }
                app.endGame();
            }
        });


        root.getChildren().add(userName);
        root.getChildren().add(userNameInput);

        root.getChildren().add(password);
        root.getChildren().add(passwordInput);

        root.getChildren().add(password2);
        root.getChildren().add(passwordInput2);

        root.getChildren().add(email);
        root.getChildren().add(emailInput);

        root.getChildren().add(signUp);

        return root;
    }
}
