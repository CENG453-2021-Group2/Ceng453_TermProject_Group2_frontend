package group2.monopoly.frontend.Player;

import group2.monopoly.frontend.Pawn.Pawn;
import javafx.scene.Group;
import javafx.scene.text.Text;
import org.json.JSONArray;

import java.util.List;

public class Player {
    private String name;
    private int money;
    private int position;
    private Pawn pawn;
    private int board_width;
    private int board_height;
    private int board_grid_count;
    private DisplayMoney displayMoney;

    private String properties;
    private DisplayProperties displayProperties;

    public Player(String name, int money, String pawn_path, int board_width, int board_height, int board_grid_count) {
        this.name = name;
        this.money = money;
        this.position = 0;
        this.board_grid_count = board_grid_count;
        this.board_width = board_width;
        this.board_height = board_height;

        this.pawn = new Pawn(name, pawn_path, 0, board_grid_count, board_height, board_width);
        this.displayMoney = new DisplayMoney(board_width, board_height, board_grid_count);

        this.displayProperties = new DisplayProperties(board_width, board_height, board_grid_count);
        this.properties = "";
    }

    public Pawn getPawn() {
        return pawn;
    }

    public void updateProperties(JSONArray properties) {
        String s = "";
        for (int i = 0; i < properties.length(); i++) {
            s = s + properties.get(i) + "\n";
        }
        this.displayProperties.updateProperties(List.of(new String[]{s}));
    }

    public int getMoney() {
        return money;
    }

    public Text getMoneyDisplay() {
        displayMoney.displayMoney(this.money);
        return displayMoney.getGroup();
    }

    public Text getPropertiesDisplay() {
        //displayProperties.displayProperties(List.of(new String[]{"asad"}));
        return displayProperties.getGroup();
    }

}
