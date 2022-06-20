package group2.monopoly.frontend.BoardParts;

import group2.monopoly.frontend.BoardParts.BoardElement;
import javafx.scene.paint.Color;

/**
 * This implements the income tax element in the board.
 *
 */
public class IncomeTax implements BoardElement{


    private final Color empty_color = Color.web("#DAEAF6");
    private final Color occupied_color = Color.web("#7EC5CF");
    private boolean is_occupied;

    /**
      * Constructor for the income tax element.
      * Always starts as unoccupied.
    */
    public IncomeTax(){
        this.is_occupied = false;
    }

    /**
     * @return Always returns "Income Tax"
     */
    @Override
    public String getType() {
        return "Income Tax";
    }

    /**
     * @return Always returns "Income\n Tax", newline is necessary to make it fit in the screen.
     */
    @Override
    public String getDisplayName() {
        return "Income\nTax";
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
