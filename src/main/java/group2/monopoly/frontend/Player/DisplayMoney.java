package group2.monopoly.frontend.Player;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import group2.monopoly.frontend.Utils.BoardToCoords;

public class DisplayMoney {
    private Text money_group;
    private int grid_count;
    private int width;
    private int height;


    public DisplayMoney(int width, int height, int grid_count) {
        this.money_group = new Text();
        this.grid_count = grid_count;
        this.width = width;
        this.height = height;
    }

    public void displayMoney(int money) {

        this.money_group.setText("$" + String.valueOf(money));
        this.money_group.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: red;");

        BoardToCoords boardToCoords = new BoardToCoords(width, height, grid_count);

        money_group.setX(this.width - this.width / this.grid_count);
        money_group.setY(this.height / this.grid_count / 2);
    }

    public Text getGroup() {
        return money_group;
    }
}
