package group2.monopoly.frontend.Player;

import group2.monopoly.frontend.Pawn.Pawn;

public class Player {
    private String name;
    private int money;
    private int position;
    private Pawn pawn;
    private int board_width;
    private int board_height;
    private int board_grid_count;

    public Player(String name, int money, String pawn_path, int board_width, int board_height, int board_grid_count) {
        this.name = name;
        this.money = money;
        this.position = 0;
        this.board_grid_count = board_grid_count;
        this.board_width = board_width;
        this.board_height = board_height;

        this.pawn = new Pawn(name, pawn_path, 0, board_grid_count, board_height, board_width);

    }

    public Pawn getPawn() {
        return pawn;
    }

    public int getMoney() {
        return money;
    }

}
