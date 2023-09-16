package startypes;
import java.awt.*;

public abstract class StarType {

    private int size;
    private Color color;
    protected String description;
    protected Float[] physicalProperties;
    private int x;
    private int y;

    public StarType(int size, Color color) {
        this.size = size;
        this.color = color;
    }
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y , size, size);
    }

    public Color getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public Float[] getPhysicalProperties(){
        return this.physicalProperties;
    }

    
}
