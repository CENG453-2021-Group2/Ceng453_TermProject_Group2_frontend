package group2.monopoly.frontend;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;

public class RenderSignMenu {

    public static Group render(HelloApplication app) {
        Button btn = new Button("Heck");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                app.startGame();
            }
        });
        Group root = new Group();
        root.getChildren().add(btn);
        return root;
    }
}
