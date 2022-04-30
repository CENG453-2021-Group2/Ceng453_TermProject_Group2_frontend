package group2.monopoly.frontend;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


public class BoardRectangles {
    Group rects;
    int width, height;
    int text_margin_width = 20;
    int text_margin_height = 30;

    public BoardRectangles(int width, int height) {
        rects = new Group();
        this.width = width;
        this.height = height;
    }

    public void CreateRectangles() {
        // There is a 5x5 board that we will fit into the whole screen
        int i = 0;
        int j = 0;
        int width_step = width / 7;
        int height_step = height / 7;
        ArrayList<StackPane> stackPanes = new ArrayList<>();
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 5; j++) {
                if (i == 0 || j == 0 || i == 4 || j == 4) {
                    Rectangle rect = new Rectangle(0, 0, width_step, height_step);
                    rect.setFill(Color.CORNFLOWERBLUE);
                    rect.setStroke(Color.BLACK);
                    StackPane stack = new StackPane();
                    Text text = new Text("Start point");
                    text.setStyle("-fx-font: 20 arial; -fx-text-fill: red;");
                    //text.setX(width_step * (i + 1) + text_margin_width);
                    //text.setY(height_step * (j + 1) + text_margin_height);
                    stack.getChildren().add(rect);
                    stack.getChildren().add(text);

                    //stack.setMaxHeight(height_step);
                    //stack.setMaxWidth(width_step);
                    stackPanes.add(stack);
                    stack.setLayoutX(width_step * (i + 1));
                    stack.setLayoutY(height_step * (j + 1));

                    rects.getChildren().add(stack);
                    System.out.println("Rectangle added: " + i + " " + j);

                }
            }
        }
    }

    public Group getRects() {
        return rects;
    }


}