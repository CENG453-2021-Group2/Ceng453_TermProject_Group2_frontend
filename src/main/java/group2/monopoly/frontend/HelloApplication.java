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
        List<List<String>> places = new ArrayList<>();
        // income tax random
        // railroad/ferry 4 random
        // 8 properties random

        // mock
        places.add(new ArrayList<String>() {
            {
                add("Jail/Just visiting"); // Property type
                add(""); // property property 1
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Income tax"); // Property type
                add("50"); // property property 1, income tax amount
            }
        });

        places.add(new ArrayList<String>() {
            {
                add("Railroad/Ferry"); // Property type
                add(""); // property property 1
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Railroad/Ferry"); // Property type
                add(""); // property property 1
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Start point"); // Property type
                add(""); // property property 1
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Railroad/Ferry"); // Property type
                add(""); // property property 1
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Railroad/Ferry"); // Property type
                add(""); // property property 1
            }
        });

        places.add(new ArrayList<String>() {
            {
                add("Property"); // Property type
                add("100"); // property property 1 / cost of property
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Property"); // Property type
                add("100"); // property property 1 / cost of property
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Property"); // Property type
                add("100"); // property property 1 / cost of property
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Property"); // Property type
                add("100"); // property property 1 / cost of property
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Property"); // Property type
                add("100"); // property property 1 / cost of property
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Property"); // Property type
                add("100"); // property property 1 / cost of property
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Property"); // Property type
                add("100"); // property property 1 / cost of property
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Property"); // Property type
                add("100"); // property property 1 / cost of property
            }
        });

        places.add(new ArrayList<String>() {
            {
                add("Go to jail"); // Property type
                add(""); // property property 1
            }
        });


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