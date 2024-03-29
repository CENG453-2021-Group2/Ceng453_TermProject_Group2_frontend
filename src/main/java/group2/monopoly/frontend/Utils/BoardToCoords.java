package group2.monopoly.frontend.Utils;

import group2.monopoly.frontend.BoardRectangles;

import java.util.ArrayList;
import java.util.List;

/**
  * This class translates the monopoly square positions to the actual screen rectangle coordinates.
  * The screen's rectangle positions are written hardcoded, because each of them are in a different place in screen grid, with different indexes.
 */
public class BoardToCoords {
    private int width;
    private int height;
    private int grid_count;
    private int height_step;
    private int width_step;

    /**
      * This constructor saves all the arguments to the class
      * It calculates some of the parameters by these parameters
        * @param width The width of the board
        * @param height The height of the board
        * @param grid_count The number of squares in the grid
    */
    public BoardToCoords(int width, int height, int grid_count) {
        this.width = width;
        this.height = height;
        this.grid_count = grid_count;
        this.width_step = width / grid_count;
        this.height_step = height / grid_count;
    }

    /*
    public List<Integer> getCoords(int position_square){
        int ctr = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 4; j >= 0; j--) {
                if (i == 0 || i == 4 || j == 0 || j == 4) {
                    if (ctr == position_square) {
                        int finalI = i;
                        int finalJ = j;
                        return new ArrayList<Integer>() {{
                            add(width_step * (finalI + 1));
                            add(height_step * (finalJ + 1));
                        }};
                    }
                    ctr++;
                }
            }
        }
        return null;
    }

     */

    /**
        * This method returns the coordinates of the square on the board
        * All the coordinates are hardcoded, because they are in a different place in screen grid, with different indexes.
        * @param position_square The square number of the player's current position
        * @return List<Integer>
     */
    public List<Integer> getCoords(int position_square){
        position_square = position_square % 16;
        int ctr = 0;
        if (position_square == 0) {
            return new ArrayList<Integer>() {{
                add(width_step * (1));
                add(height_step * (5));
            }};
        } else if (position_square == 1) {
            return new ArrayList<Integer>() {{
                add(width_step * (1));
                add(height_step * (4));
            }};
        } else if (position_square == 2) {
            return new ArrayList<Integer>() {{
                add(width_step * (1));
                add(height_step * (3));
            }};
        } else if (position_square == 3) {
            return new ArrayList<Integer>() {{
                add(width_step * (1));
                add(height_step * (2));
            }};
        } else if (position_square == 4) {
            return new ArrayList<Integer>() {{
                add(width_step * (1));
                add(height_step * (1));
            }};
        } else if (position_square == 5) {
            return new ArrayList<Integer>() {{
                add(width_step * (2));
                add(height_step * (1));
            }};
        } else if (position_square == 6) {
            return new ArrayList<Integer>() {{
                add(width_step * (3));
                add(height_step * (1));
            }};
        } else if (position_square == 7) {
            return new ArrayList<Integer>() {{
                add(width_step * (4));
                add(height_step * (1));
            }};
        } else if (position_square == 8) {
            return new ArrayList<Integer>() {{
                add(width_step * (5));
                add(height_step * (1));
            }};
        } else if (position_square == 9) {
            return new ArrayList<Integer>() {{
                add(width_step * (5));
                add(height_step * (2));
            }};
        } else if (position_square == 10) {
            return new ArrayList<Integer>() {{
                add(width_step * (5));
                add(height_step * (3));
            }};
        } else if (position_square == 11) {
            return new ArrayList<Integer>() {{
                add(width_step * (5));
                add(height_step * (4));
            }};
        } else if (position_square == 12) {
            return new ArrayList<Integer>() {{
                add(width_step * (5));
                add(height_step * (5));
            }};
        } else if (position_square == 13) {
            return new ArrayList<Integer>() {{
                add(width_step * (4));
                add(height_step * (5));
            }};
        } else if (position_square == 14) {
            return new ArrayList<Integer>() {{
                add(width_step * (3));
                add(height_step * (5));
            }};
        } else if (position_square == 15) {
            return new ArrayList<Integer>() {{
                add(width_step * (2));
                add(height_step * (5));
            }};
        }
        return null;
    }



}
