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
    Scene scene;
    Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        int width = 800;
        int height = 600;

        if (in_game) {
            this.scene = new Scene(RenderGame.render(this), width, height);
        }
        else {
            this.scene = new Scene(RenderSignMenu.render(this), width, height);
        }
        stage.setTitle("Monopoly");
        stage.setScene(scene);
        stage.show();
    }

    public void startGame() {
        in_game = true;
        System.out.println("Starting game");
        this.scene.setRoot(RenderGame.render(this));
    }

    public void endGame() {
        in_game = false;
        System.out.println("Ending game");
        this.scene.setRoot(RenderSignMenu.render(this));
    }

    public static void main(String[] args) {
        launch();
    }
}