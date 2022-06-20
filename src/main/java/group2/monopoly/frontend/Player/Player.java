package group2.monopoly.frontend.Player;

import group2.monopoly.frontend.Pawn.Pawn;
import javafx.scene.Group;
import javafx.scene.text.Text;

import java.util.List;

/**
  * This class holds the player's all information and actions

 */
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

    private boolean is_robot;

    private Text moneyGroup;

    /**
      * This constructor saves all the arguments to itself, and creates helper classes for display
        * @param name The player's name
        * @param money The player's money
        * @param pawn_path The path to the png file, which is the pawn's image
        * @param board_width The width of the board
        * @param board_height The height of the board
        * @param board_grid_count The number of squares in the grid
        * @param is_robot Whether the player is a robot
     */
    public Player(String name, int money, String pawn_path, int board_width, int board_height, int board_grid_count, boolean is_robot) {
        this.name = name;
        this.money = money;
        this.position = 0;
        this.board_grid_count = board_grid_count;
        this.board_width = board_width;
        this.board_height = board_height;
        this.is_robot = is_robot;

        this.pawn = new Pawn(name, pawn_path, 0, board_grid_count, board_height, board_width);
        this.displayMoney = new DisplayMoney(board_width, board_height, board_grid_count, this.is_robot);

        this.displayProperties = new DisplayProperties(board_width, board_height, board_grid_count, this.is_robot);
        this.properties = "";
    }

    /**
      * This method returns the player's pawn
        * @return Pawn
        */
    public Pawn getPawn() {
        return pawn;
    }

    /**
     * This method updates the player's properties
     */
    public void updateProperties(List<String> properties) {
        String s = "";
        for (int i = 0; i < properties.size(); i++) {
            s = s + properties.get(i) + "\n";
        }
        this.displayProperties.updateProperties(List.of(new String[]{s}));
    }

    /**
     * This method updates the player's money
     */
    public void setMoney(int money) {
        this.money = money;
        System.out.println("the player's money is");
        System.out.println(this.money);
        this.moneyGroup.setText("$" + String.valueOf(this.money));

    }

    /**
     * This method returns the player's money
     * @return int
    */
    public int getMoney() {
        System.out.println("the player's money get is");
        System.out.println(this.money);
        return this.money;
    }

    /**
     * This method returns the player's money display group
     * @return Group
     */
    public Text getMoneyDisplay() {
        displayMoney.displayMoney(this.money);
        this.moneyGroup = displayMoney.getGroup();
        return this.moneyGroup;
    }

    /**
     * This method returns the player's properties display group
     * @return Group
     */
    public Text getPropertiesDisplay() {
        return displayProperties.getGroup();
    }

}
