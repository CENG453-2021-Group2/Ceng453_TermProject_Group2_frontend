package group2.monopoly.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        int width = 800;
        int height = 600;
        BoardRectangles board_rectangles = new BoardRectangles(width, height);
        board_rectangles.CreateRectangles();
        Scene scene = new Scene(board_rectangles.getRects(), width, height);
        stage.setTitle("Monopoly");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}