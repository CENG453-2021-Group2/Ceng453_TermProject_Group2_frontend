package group2.monopoly.frontend.BoardParts;

import group2.monopoly.frontend.BoardParts.BoardElement;
import javafx.scene.paint.Color;

public class Property implements BoardElement{
    private String price;
    private Color color;

    public Property(String price){
        this.price = price;
        this.color = Color.CORNFLOWERBLUE;
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
        return color;
    }

    public Color setColor(Color color){
        return this.color = color;
    }


}
