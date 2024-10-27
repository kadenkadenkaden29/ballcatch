import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FallingObject {
    private Circle shape;
    private boolean caught;

    public FallingObject(double x, double y) {
        shape = new Circle(10, Color.RED);
        shape.setCenterX(x);
        shape.setCenterY(y);
        caught = false;
    }

    public Circle getShape() {
        return shape;
    }

    public void update() {
        shape.setCenterY(shape.getCenterY() + 1); // Slower movement
    }

    public boolean isCaught() {
        return caught;
    }

    public void setCaught(boolean caught) {
        this.caught = caught;
    }
}