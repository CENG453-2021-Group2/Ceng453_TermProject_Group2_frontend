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

public class RenderSignMenu {

    private static final int field_width = 150;
    private static final int field_margin = 60;
    private static final int field_margin_margin = 10;

    public static Group render(HelloApplication app, int width, int height) {

        System.out.println("RenderSignMenu.render");

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

        Button signIn = new Button("Sign In");
        signIn.setLayoutX(width / 2 - field_width / 2);
        signIn.setLayoutY(passwordHeight + field_margin_margin + field_margin);
        signIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    app.executeSignIn(userNameInput.getText(), passwordInput.getText());
                } catch (IOException e) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Could not sign in to user");
                    a.show();
                    return;
                }
                app.startGame();;

            }
        });

        Button signUp = new Button("Sign Up");
        signUp.setLayoutX(width / 2 - field_width / 2);
        signUp.setLayoutY(passwordHeight + field_margin_margin + field_margin + field_margin);
        signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                app.startSignUp();
            }
        });

        Button forgotPassword = new Button("Forgot Password");
        forgotPassword.setLayoutX(width / 2 - field_width / 2);
        forgotPassword.setLayoutY(passwordHeight + field_margin_margin + field_margin + field_margin + field_margin);
        forgotPassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                app.startForgotPassword();
            }
        });


        root.getChildren().add(userName);
        root.getChildren().add(userNameInput);

        root.getChildren().add(password);
        root.getChildren().add(passwordInput);

        root.getChildren().add(signIn);
        root.getChildren().add(signUp);
        root.getChildren().add(forgotPassword);

        return root;
    }
}
