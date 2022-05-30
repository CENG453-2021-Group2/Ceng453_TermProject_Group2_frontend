package group2.monopoly.frontend;

import group2.monopoly.frontend.Player.Player;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.JSONArray;
import org.json.JSONObject;
import javafx.event.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class RenderGame {

    public static Group render(HelloApplication app, JSONObject gameTableConfiguration, int width, int height) {
        int grid_count = 7;
        List<List<String>> places = new ArrayList<>();
        final boolean[] first_step = {true};
        // income tax random
        // railroad/ferry 4 random
        // 8 properties random

        int incomeTaxIndex = gameTableConfiguration.getInt("incomeTaxIndex");
        JSONArray propertyIndices = gameTableConfiguration.getJSONArray("propertyIndices");
        JSONArray portIndices = gameTableConfiguration.getJSONArray("portIndices");
        List<Integer> propertyIndicesArr = new ArrayList<Integer>();
        for (int i = 0; i < propertyIndices.length(); i++) {
            propertyIndicesArr.add(propertyIndices.getInt(i));
        }
        List<Integer> portIndicesArr = new ArrayList<Integer>();
        for (int i = 0; i < portIndices.length(); i++) {
            portIndicesArr.add(portIndices.getInt(i));
        }

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
                add("Jail/Just visiting"); // Property type
                add("-100"); // property property 1
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Property"); // Property type
                add("-100$"); // property property 1
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
                add("Go to jail"); // Property type
                add("100"); // property property 1 / cost of property
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Go to jail"); // Property type
                add("100"); // property property 1 / cost of property
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Go to jail"); // Property type
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

        // now arrange them!
        for (int i = 0; i < portIndicesArr.size(); i++) {
            places.set(16 - portIndicesArr.get(i), new ArrayList<String>() {
                {
                    add("Railroad/Ferry");
                    add("100$");
                }
            });
        }

        int max_price = 400;
        // now arrange them!
        for (int i = 0; i < propertyIndicesArr.size(); i++) {
            int finalI = i;
            places.set(16 - propertyIndicesArr.get(i), new ArrayList<String>() {
                {
                    add("Property");
                    add(String.valueOf((400-50* finalI)));
                }
            });
        }

        places.set(16 - incomeTaxIndex, new ArrayList<String>() {
            {
                add("Income tax");
                add("");
            }
        });

        // who plays text
        Text whoPlaysText = new Text("Human plays!");
        whoPlaysText.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: red;");
        whoPlaysText.setX(width/2-100);
        whoPlaysText.setY(50);

        BoardRectangles board_rectangles = new BoardRectangles(width, height, places, grid_count);
        board_rectangles.createRectangles();
        System.out.println("pwd: " + System.getProperty("user.dir"));
        Player player = new Player("alp", 1500, "file:src/main/java/group2/monopoly/frontend/pawn_ship.png", width, height, grid_count);
        Player player2 = new Player("deniz", 1500, "file:src/main/java/group2/monopoly/frontend/pawn_shoe.png", width, height, grid_count);
        final List<Player>[] players = new List[]{new ArrayList<>()};
        players[0].add(player);
        players[0].add(player2);
        final int[] pawn2_pose = {0};
        int[] pawn1_pose = {0};
        Button button = new Button("Step");
        board_rectangles.boardedToRect(0);

        Text buyText = new Text("Do you want to buy the property?");
        buyText.setStyle("-fx-font-size: 14px;");
        buyText.setLayoutX(60);
        buyText.setLayoutY(15);
        CheckBox checkBox = new CheckBox();
        checkBox.setLayoutX(100);
        checkBox.setLayoutY(30);

        // when clicked to button, call pawn2.transition(pawn2_pose+1)
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String gameState;
                if (first_step[0]) {
                    try {
                        gameState = app.stepInGame(false);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        System.out.println("Buy box is");
                        System.out.println(checkBox.isSelected());
                        gameState = app.stepInGame(checkBox.isSelected());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                first_step[0] = false;
                checkBox.setSelected(false);
                JSONObject gameStateJSON = new JSONObject(gameState);
                JSONArray players_json = gameStateJSON.getJSONArray("players");
                System.out.println("players json is");
                System.out.println(players_json);

                // First update the player's purchase, if any
                player2.updateProperties((JSONArray) players_json.getJSONObject(0).get("ownedPurchasables"));
                int new_location_for_robot = players_json.getJSONObject(1).getInt("location");
                boolean playerPlaceChange = false;
                if (new_location_for_robot != pawn1_pose[0]) {
                    playerPlaceChange = true;
                }

                // set player money
                player2.setMoney(players_json.getJSONObject(0).getInt("money"));


                // Then, continue with the robot's move
                SequentialTransition playerTransition = new SequentialTransition();
                int j = pawn1_pose[0];
                while (j != new_location_for_robot) {
                    j++;
                    j = j % 16;
                    playerTransition.getChildren().add(player.getPawn().transition(j));
                }
                if (new_location_for_robot == 12) {
                    playerTransition.getChildren().add(player.getPawn().transition(4));
                }

                FillTransition unboardPlayerRect = board_rectangles.unBoardedToRect(pawn1_pose[0]);
                FillTransition boardPlayerRect = board_rectangles.boardedToRect(new_location_for_robot);
                System.out.println("the location of the robot");
                System.out.println(new_location_for_robot);
                pawn1_pose[0] = new_location_for_robot;


                // TODO: Update the robot's properties
                // TODO: Update the robot's money


                // update player position
                int new_location = players_json.getJSONObject(0).getInt("location");
                boolean player2PlaceChange = false;
                if (new_location != pawn2_pose[0]) {
                    player2PlaceChange = true;
                }

                SequentialTransition player2Transition = new SequentialTransition();
                j = pawn2_pose[0];
                while (j != new_location) {
                    j++;
                    j = j % 16;
                    player2Transition.getChildren().add(player2.getPawn().transition(j));
                }
                if (new_location == 12) {
                    player2Transition.getChildren().add(player2.getPawn().transition(4));
                }
                //TranslateTransition player2Transition = player2.getPawn().transition(new_location);

                FillTransition unboardPlayer2Rect = board_rectangles.unBoardedToRect(pawn2_pose[0]);
                FillTransition boardPlayer2Rect = board_rectangles.boardedToRect(new_location);
                System.out.println("the location of user");
                System.out.println(new_location);
                pawn2_pose[0] = new_location;

                ParallelTransition parallelTransition1 = new ParallelTransition();
                //parallelTransition1.setOnFinished(actionEvent -> whoPlaysText.setText("Robot plays!")); // Not working currently!!!
                ParallelTransition parallelTransition2 = new ParallelTransition();
                //parallelTransition2.setOnFinished(actionEvent -> whoPlaysText.setText("Human plays!")); // Not working currently!!!

                FadeTransition fadeInRobot = new FadeTransition(Duration.millis(1), whoPlaysText);
                fadeInRobot.setFromValue(0.0);
                fadeInRobot.setToValue(1.0);

                FadeTransition fadeOut = new FadeTransition(Duration.millis(1), whoPlaysText);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);

                fadeOut.setOnFinished(event2 -> whoPlaysText.setText("Robot Plays"));

                FadeTransition fadeIn = new FadeTransition(Duration.millis(1), whoPlaysText);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);

                PauseTransition pause = new PauseTransition(Duration.millis(300));
                pause.setOnFinished(event3 -> whoPlaysText.setText("Human plays"));

                parallelTransition2.setOnFinished(event1 -> whoPlaysText.setText("Human plays"));

                if (playerPlaceChange) {
                    parallelTransition1 = new ParallelTransition(
                            unboardPlayerRect,
                            playerTransition,
                            boardPlayerRect
                    );
                }
                if (player2PlaceChange) {
                    parallelTransition2 = new ParallelTransition(
                            unboardPlayer2Rect,
                            player2Transition,
                            boardPlayer2Rect
                    );
                }

                SequentialTransition sequentialTransition = new SequentialTransition(
                        fadeInRobot,
                        parallelTransition1,
                        fadeOut,
                        fadeIn,
                        parallelTransition2,
                        pause
                );
                sequentialTransition.play();

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
        root.getChildren().add(whoPlaysText);
        root.getChildren().add(buyText);
        root.getChildren().add(checkBox);

        return root;
    }


}