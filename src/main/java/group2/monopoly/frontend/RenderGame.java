package group2.monopoly.frontend;

import group2.monopoly.frontend.Player.Player;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class RenderGame {

    public static Group render(HelloApplication app) {
        int grid_count = 7;
        int width = 800;
        int height = 600;
        List<List<String>> places = new ArrayList<>();
        // income tax random
        // railroad/ferry 4 random
        // 8 properties random

        // mock
        places.add(new ArrayList<String>() {
            {
                add("Start point"); // Property type
                add(""); // property property 1
            }
        });
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

        BoardRectangles board_rectangles = new BoardRectangles(width, height, places, grid_count);
        board_rectangles.createRectangles();
        System.out.println("pwd: " + System.getProperty("user.dir"));
        Player player = new Player("alp", 1500, "file:src/main/java/group2/monopoly/frontend/pawn_ship.png", width, height, grid_count);
        Player player2 = new Player("deniz", 1500, "file:src/main/java/group2/monopoly/frontend/pawn_shoe.png", width, height, grid_count);
        List<Player> players = new ArrayList<>();
        players.add(player);
        players.add(player2);
        final int[] pawn2_pose = {0};
        Button button = new Button("Test");
        board_rectangles.boardedToRect(0);
        // when clicked to button, call pawn2.transition(pawn2_pose+1)
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player2.getPawn().transition(pawn2_pose[0] + 1);
                board_rectangles.boardedToRect(pawn2_pose[0] + 1);
                board_rectangles.unBoardedToRect(pawn2_pose[0]);
                pawn2_pose[0]++;
            }
        });

        Button exit_button = new Button("Exit");

        exit_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                app.endGame();
            }
        });
        exit_button.setLayoutX(0);
        exit_button.setLayoutY(50);

        Group root = new Group();
        root.getChildren().addAll(board_rectangles.getRects());
        root.getChildren().add(player.getPawn().draw());
        root.getChildren().add(player2.getPawn().draw());
        root.getChildren().add(button);
        root.getChildren().add(exit_button);

        // add player's special info
        root.getChildren().add(player2.getMoneyDisplay());
        root.getChildren().add(player2.getPropertiesDisplay());


        return root;
    }


}