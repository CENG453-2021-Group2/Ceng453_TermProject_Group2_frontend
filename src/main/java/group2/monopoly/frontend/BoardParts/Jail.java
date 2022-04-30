package group2.monopoly.frontend.BoardParts;

import group2.monopoly.frontend.BoardParts.BoardElement;
import javafx.scene.paint.Color;

public class Jail implements BoardElement{

    @Override
    public String getType() {
        return "Jail";
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return "Jail/Just\nvisiting";
    }


    public Color getColor(){
        return Color.CORNFLOWERBLUE;
    }

}
