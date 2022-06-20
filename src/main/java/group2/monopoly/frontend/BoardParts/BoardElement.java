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

    /**
     * @return the display name of the class, as written in the board
     */
    public String getDisplayName();

    /**
     * @return the color of the class, depending on whether it is occupied or not
     */
    public Color getColor();

    /**
     * Sets the element to occupied.
     */
    public void placedPawn();

    /**
     * Sets the element to unoccupied.
     */
    public void removedPawn();
}
