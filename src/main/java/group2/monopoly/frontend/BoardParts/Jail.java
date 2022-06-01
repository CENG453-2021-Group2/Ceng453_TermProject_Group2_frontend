package group2.monopoly.frontend.BoardParts;

import group2.monopoly.frontend.BoardParts.BoardElement;
import javafx.scene.paint.Color;

/**
 * This implements the jail element in the board.
 *
 */
public class Jail implements BoardElement{


    private final Color empty_color = Color.web("#DAEAF6");
    private final Color occupied_color = Color.web("#7EC5CF");
    private boolean is_occupied;

    public Jail(){
        this.is_occupied = false;
    }

    /**
     * @return Always returns "Jail"
     */
    @Override
    public String getType() {
        return "Jail";
    }

    @Override
    public String getDisplayName() {
        return "Jail/Just\nvisiting";
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
