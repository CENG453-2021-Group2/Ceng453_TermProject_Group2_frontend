package group2.monopoly.frontend.Player;

import javafx.scene.text.Text;

import java.util.List;

public class DisplayProperties {
    private Text property_group;
    private int grid_count;
    private int width;
    private int height;
    private boolean is_left;

    public DisplayProperties(int width, int height, int grid_count, boolean is_left) {
        this.grid_count = grid_count;
        this.width = width;
        this.height = height;
        this.is_left = is_left;

        this.property_group = new Text();
        this.property_group.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: red;");
        if (this.is_left) {
            property_group.setX(this.width - this.width / this.grid_count);
        }
        else {
            property_group.setX(0);
        }
        property_group.setY(this.height / 3);
        System.out.println("Display property");
        System.out.println(is_left);
        System.out.println(property_group.getX());
        System.out.println(property_group.getY());
    }

    public void updateProperties(List<String> properties) {
        StringBuilder sb = new StringBuilder();
        if (this.is_left) {
            sb.append("Robot's \n");
        }
        else {
            sb.append("Player's \n");
        }
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
