package group2.monopoly.frontend.BoardParts;

import group2.monopoly.frontend.BoardParts.BoardElement;
import javafx.scene.paint.Color;

/**
 * This implements the railroad/ferry element in the board.
 *
 */
public class RailroadFerry implements BoardElement{
    private boolean is_occupied;
    private final Color empty_color = Color.web("DDEDEA");
    private final Color occupied_color = Color.web("B8E0D2");

    /**
     * @return Always returns "Railroad/Ferry"
     */
    @Override
    public String getType() {
        return "Railroad/Ferry";
    }

    @Override
    public String getDisplayName() {
        return "Railroad\nFerry";
    }


    public Color getColor(){
        if (this.is_occupied)
            return occupied_color;
        else
            return empty_color;
    }

    public void placedPawn(){
        this.is_occupied = true;
    }

    public void removedPawn(){
        this.is_occupied = false;
    }
}
