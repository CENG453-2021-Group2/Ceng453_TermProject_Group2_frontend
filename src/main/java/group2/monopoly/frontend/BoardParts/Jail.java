package group2.monopoly.frontend.BoardParts;

import group2.monopoly.frontend.BoardParts.BoardElement;
import javafx.scene.paint.Color;

/**
 * This implements the jail element in the board.
 *
 */
public class Jail implements BoardElement{


    private final Color empty_color = Color.web("#DAEAF6");
    private final Color occupied_color = Color.web("#7EC5CF");
    private boolean is_occupied;

    /**
     * Constructor for the jail element.
     * Always starts as unoccupied.
     */
    public Jail(){
        this.is_occupied = false;
    }

    /**
     * @return Always returns "Jail"
     */
    @Override
    public String getType() {
        return "Jail";
    }

    /**
     * @return Always returns "Jail"
     */
    @Override
    public String getDisplayName() {
        return "Jail/Just\nvisiting";
    }

    /**
     * Returns the predetermined colors, depending on whether the element is occupied or not.
     * If empty, returns the empty color, #DAEAF6.
     * If occupied, returns the occupied color, #7EC5CF.
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
