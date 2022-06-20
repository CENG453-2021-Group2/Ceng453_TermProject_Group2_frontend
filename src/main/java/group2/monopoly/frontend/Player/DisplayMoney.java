package group2.monopoly.frontend.Player;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
    * This class holds the information for the player's money.
    * It has a one-to-one relationship with player class.
 */
public class DisplayMoney {
    private Text money_group;
    private int grid_count;
    private int width;
    private int height;
    private boolean is_left;

    private List<String> property_list;

    /**
        * This constructor saves all the arguments to class variables, and creates a money group for GUI
        * The color of the money group is determined by the is_left boolean.
        * @param grid_count The number of squares in the grid
        * @param width The width of the board
        * @param height The height of the board
        * @param is_left Whether the money is on the left or right side of the board
    */
    public DisplayMoney(int width, int height, int grid_count, boolean is_left) {
        this.grid_count = grid_count;
        this.width = width;
        this.height = height;
        this.property_list = new ArrayList<String>();
        this.is_left = is_left;

        this.money_group = new Text();
        this.money_group.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: red;");
        if (this.is_left) {
            money_group.setX(this.width - this.width / this.grid_count);
            this.money_group.setFill(Color.rgb(75,134,115));
        }
        else {
            money_group.setX(0);
            this.money_group.setFill(Color.rgb(255,140,140));
        }
        money_group.setY(this.height / 3-70);
    }

    /**
      * This method updates the player's money display.
     */
    public void displayMoney(int money) {
        this.money_group.setText("$" + String.valueOf(money));

    }

    /**
      * This method returns the player's money display.
     */
    public Text getGroup() {
        return money_group;
    }
}
