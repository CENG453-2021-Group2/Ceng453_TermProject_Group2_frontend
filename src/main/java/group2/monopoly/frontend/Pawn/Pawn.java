package group2.monopoly.frontend.Pawn;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Pawn {
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

    private final int image_height = 40;

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
    }

    private List<Integer> getCoords(){
        int ctr = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 4; j > 0; j--) {
                if (i == 0 || i == 4 || j == 0 || j == 4) {
                    if (ctr == position_square) {
                        int finalI = i;
                        int finalJ = j;
                        return new ArrayList<Integer>() {{
                            add(width_step * (finalI + 1) + width_grid_center);
                            add(height_step * (finalJ + 1) + height_grid_center);
                        }};
                    }
                    ctr++;
                }
            }
        }
        return null;
    }

    public Group draw() {
        // draw the pawn from the png!
        Image pawn = new Image(png_path);
        ImageView pawn_view = new ImageView(pawn);
        List<Integer> coords = getCoords();
        assert coords != null;
        pawn_view.setX(coords.get(0));
        pawn_view.setY(coords.get(1));
        pawn_view.setFitHeight(this.image_height);
        pawn_view.setPreserveRatio(true);

        return new Group(pawn_view);
    }

    /*
    public void transition(int position_x, int position_y) {
        // execute a transition animation!

        this.position_x = position_x;
        this.position_y = position_y;
    }

     */
}
