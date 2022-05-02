package group2.monopoly.frontend;

import group2.monopoly.frontend.BoardParts.*;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

import group2.monopoly.frontend.Utils.BoardToCoords;


public class BoardRectangles {
    Group rects;
    int width, height;
    int text_margin_width = 20;
    int text_margin_height = 30;
    private int grid_count;
    BoardToCoords boardToCoords;
    List<BoardElement> board_elements = new ArrayList<>();

    public BoardRectangles(int width, int height, List<List<String>> board_elements, int grid_count) {
        rects = new Group();
        this.width = width;
        this.height = height;
        this.grid_count = grid_count;
        boardToCoords = new BoardToCoords(width, height, grid_count);
        for (List<String> list : board_elements) {
            if (list.get(0).equals("Property")) {
                Property property = new Property(list.get(1));
                property.setColor(Color.CADETBLUE);
                this.board_elements.add(property);
            }
            else if (list.get(0).equals("Income tax")) {
                this.board_elements.add(new IncomeTax());
            }
            else if (list.get(0).equals("Go to jail")) {
                this.board_elements.add(new GoToJail());
            }
            else if (list.get(0).equals("Railroad/Ferry")) {
                this.board_elements.add(new RailroadFerry());
            }
            else if (list.get(0).equals("Jail/Just visiting")) {
                this.board_elements.add(new Jail());
            }
            else if (list.get(0).equals("Start point")) {
                this.board_elements.add(new StartPoint());
            }
            else {
                System.out.println("Error: " + list.get(0) + " is not a valid board element");
            }
        }
    }

    public void createRectangles() {
        // There is a 5x5 board that we will fit into the whole screen
        int width_step = width / this.grid_count;
        int height_step = height / this.grid_count;
        ArrayList<StackPane> stackPanes = new ArrayList<>();

        // append all coordinates to a list
        List<List<Integer>> board_coords = new ArrayList<>();
        for (int k = 0; k < 16; k++) {
            List<Integer> coords = boardToCoords.getCoords(k);
            System.out.println(k);

            Rectangle rect = new Rectangle(0, 0, width_step, height_step);
            //rect.setFill(Color.CORNFLOWERBLUE);
            rect.setFill(board_elements.get(k).getColor());
            rect.setStroke(Color.BLACK);
            StackPane stack = new StackPane();

            Text text = new Text(board_elements.get(k).getDisplayName());
            text.setStyle("-fx-font: 20 arial; -fx-text-fill: red;");
            //text.setX(width_step * (i + 1) + text_margin_width);
            //text.setY(height_step * (j + 1) + text_margin_height);
            stack.getChildren().add(rect);
            stack.getChildren().add(text);

            //stack.setMaxHeight(height_step);
            //stack.setMaxWidth(width_step);
            stackPanes.add(stack);
            stack.setLayoutX(coords.get(0));
            stack.setLayoutY(coords.get(1));

            rects.getChildren().add(stack);


        }




    }

    public Group getRects() {
        return rects;
    }


}