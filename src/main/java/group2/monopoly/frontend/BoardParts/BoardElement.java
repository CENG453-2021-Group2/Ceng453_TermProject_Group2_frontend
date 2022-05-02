package group2.monopoly.frontend.BoardParts;

import javafx.scene.paint.Color;

public interface BoardElement {

    public String getType();

    public String getName();

    public String getDisplayName();

    public Color getColor();

    public void placedPawn();

    public void removedPawn();
}
