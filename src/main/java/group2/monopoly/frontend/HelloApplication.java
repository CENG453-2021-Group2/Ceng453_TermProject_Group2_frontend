package group2.monopoly.frontend;

import group2.monopoly.frontend.Player.DisplayMoney;
import group2.monopoly.frontend.Player.Player;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import group2.monopoly.frontend.Pawn.Pawn;

public class HelloApplication extends Application {
    private final int grid_count = 7;
    private boolean in_game = false;
    private boolean in_signup = false;
    Scene scene;
    Stage stage;
    private final int width = 800;
    private final int height = 600;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        if (in_game) {
            this.scene = new Scene(RenderGame.render(this), width, height);
        }
        else if (in_signup) {
            this.scene = new Scene(RenderSignUp.render(this, width, height), width, height);
        }
        else {
            this.scene = new Scene(RenderSignMenu.render(this, width, height), width, height);
        }
        stage.setTitle("Monopoly");
        stage.setScene(scene);
        stage.show();
    }

    public void startGame() {
        in_game = true;
        in_signup = false;
        System.out.println("Starting game");
        this.scene.setRoot(RenderGame.render(this));
    }

    public void endGame() {
        in_game = false;
        in_signup = true;
        System.out.println("Ending game");
        this.scene.setRoot(RenderSignMenu.render(this, width, height));
    }

    public void startSignUp() {
        in_game = false;
        in_signup = true;
        System.out.println("Starting sign up");
        this.scene.setRoot(RenderSignUp.render(this, width, height));
    }

    public static void main(String[] args) {
        launch();
    }
}