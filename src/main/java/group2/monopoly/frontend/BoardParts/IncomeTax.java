package group2.monopoly.frontend.BoardParts;

import group2.monopoly.frontend.BoardParts.BoardElement;
import javafx.scene.paint.Color;

public class IncomeTax implements BoardElement{

    public IncomeTax(){}

    @Override
    public String getType() {
        return "Income Tax";
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return "Income\nTax";
    }


    public Color getColor(){
        return Color.CORNFLOWERBLUE;
    }


}
