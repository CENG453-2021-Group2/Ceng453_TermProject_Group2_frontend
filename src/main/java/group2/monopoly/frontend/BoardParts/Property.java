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

    @Override
    public String getDisplayName() {
        return this.name + "\n$" + price;
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
