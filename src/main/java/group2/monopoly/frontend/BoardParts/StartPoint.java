package group2.monopoly.frontend.BoardParts;

import group2.monopoly.frontend.BoardParts.BoardElement;
import javafx.scene.paint.Color;

/**
 * This implements the start point element in the board.
 *
 */
public class StartPoint implements BoardElement{

    private final Color empty_color = Color.web("#DAEAF6");
    private final Color occupied_color = Color.web("#7EC5CF");
    private boolean is_occupied;

    /**
     * Constructor for the start point element.
     * Always starts as unoccupied.
     */
    public StartPoint(){
        this.is_occupied = false;
    }

    /**
     * @return Always returns "Start point"
     */
    @Override
    public String getType() {
        return "Start point";
    }

    /**
     * @return Always returns "Start\npoint". Newline is needed to make it fit in the board.
     */
    @Override
    public String getDisplayName() {
        return "Start\nPoint";
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
     * @return Always returns "Start point"
     */
    public void removedPawn(){
        this.is_occupied = false;
    }
}
