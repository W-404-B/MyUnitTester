import java.awt.*;

public class MyColorChanger {
    private Color color;
    private Color defaultColor = Color.black;
    public MyColorChanger() {
        this.color = defaultColor;
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public void resetColor() {
        this.color = defaultColor;
    }

    public Color getColor() {
        return color;
    }
}
