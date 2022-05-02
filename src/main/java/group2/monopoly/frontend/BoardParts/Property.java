package group2.monopoly.frontend.BoardParts;

import group2.monopoly.frontend.BoardParts.BoardElement;
import javafx.scene.paint.Color;

public class Property implements BoardElement{
    private String price;
    private final Color empty_color = Color.web("#809BCE");
    private final Color occupied_color = Color.web("#6891C3");
    private boolean is_occupied;

    public Property(String price){
        this.price = price;
        this.is_occupied = false;
    }

    @Override
    public String getType() {
        return "Property";
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return "Property\n$" + price;
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
