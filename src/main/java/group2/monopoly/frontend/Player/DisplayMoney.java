package group2.monopoly.frontend.Player;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import group2.monopoly.frontend.Utils.BoardToCoords;

import java.util.ArrayList;
import java.util.List;

public class DisplayMoney {
    private Text money_group;
    private int grid_count;
    private int width;
    private int height;
    private boolean is_left;

    private List<String> property_list;


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
        }
        else {
            money_group.setX(0);
        }
        money_group.setY(this.height / 3-70);
    }

    public void displayMoney(int money) {


        this.money_group.setText("$" + String.valueOf(money));

        BoardToCoords boardToCoords = new BoardToCoords(width, height, grid_count);

    }

    public Text getGroup() {
        return money_group;
    }
}
