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

@SpringBootApplication
@RestController
@RequestMapping("/frontend")
public class HelloApplication extends Application {
    private final int grid_count = 7;
    private boolean in_game = false;
    private boolean in_signup = false;
    private boolean in_forgot_password = false;
    Scene scene;
    Stage stage;
    private final int width = 800;
    private final int height = 600;

    private String user_token = "";

    private String curr_game_name = "";

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        if (in_game) {
            this.scene = new Scene(RenderGame.render(this), width, height);
        }
        else if (in_signup) {
            this.scene = new Scene(RenderSignUp.render(this, width, height), width, height);
        }
        else if (in_forgot_password) {
            this.scene = new Scene(RenderForgotPassword.render(this, width, height), width, height);
        }
        else {
            this.scene = new Scene(RenderSignMenu.render(this, width, height), width, height);
        }
        stage.setTitle("Monopoly");
        stage.setScene(scene);
        stage.show();
    }

    public void startGame() {
        in_game = true;
        in_signup = false;
        in_forgot_password = false;
        System.out.println("Starting game");
        try {
            executeCreateGame("1");
        } catch (IOException e) {
            return;
        }
        curr_game_name = "1";
        this.scene.setRoot(RenderGame.render(this));
    }

    public void endGame() {
        in_game = false;
        in_signup = true;
        in_forgot_password = false;
        System.out.println("Ending game");
        user_token = "";
        this.scene.setRoot(RenderSignMenu.render(this, width, height));
    }

    public void startSignUp() {
        in_game = false;
        in_signup = true;
        in_forgot_password = false;
        System.out.println("Starting sign up");
        this.scene.setRoot(RenderSignUp.render(this, width, height));
    }

    public void startForgotPassword() {
        in_game = false;
        in_signup = false;
        in_forgot_password = true;
        System.out.println("Starting forgot menu");
        this.scene.setRoot(RenderForgotPassword.render(this, width, height));
    }

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
        }

        conn.disconnect();
    }

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

    public static void main(String[] args) {
        launch();
    }

    @GetMapping("/startGame")
    public void startGameRest() {
        startGame();
    }
}