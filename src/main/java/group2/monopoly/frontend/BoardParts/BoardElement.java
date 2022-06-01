package group2.monopoly.frontend.BoardParts;

import javafx.scene.paint.Color;

/**
 * This interface is for board elements such as a property, start point, go to jail etc.
 * It has several functions, getType, getDisplayName, getColor, placedPawn and removedPawn.
 */
public interface BoardElement {

    /**
     * @return the type of the class
     */
    public String getType();

    public String getDisplayName();

    public Color getColor();

    public void placedPawn();

    public void removedPawn();
}
