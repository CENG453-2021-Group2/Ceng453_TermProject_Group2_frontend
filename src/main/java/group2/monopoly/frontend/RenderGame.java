package group2.monopoly.frontend;

import group2.monopoly.frontend.Player.Player;
import javafx.animation.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
  * This class renders the game screen
 */
public class RenderGame {

    /**
      * This method checks if the player can buy the property, by the given parameter
      * @param gameStateJSON gamestate as JSON
      * @return boolean if the player can buy the property
      */
    private static boolean checkIfCanBuyProperty(JSONObject gameStateJSON) {
        JSONArray players_json = gameStateJSON.getJSONArray("players");
        JSONObject player_json = players_json.getJSONObject(0);
        JSONObject player2_json = players_json.getJSONObject(1);
        JSONObject gameTableConfiguration = gameStateJSON.getJSONObject("gameTableConfiguration");
        int incomeTax = gameTableConfiguration.getInt("incomeTaxIndex");

        int curr_location = player_json.getInt("location");

        // check If we own it
        JSONArray our_properties = player_json.getJSONArray("ownedPurchasables");
        for(int i = 0; i < our_properties.length(); i++) {
            int property = our_properties.getInt(i);
            if (curr_location == property) {
                return false;
            }
        }

        // check if the other player owns it
        JSONArray robot_properties = player2_json.getJSONArray("ownedPurchasables");
        for(int i = 0; i < robot_properties.length(); i++) {
            int property = robot_properties.getInt(i);
            if (curr_location == property) {
                return false;
            }
        }

        // check if it was income tax, or other special places
        if (curr_location == incomeTax || curr_location == 4 || curr_location == 12 || curr_location == 0) {
            return false;
        }

        return true;
    }

