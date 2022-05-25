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
    Scene scene;
    Stage stage;
    private final int width = 800;
    private final int height = 600;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        if (in_game) {
            this.scene = new Scene(RenderGame.render(this), width, height);
        }
        else if (in_signup) {
            this.scene = new Scene(RenderSignUp.render(this, width, height), width, height);
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
        System.out.println("Starting game");
        this.scene.setRoot(RenderGame.render(this));
    }

    public void endGame() {
        in_game = false;
        in_signup = true;
        System.out.println("Ending game");
        this.scene.setRoot(RenderSignMenu.render(this, width, height));
    }

    public void startSignUp() {
        in_game = false;
        in_signup = true;
        System.out.println("Starting sign up");
        this.scene.setRoot(RenderSignUp.render(this, width, height));
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

    public static void main(String[] args) {
        launch();
    }

    @GetMapping("/startGame")
    public void startGameRest() {
        startGame();
    }
}