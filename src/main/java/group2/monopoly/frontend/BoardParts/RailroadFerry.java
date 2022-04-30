package group2.monopoly.frontend.BoardParts;

import group2.monopoly.frontend.BoardParts.BoardElement;
import javafx.scene.paint.Color;

public class RailroadFerry implements BoardElement{

    @Override
    public String getType() {
        return "Railroad/Ferry";
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return "Railroad\nFerry";
    }


    public Color getColor(){
        return Color.CORNFLOWERBLUE;
    }

}
