package group2.monopoly.frontend;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class RenderGameSelection {

    private static final int field_width = 150;
    private static final int field_margin = 60;
    private static final int field_margin_margin = 10;

    public static Group render(HelloApplication app, int width, int height) {
        System.out.println();

        Group root = new Group();

        // Get list of games
        final JSONArray[] games = {null};
        try {
            games[0] = app.executeListGames();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Games: " + games[0]);

        // Create a table containing the game's ids
        TableView<String> table = new TableView<>();
        table.setMinWidth(100);
        table.setMinHeight(50);
        table.setMaxWidth(100);
        table.setMaxHeight(600);
        // put id column
        TableColumn<String, String> idColumn = new TableColumn<>("Game ID");
        idColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
        table.getColumns().add(idColumn);

        // put ids to id column
        for (int i = 0; i < games[0].length(); i++) {
            JSONObject game = games[0].getJSONObject(i);
            Integer id = game.getInt("id");
            table.getItems().add(id.toString());
        }
        final Integer[] last_game_id = {games[0].getJSONObject(games[0].length() - 1).getInt(("id"))};

        Button createGame = new Button("Create Game");
        createGame.setLayoutX(width/2 - 100);
        createGame.setLayoutY(height/3*2);

        createGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Firstly, get all games!
                try {
                    games[0] = app.executeListGames();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                last_game_id[0] = games[0].getJSONObject(games[0].length() - 1).getInt(("id"));
                String next_game_id = new Integer(last_game_id[0] + 1).toString();
                try {
                    app.executeCreateGame(next_game_id);
                    table.getItems().add(next_game_id);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Button listGames = new Button("List Games");
        listGames.setLayoutX(width/2 - 100);
        listGames.setLayoutY(height/3*2+80);

        listGames.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Firstly, get all games!
                try {
                    JSONArray a = app.executeListGames();
                    System.out.println("Returning!!!!");
                    System.out.println(a);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // add a text field for id input
        TextField idInput = new TextField();
        idInput.setLayoutX(width/2 - 100);
        idInput.setLayoutY(height/3*2-100);

        // add a button, getting the idInput text field
        Button joinGame = new Button("Join Game");
        joinGame.setLayoutX(width/2 - 100);
        joinGame.setLayoutY(height/3*2-100+80);

        joinGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String id = idInput.getText();
                try {
                    app.gameTableConfigurationJSON = app.executeGetGame(id).getJSONObject("gameTableConfiguration");
                    app.curr_game_name = id;
                    app.startGame();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        root.getChildren().add(createGame);
        root.getChildren().add(listGames);
        root.getChildren().add(table);
        root.getChildren().add(idInput);
        root.getChildren().add(joinGame);

        return root;
    }
}
