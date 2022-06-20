package group2.monopoly.frontend.BoardParts;

import group2.monopoly.frontend.BoardParts.BoardElement;
import javafx.scene.paint.Color;

/**
 * This implements the property element in the board.
 *
 */
public class Property implements BoardElement{

    private String price;
    private final Color empty_color = Color.web("#809BCE");
    private final Color occupied_color = Color.web("#6891C3");
    private boolean is_occupied;

    private String name;

    /**
     * Constructor for the property element.
     * Always starts as unoccupied.
     * @param name The name of the property.
     * @param price The price of the property.
     */
    public Property(String name, String price){
        this.price = price;
        this.name = name;
        this.is_occupied = false;
    }

    /**
     * @return Always returns "Property"
     */
    @Override
    public String getType() {return "Property"; }

    /**
     * @return name + "\n$" + price, because this is the way it will be shown in the board.
     */
    @Override
    public String getDisplayName() {
        return this.name + "\n$" + price;
    }

    /**
     * Returns the predetermined colors, depending on whether the element is occupied or not.
        * If empty, returns the empty color, #809BCE.
        * If occupied, returns the occupied color, #6891C3.
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
