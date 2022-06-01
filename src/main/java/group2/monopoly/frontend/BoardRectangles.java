package group2.monopoly.frontend;

import group2.monopoly.frontend.BoardParts.*;
import javafx.animation.FillTransition;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import group2.monopoly.frontend.Utils.BoardToCoords;
import javafx.util.Duration;
import org.json.JSONArray;


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
        for (List<String> board_element : board_elements) {
            if (board_element.get(0).equals("Income tax")) {
                this.board_elements.add(new IncomeTax());
            }
            else if (board_element.get(0).equals("Go to jail")) {
                this.board_elements.add(new GoToJail());
            }
            else if (board_element.get(0).equals("Railroad/Ferry")) {
                this.board_elements.add(new RailroadFerry());
            }
            else if (board_element.get(0).equals("Jail/Just visiting")) {
                this.board_elements.add(new Jail());
            }
            else if (board_element.get(0).equals("Start point")) {
                this.board_elements.add(new StartPoint());
            }
            else { // It must be a property!
                Property property = new Property(board_element.get(0), board_element.get(1));
                this.board_elements.add(property);
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
            text.setStyle("-fx-font: 19 arial; -fx-text-fill: red;");
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

    public FillTransition boardedToRect(int index) {
        System.out.println("Boarded to rect");
        StackPane s = (StackPane) rects.getChildren().get(index);
        Rectangle r = (Rectangle) s.getChildren().get(0);
        Color beforeColor = board_elements.get(index).getColor();
        board_elements.get(index).placedPawn();
        Color afterColor = board_elements.get(index).getColor();
        return new FillTransition(Duration.millis(200), r, beforeColor, afterColor);
        //System.out.println(board_elements.get(index).getColor());
        //r.setFill(board_elements.get(index).getColor());
    }

    public FillTransition unBoardedToRect(int index) {
        System.out.println("Unboarded to rect");
        StackPane s = (StackPane) rects.getChildren().get(index);
        Rectangle r = (Rectangle) s.getChildren().get(0);
        Color beforeColor = board_elements.get(index).getColor();
        board_elements.get(index).removedPawn();
        Color afterColor = board_elements.get(index).getColor();
        return new FillTransition(Duration.millis(200), r, beforeColor, afterColor);
        //r.setFill(board_elements.get(index).getColor());
    }

    public void updateRectTexts(JSONArray player1_own, JSONArray player2_own) {
        System.out.println("updateRectTexts");
        System.out.println(player1_own);
        for (int i = 0; i < player1_own.length(); i++) {
            StackPane s = (StackPane) this.getRects().getChildren().get(16 - player1_own.getInt(i));
            Text t = (Text) s.getChildren().get(1);
            t.setFill(Color.rgb(255,140,140));
        }

        for (int i = 0; i < player2_own.length(); i++) {
            StackPane s = (StackPane) this.getRects().getChildren().get(16 - player1_own.getInt(i));
            Text t = (Text) s.getChildren().get(1);
            t.setFill(Color.rgb(75,134,115));
        }
    }


}