    /**
      * This method prepares the property orders in the screen according to the game table configuration
      * @param gameTableConfiguration gamestate as JSON
      * @param boardPlaceNames the board place names
      * @return the names of the properties according to the game table configuration
      */
    private static List<List<String>> prepareInitialPlacesList (JSONObject gameTableConfiguration, List<String> boardPlaceNames) {
        List<List<String>> places = new ArrayList<>();// mock

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

        places.add(new ArrayList<String>() {
            {
                add("Start point"); // Property type
                add(""); // property property 1
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Go to jail"); // Property type
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
                add("Go to jail"); // Property type
                add("-100"); // property property 1
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Property"); // Property type
                add("-1"); // property property 1
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
                add("-2"); // property property 1 / cost of property
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Property"); // Property type
                add("-3"); // property property 1 / cost of property
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Property"); // Property type
                add("-4"); // property property 1 / cost of property
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Jail/Just visiting"); // Property type
                add("100"); // property property 1 / cost of property
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Jail/Just visiting"); // Property type
                add("100"); // property property 1 / cost of property
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Jail/Just visiting"); // Property type
                add("100"); // property property 1 / cost of property
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Property"); // Property type
                add("-5"); // property property 1 / cost of property
            }
        });
        places.add(new ArrayList<String>() {
            {
                add("Property"); // Property type
                add("-6"); // property property 1 / cost of property
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
        final int[] place_index = {0};
        // now arrange them!
        for (int i = 0; i < propertyIndicesArr.size(); i++) {
            int finalI = i;
            places.set(16 - propertyIndicesArr.get(i), new ArrayList<String>() {
                {
                    add(boardPlaceNames.get(place_index[0]));
                    add(String.valueOf((50 * place_index[0] + 100)));
                    place_index[0]++;
                }
            });
        }

        places.set(16 - incomeTaxIndex, new ArrayList<String>() {
            {
                add("Income tax");
                add("");
            }
        });

        return places;
    }

    /**
      * This method renders the board in the screen
      * @param app the spring application
      * @param gameTableConfiguration gametableconfiguration as JSON
      * @param gameJSON gamestate as JSON
      * @param width width of the board
      * @param height height of the board
      */
    public static Group render(HelloApplication app, JSONObject gameTableConfiguration, JSONObject gameJSON, int width, int height) {
        System.out.println("got render game render");
        System.out.println(gameJSON.toString());
        JSONObject player1JSON = gameJSON.getJSONArray("players").getJSONObject(0);
        JSONObject player2JSON = gameJSON.getJSONArray("players").getJSONObject(1);

        int grid_count = 7;
        final boolean[] first_step = {true};
        List<String> boardPlaceNames = new ArrayList<String>(List.of(
                "Yemekhane",
                "Balıkçı",
                "Central",
                "Piyata",
                "Susam",
                "Pizzacı\n Altan",
                "Çatı",
                "Naz"
        ));
        List<List<String>> places = prepareInitialPlacesList(gameTableConfiguration, boardPlaceNames);

        final boolean[] stepButtonBoolean = new boolean[1];
        stepButtonBoolean[0] = false;
        BooleanBinding stepButtonBooleanBinding = Bindings.createBooleanBinding(() -> stepButtonBoolean[0]);

        // who plays text
        Text whoPlaysText = new Text("Human plays!");
        whoPlaysText.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: red;");
        whoPlaysText.setX(width/2-100);
        whoPlaysText.setY(50);

        BoardRectangles board_rectangles = new BoardRectangles(width, height, places, grid_count);
        board_rectangles.createRectangles();
        System.out.println("pwd: " + System.getProperty("user.dir"));
        Player player = new Player("alp", player1JSON.getInt("money"), "file:src/main/java/group2/monopoly/frontend/pawn_ship.png", width, height, grid_count, true);
        Player player2 = new Player("deniz", player2JSON.getInt("money"), "file:src/main/java/group2/monopoly/frontend/pawn_shoe.png", width, height, grid_count, false);
        final List<Player>[] players = new List[]{new ArrayList<>()};
        players[0].add(player);
        players[0].add(player2);
        List<String> player1OwnedPurchasables = CreatePropertyDisplay.findNames(
                (JSONArray) player1JSON.get("ownedPurchasables"),
                (JSONArray) gameTableConfiguration.get("propertyIndices"),
                (JSONArray) gameTableConfiguration.get("portIndices"),
                boardPlaceNames
        );
        player.updateProperties(player1OwnedPurchasables);
        List<String> player2OwnedPurchasables = CreatePropertyDisplay.findNames(
                (JSONArray) player2JSON.get("ownedPurchasables"),
                (JSONArray) gameTableConfiguration.get("propertyIndices"),
                (JSONArray) gameTableConfiguration.get("portIndices"),
                boardPlaceNames
        );
        player2.updateProperties(player2OwnedPurchasables);
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

        boolean cheat = false;
        app.scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination keyCombination = new KeyCodeCombination(KeyCode.DIGIT9, KeyCombination.CONTROL_ANY);
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyCombination.match(keyEvent)) {
                    System.out.println("Pressed cheat!");

                    // send the player to income tax index
                    int incomeTaxIndex = gameTableConfiguration.getInt("incomeTaxIndex");
                    TranslateTransition playerTransition = player2.getPawn().transition(16 - incomeTaxIndex);
                    int beforeMoney = player2.getMoney();
                    playerTransition.setOnFinished(event10 -> {
                        player2.setMoney(player2.getMoney() - 10000000);
                    });

                    playerTransition.play();

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("You lost!");
                    String s = new String();
                    s = "Final money: ";
                    alert.setHeaderText(s + String.valueOf(beforeMoney - 10000000));
                    app.endGame();
                    alert.showAndWait();

                }
            }

        });

        // when clicked to button, call pawn2.transition(pawn2_pose+1)
        //button.disableProperty().bind(stepButtonBooleanBinding);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                button.setDisable(true);
                checkBox.setDisable(true);
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
                List<String> player2OwnedPurchasables = CreatePropertyDisplay.findNames(
                        (JSONArray) players_json.getJSONObject(0).get("ownedPurchasables"),
                        (JSONArray) gameTableConfiguration.get("propertyIndices"),
                        (JSONArray) gameTableConfiguration.get("portIndices"),
                        boardPlaceNames
                        );
                player2.updateProperties(player2OwnedPurchasables);
                int new_location_for_robot = 16 - players_json.getJSONObject(1).getInt("location");
                boolean playerPlaceChange = false;
                if (new_location_for_robot != pawn1_pose[0]) {
                    playerPlaceChange = true;
                }

                // set player money
                player2.setMoney(players_json.getJSONObject(0).getInt("money"));


