package group2.monopoly.frontend.BoardParts;

import group2.monopoly.frontend.BoardParts.BoardElement;
import javafx.scene.paint.Color;

public class StartPoint implements BoardElement{

    @Override
    public String getType() {
        return "Start point";
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return "Start\nPoint";
    }


    public Color getColor(){
        return Color.CORNFLOWERBLUE;
    }


}
