package group2.monopoly.frontend;

import group2.monopoly.frontend.Player.Player;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class RenderGame {

    public static Group render(HelloApplication app, JSONObject gameTableConfiguration) {
        int grid_count = 7;
        int width = 800;
        int height = 600;
        List<List<String>> places = new ArrayList<>();
        boolean first_step = true;
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
        // when clicked to button, call pawn2.transition(pawn2_pose+1)
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (first_step) {
                    try {
                        String gameState = app.stepInGame(false);
                        JSONObject gameStateJSON = new JSONObject(gameState);
                        JSONArray players_json = gameStateJSON.getJSONArray("players");
                        System.out.println("players json is");
                        System.out.println(players_json);
                        int new_location = players_json.getJSONObject(0).getInt("location");
                        System.out.println("Neww location for player!!!!!!");
                        System.out.println(new_location);
                        player2.getPawn().transition(new_location);
                        board_rectangles.boardedToRect(new_location);
                        board_rectangles.unBoardedToRect(pawn2_pose[0]);
                        pawn2_pose[0] = 16 - new_location;
                        int new_location_for_robot = players_json.getJSONObject(0).getInt("location");
                        player.getPawn().transition(new_location_for_robot);
                        board_rectangles.boardedToRect(new_location_for_robot);
                        board_rectangles.unBoardedToRect(pawn1_pose[0]);
                        pawn1_pose[0] = 16 - new_location_for_robot;

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                //pawn2_pose[0]++;
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