                // Then, continue with the robot's move
                SequentialTransition playerTransition = new SequentialTransition();
                int j = pawn1_pose[0];
                // Workaround until the bug is fixed
                if (new_location_for_robot == 16) {
                    new_location_for_robot = 0;
                }
                while (j != new_location_for_robot) {
                    j--;
                    j += 16; // because mod operator does not necessarily convert -1 to 15
                    j = j % 16;
                    playerTransition.getChildren().add(player.getPawn().transition(j));
                }
                if (new_location_for_robot == 4) {
                    playerTransition.getChildren().add(player.getPawn().transition(12));
                }

                FillTransition unboardPlayerRect = board_rectangles.unBoardedToRect(pawn1_pose[0]);
                FillTransition boardPlayerRect = board_rectangles.boardedToRect(new_location_for_robot);
                System.out.println("the location of the robot");
                System.out.println(new_location_for_robot);
                pawn1_pose[0] = new_location_for_robot;


                // TODO: Update the robot's properties
                // First update the player's purchase, if any
                List<String> playerOwnedPurchasables = CreatePropertyDisplay.findNames(
                        (JSONArray) players_json.getJSONObject(1).get("ownedPurchasables"),
                        (JSONArray) gameTableConfiguration.get("propertyIndices"),
                        (JSONArray) gameTableConfiguration.get("portIndices"),
                        boardPlaceNames
                );
                player.updateProperties(playerOwnedPurchasables);


                // TODO: Update the robot's money
                player.setMoney(players_json.getJSONObject(1).getInt("money"));



                // update player position
                int new_location = 16 - players_json.getJSONObject(0).getInt("location");
                boolean player2PlaceChange = false;
                if (new_location != pawn2_pose[0]) {
                    player2PlaceChange = true;
                }

                SequentialTransition player2Transition = new SequentialTransition();
                j = pawn2_pose[0];
                // Workaround until the bug is fixed
                if (new_location == 16) {
                    new_location = 0;
                }
                while (j != new_location) {
                    j--;
                    j += 16; // because mod operator does not necessarily convert -1 to 15
                    j = j % 16;
                    player2Transition.getChildren().add(player2.getPawn().transition(j));
                }
                if (new_location == 4) {
                    player2Transition.getChildren().add(player2.getPawn().transition(12));
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

                fadeOut.setOnFinished(event2 -> whoPlaysText.setText("Human Plays"));

                FadeTransition fadeIn = new FadeTransition(Duration.millis(1), whoPlaysText);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);

                PauseTransition pause = new PauseTransition(Duration.millis(300));

                parallelTransition2.setOnFinished(event1 -> whoPlaysText.setText("Robot plays"));

                PauseTransition pauseBetweenPlayers = new PauseTransition(Duration.millis(500));
                PauseTransition pauseBeforeFirstMove = new PauseTransition(Duration.millis(100));
                PauseTransition pauseAfterRobot = new PauseTransition(Duration.millis(600));
                pauseBeforeFirstMove.setOnFinished(event8 -> whoPlaysText.setText("Robot plays"));
                PauseTransition pauseAfterBeforeFirstMove = new PauseTransition(Duration.seconds(1));

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
                        pauseBeforeFirstMove,
                        fadeInRobot,
                        parallelTransition1,
                        pauseAfterRobot,
                        fadeOut,
                        fadeIn,
                        pauseBetweenPlayers,
                        parallelTransition2,
                        pause
                );
                sequentialTransition.setOnFinished(event7 -> {
                    button.setDisable(false);
                    if (checkIfCanBuyProperty(gameStateJSON)) {
                        checkBox.setDisable(false);
                    }
                    board_rectangles.updateRectTexts(
                            (JSONArray) players_json.getJSONObject(0).get("ownedPurchasables"),
                            (JSONArray) players_json.getJSONObject(1).get("ownedPurchasables")
                    );
                });
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
        root.getChildren().add(player.getMoneyDisplay());
        root.getChildren().add(player2.getPropertiesDisplay());
        root.getChildren().add(player.getPropertiesDisplay());
        root.getChildren().add(whoPlaysText);
        root.getChildren().add(buyText);
        root.getChildren().add(checkBox);

        return root;
    }


}