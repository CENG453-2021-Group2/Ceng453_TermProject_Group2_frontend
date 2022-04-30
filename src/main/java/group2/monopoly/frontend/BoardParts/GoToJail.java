package group2.monopoly.frontend.BoardParts;

import group2.monopoly.frontend.BoardParts.BoardElement;
import javafx.scene.paint.Color;

public class GoToJail implements BoardElement{

    @Override
    public String getType() {
        return "Go to jail";
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return "Go to jail";
    }


    public Color getColor(){
        return Color.CORNFLOWERBLUE;
    }


}
