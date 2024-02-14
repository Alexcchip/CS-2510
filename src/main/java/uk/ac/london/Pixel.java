package uk.ac.london;
import java.awt.*;
import java.util.Formatter;

public class Pixel {

    Color color;
    int x;
    int y;

    public Pixel(Color color) {
        this.color = color;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void addY(int y) {
        this.y =+ y;
    }
    public void addX(int x) {
        this.x =+ x;
    }
    public Color getColor() {return this.color;}
    public Pixel() {
        this.x = 0;
        this.y = 0;
    }
    public Pixel(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }


}
