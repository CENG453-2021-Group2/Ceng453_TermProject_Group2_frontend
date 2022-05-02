package group2.monopoly.frontend.Player;

import javafx.scene.text.Text;

import java.util.List;

public class DisplayProperties {
    private Text property_group;
    private int grid_count;
    private int width;
    private int height;

    public DisplayProperties(int width, int height, int grid_count) {
        this.grid_count = grid_count;
        this.width = width;
        this.height = height;

        this.property_group = new Text();
        this.property_group.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: red;");
        property_group.setX(this.width - this.width / this.grid_count);
        property_group.setY(this.height / this.grid_count / 2 * 3);
    }

    public void updateProperties(List<String> properties) {
        StringBuilder sb = new StringBuilder();
        sb.append("Properties: ");
        sb.append("\n");
        sb.append("\n");

        for (String property : properties) {
            sb.append(property);
            sb.append("\n");
        }
        this.property_group.setText(sb.toString());
    }

    public Text getGroup() {
        return this.property_group;
    }
}
