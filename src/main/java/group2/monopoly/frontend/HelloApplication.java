package group2.monopoly.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        int width = 800;
        int height = 600;
        List<String> places = new ArrayList<>();
        // income tax random
        // railroad/ferry 4 random
        // 8 properties random

        // mock
        places.add("Jail/Just \nvisiting"); // top left
        places.add("Income tax");
        places.add("Railroad/\nferry");
        places.add("Railroad/\nferry");
        places.add("Start point"); // bottom left
        places.add("Railroad/\nferry");
        places.add("Railroad/\nferry");
        places.add("Property\nx$");
        places.add("Property\nx$");
        places.add("Property\nx$");
        places.add("Property\nx$");
        places.add("Property\nx$");
        places.add("Property\nx$");
        places.add("Property\nx$");
        places.add("Property\nx$");
        places.add("Go to jail"); // bottom right


        BoardRectangles board_rectangles = new BoardRectangles(width, height, places);
        board_rectangles.createRectangles();
        Scene scene = new Scene(board_rectangles.getRects(), width, height);
        stage.setTitle("Monopoly");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}