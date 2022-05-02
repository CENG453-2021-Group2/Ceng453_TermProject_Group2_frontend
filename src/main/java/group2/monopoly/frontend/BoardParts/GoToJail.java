package group2.monopoly.frontend.BoardParts;

import group2.monopoly.frontend.BoardParts.BoardElement;
import javafx.scene.paint.Color;

public class GoToJail implements BoardElement{

    private final Color empty_color = Color.web("#DAEAF6");
    private final Color occupied_color = Color.web("#7EC5CF");
    private boolean is_occupied;

    public GoToJail(){
        this.is_occupied = false;
    }

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
