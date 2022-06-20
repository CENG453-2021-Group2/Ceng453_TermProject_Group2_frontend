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
     * Constructor for the railroad/ferry element.
        * Always starts as unoccupied.  
        */
    public RailroadFerry(){
        this.is_occupied = false;
    }

    /**
     * @return Always returns "Railroad/Ferry"
     */
    @Override
    public String getType() {
        return "Railroad/Ferry";
    }

    /**
     * @return Always returns "Railroad\nFerry". Newline is needed to make it fit in the board.
     */
    @Override
    public String getDisplayName() {
        return "Railroad\nFerry";
    }


    /**
     * Returns the predetermined colors, depending on whether the element is occupied or not.
        * If empty, returns the empty color, #DDEDEA.
        * If occupied, returns the occupied color, #B8E0D2.
        * @return Color
        */
    public Color getColor(){
        if (this.is_occupied)
            return occupied_color;
        else
            return empty_color;
    }

    /**
     * Sets the element to occupied.
     */
    public void placedPawn(){
        this.is_occupied = true;
    }

    /**
     * Sets the element to unoccupied.
     */
    public void removedPawn(){
        this.is_occupied = false;
    }
}
