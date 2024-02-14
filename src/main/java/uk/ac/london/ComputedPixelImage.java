package uk.ac.london;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.ArrayList;

public class ComputedPixelImage {
    private ArrayList<ArrayList<Color>> grid;
    private int x;
    private int y;
    private int height;
    private int width;
    private Color color;


    public ComputedPixelImage() {
        this.grid = new ArrayList<ArrayList<Color>>();
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                grid[i][j] = new Pixel(x+j,y+i,color);
//            }
//        }
    }
    public void setPixel(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
    public void setPixels(int x, int y, int height, int width, Color color) {

    }

//    @Override
//    public String print() {
//        String result = "";
//        for (int i = 0; i< height; i++) {
//            for (int j = 0; j < width; j++) {
//                result += Integer.toString(grid[i][j].getX());
//            }
//        }
//        result+= "test";
//        return result;
//    }
}
