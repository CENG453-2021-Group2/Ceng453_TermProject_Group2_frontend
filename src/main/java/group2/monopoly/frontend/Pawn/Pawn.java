package group2.monopoly.frontend.Pawn;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import group2.monopoly.frontend.Utils.BoardToCoords;

public class Pawn {
    /**
     * This class holds the information for the player's pawn.
     * It actually has a one-to-one relationship with player class.
     */
    private String owner;
    private String png_path;
    private int position_square;
    private int grid_count;
    private int res_height;
    private int res_width;
    private int width_step;
    private int height_step;
    private int width_grid_center;
    private int height_grid_center;
    private BoardToCoords board_to_coords;

    private final int image_height = 40;

    private Group pawn_group;

    /**
     * This constructor saves all the arguments to itself, and creates a pawn group for GUI
     */
    public Pawn(String owner, String png_path, int position_square, int grid_count, int res_height, int res_width) {
        this.owner = owner;
        this.png_path = png_path;
        this.position_square = position_square;
        this.grid_count = grid_count;
        this.res_height = res_height;
        this.res_width = res_width;
        this.width_step = res_width / grid_count;
        this.height_step = res_height / grid_count;
        this.width_grid_center = width_step / 2 - image_height / 2;
        this.height_grid_center = height_step / 2 - image_height / 2;
        pawn_group = new Group();
        board_to_coords = new BoardToCoords(res_width, res_height, grid_count);
    }

    public Group draw(){
        this.pawn_group.getChildren().clear();
        // draw the pawn from the png!
        Image pawn = new Image(png_path);
        ImageView pawn_view = new ImageView(pawn);
        List<Integer> coords = this.board_to_coords.getCoords(this.position_square);
        assert coords != null;
        pawn_view.setX(coords.get(0));
        pawn_view.setY(coords.get(1));
        pawn_view.setFitHeight(this.image_height);
        pawn_view.setPreserveRatio(true);
        this.pawn_group.getChildren().add(pawn_view);
        return this.pawn_group;
    }

    public TranslateTransition transition(int new_square_position) {
        // execute a transition animation!

        // get curr coords
        List<Integer> curr_coords = this.board_to_coords.getCoords(this.position_square);

        // animate the pawn to the new coords!

        this.position_square = new_square_position;
        // get new coords
        List<Integer> new_coords = this.board_to_coords.getCoords(this.position_square);

        Duration duration = Duration.millis(100);
        TranslateTransition translateTransition = new TranslateTransition(duration, this.pawn_group);

        translateTransition.setByX(new_coords.get(0) - curr_coords.get(0));
        translateTransition.setByY(new_coords.get(1) - curr_coords.get(1));

        translateTransition.setAutoReverse(false);
        //translateTransition.play();
        return translateTransition;

    }


}
