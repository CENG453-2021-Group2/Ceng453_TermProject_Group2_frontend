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

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.json.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import group2.monopoly.frontend.Pawn.Pawn;

/**
  * This class is the main class that holds the springboot application
 */
@SpringBootApplication
@RestController
@RequestMapping("/frontend")
public class HelloApplication extends Application {
    private final int grid_count = 7;
    private boolean in_game = false;
    private boolean in_signup = false;
    private boolean in_forgot_password = false;

    private boolean in_game_selection = false;
    Scene scene;
    Stage stage;
    private final int width = 800;
    private final int height = 600;

    private String user_token = "";

    public String curr_game_name = "";

    public JSONObject gameTableConfigurationJSON = null;

    public JSONObject gameJSON = null;

    /**
      * This method is the main method that runs the application
      * It sets the scene and stage, and then shows the stage
      * The scene is decided by the boolean variables in_game, in_signup, and in_forgot_password
     */
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        if (in_game) {
            this.scene = new Scene(RenderGame.render(this, gameTableConfigurationJSON, this.gameJSON, width, height), width, height);
        }
        else if (in_signup) {
            this.scene = new Scene(RenderSignUp.render(this, width, height), width, height);
        }
        else if (in_forgot_password) {
            this.scene = new Scene(RenderForgotPassword.render(this, width, height), width, height);
        }
        else if (in_game_selection) {
            this.scene = new Scene(RenderGameSelection.render(this, width, height), width, height);
        }
        else {
            this.scene = new Scene(RenderSignMenu.render(this, width, height), width, height);
        }
        stage.setTitle("Monopoly");
        stage.setScene(scene);
        stage.show();
    }

    /**
      * This method is called when the user clicks the join game button
      * It sets the in_game boolean to true, and then changes the scene root to game scene
     */
    public void startGame() {
        in_game = true;
        in_signup = false;
        in_forgot_password = false;
        in_game_selection = false;
        System.out.println("Starting game");

        if (this.curr_game_name.equals("")) {
            System.out.println("You did not initialize game name!");
            return;
        }

        this.scene.setRoot(RenderGame.render(this, this.gameTableConfigurationJSON, this.gameJSON, this.width, this.height));
    }

    /**
      * This method is called when the user clicks the exit button in the game
      * It sets the in_signup boolean to true, and then changes the scene root to sign menu scene
      */
    public void endGame() {
        in_game = false;
        in_signup = true;
        in_forgot_password = false;
        in_game_selection = false;
        System.out.println("Ending game");
        user_token = "";
        this.scene.setRoot(RenderSignMenu.render(this, width, height));
    }

    /**
      * This method is called when the user clicks the sign up button
      * It sets the in_signup boolean to true, and then changes the scene root to sign up scene
      */
    public void startSignUp() {
        in_game = false;
        in_signup = true;
        in_forgot_password = false;
        in_game_selection = false;
        System.out.println("Starting sign up");
        this.scene.setRoot(RenderSignUp.render(this, width, height));
    }

    /**
      * This method is called when the user clicks the forgot password button
      * It sets the in_forgot_password boolean to true, and then changes the scene root to forgot password scene
      */
    public void startForgotPassword() {
        in_game = false;
        in_signup = false;
        in_forgot_password = true;
        in_game_selection = false;
        System.out.println("Starting forgot menu");
        this.scene.setRoot(RenderForgotPassword.render(this, width, height));
    }

    /**
      * This method is called when the user clicks the sign in button in the sign in scene
      * It sets the in_game_selection boolean to true, and then changes the scene root to sign menu scene
      */
    public void startGameSelection() {
        in_game = false;
        in_signup = false;
        in_forgot_password = false;
        in_game_selection = true;
        System.out.println("Starting game selection");

        this.scene.setRoot(RenderGameSelection.render(this, this.width, this.height));
    }

    /**
      * This method sends a request to backend to sign up the user
      * It does not return a value, if it executes successfully, it finished the function normally
      * If it fails, it throws an exception
      * @param username the username of the user
      * @param password the password of the user
      * @param confirmPassword the password of the user
      * @param email the email of the user
     */
    public void executeSignUp(String username, String password, String confirmPassword, String email) throws IOException {
        URL url = new URL("http://localhost:8080/api/auth/register");
        /*
        String postData = "username=" + username + "&" +
                "email=" + email + "&" +
                "password=" + password + "&" +
                "confirmPassword=" + confirmPassword;
        */
        String postData = "{ \"username\": \"" + username + "\", \"password\": \"" + password + "\", \"confirmPassword\": \"" + confirmPassword + "\", \"email\": \"" + email + "\"}";
        System.out.println("the post data is");
        System.out.println(postData);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        OutputStream os = conn.getOutputStream();
        os.write(postData.getBytes());
        os.flush();

        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            //System.out.println("Failed: HTTP error code: " + conn.getResponseCode());
            throw new RuntimeException("Failed: HTTP error code: " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String output;
        System.out.println("Output from server ... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        conn.disconnect();

    }

    /**
      * This method sends a request to backend to sign in the user
      * It does not return a value, if it executes successfully, it finishes the function normally
      * If it fails, it throws an exception
      * It assigns the user_token to the token that is returned from the backend
      * @param username the username of the user
      * @param password the password of the user
     */
    public void executeSignIn(String username, String password) throws IOException {
        URL url = new URL("http://localhost:8080/api/auth/login");

        String postData = "{ \"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
        System.out.println("the post data is");
        System.out.println(postData);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        OutputStream os = conn.getOutputStream();
        os.write(postData.getBytes());
        os.flush();

        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED && conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            //System.out.println("Failed: HTTP error code: " + conn.getResponseCode());
            throw new RuntimeException("Failed: HTTP error code: " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String output;
        System.out.println("Output from server ... \n");
        while ((output = br.readLine()) != null) {
            //System.out.println(output);
            JSONObject jsonObject = new JSONObject(output);
            user_token = jsonObject.getString("token");
            System.out.println(user_token);
        }


        conn.disconnect();

    }

    /**
      * This method sends a request to backend to reset the password of the user
      * It does not return a value, if it executes successfully, it finishes the function normally
      * If it fails, it throws an exception
      * @param identifier the identifier(user token) of the user
      */
    public void executeForgotPassword(String identifier) throws IOException {
        URL url = new URL("http://localhost:8080/api/auth/forgotPassword");

        String postData = "{ \"identifier\": \"" + identifier + "\"}";
        System.out.println("the post data is");
        System.out.println(postData);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        OutputStream os = conn.getOutputStream();
        os.write(postData.getBytes());
        os.flush();

        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED && conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            //System.out.println("Failed: HTTP error code: " + conn.getResponseCode());
            throw new RuntimeException("Failed: HTTP error code: " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String output;
        System.out.println("Output from server ... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }


        conn.disconnect();
    }

    /**
      * This method sends a request to backend to create a game
      * It does not return a value, if it executes successfully, it finishes the function normally
      * If it fails, it throws an exception
      * @param gameName the name of the game
      */
    public void executeCreateGame(String gameName) throws IOException {
        URL url = new URL("http://localhost:8080/api/game");

        String postData = "{ \"name\": \"" + gameName + "\"}";
        System.out.println("the post data is");
        System.out.println(postData);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + user_token);

        OutputStream os = conn.getOutputStream();
        os.write(postData.getBytes());
        os.flush();

        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED && conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            //System.out.println("Failed: HTTP error code: " + conn.getResponseCode());
            throw new RuntimeException("Failed: HTTP error code: " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String output;
        System.out.println("Output from server ... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
            conn.disconnect();
            return output;
        }
        return null;
    }

    /**
      * This method sends a request to backend to get the game <gameName>
      * It returns the JSONObject that is returned from the backend
      * @param gameName the name of the game
      * @return the JSONObject that is returned from the backend
     */
    public JSONObject executeGetGame(String gameName) throws IOException {
        URL url = new URL("http://localhost:8080/api/game/" + gameName);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + user_token);

        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED && conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            //System.out.println("Failed: HTTP error code: " + conn.getResponseCode());
            throw new RuntimeException("Failed: HTTP error code: " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String output;
        System.out.println("Output from server ... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
            conn.disconnect();
            
            // convert output to JSONObject
            JSONObject jsonObject = new JSONObject(output);
            return jsonObject;
        }
        return null;
    }

    /**
      * This method sends a request to backend to get the list of games
      * It returns the JSONArray that is returned from the backend
      * @return the JSONArray that is returned from the backend
      */
    public JSONArray executeListGames() throws IOException {
        URL url = new URL("http://localhost:8080/api/game");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + user_token);

        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED && conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            //System.out.println("Failed: HTTP error code: " + conn.getResponseCode());
            throw new RuntimeException("Failed: HTTP error code: " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String output;
        System.out.println("Output from server ... \n");
        while ((output = br.readLine()) != null) {
            conn.disconnect();
            // convert output to JSON array
            JSONArray jsonArray = new JSONArray(output);
            System.out.println(jsonArray);
            return jsonArray;
        }
        return null;
    }

    /**
      * This method sends a request to backend to make a step in the game
      * @param will_buy the boolean value that indicates whether the player will buy the property
      * @return String game state
     */
    public String stepInGame(boolean will_buy) throws IOException {
        URL url = new URL("http://localhost:8080/api/game/1");

        String postData = "{ \"buy\": \"" + will_buy + "\"}";
        System.out.println("the post data is");
        System.out.println(postData);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + user_token);

        OutputStream os = conn.getOutputStream();
        os.write(postData.getBytes());
        os.flush();

        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED && conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            //System.out.println("Failed: HTTP error code: " + conn.getResponseCode());
            throw new RuntimeException("Failed: HTTP error code: " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String output = "";
        System.out.println("Output from server ... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
            return output;
        }

        conn.disconnect();
        return null;
    }

    /**
      * the main function that launches the program
     */
    public static void main(String[] args) {
        launch();
    }

    /**
      * A rest method to start the game with an endpoint
     */
    @GetMapping("/startGame")
    public void startGameRest() {
        startGame();
    }
}