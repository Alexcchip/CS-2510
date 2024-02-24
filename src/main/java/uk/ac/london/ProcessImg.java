package uk.ac.london;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.util.EmptyStackException;
import java.util.Stack;


public class ProcessImg {
    public  ArrayList<ArrayList<Pixel>> grid;
//    private int count = -1;
    private int width;
    private int height;
    private int blueIdx;
    private BufferedImage bluestImg;
    private BufferedImage redImg;

    Stack<BufferedImage> history = new Stack<>();

    public void setPixel(int x, int y, Pixel color) {
        if ((y>0&&y<grid.size())&&(x>0&&x<grid.get(0).size())) {
            grid.get(y).set(x, color);
        }
    }

    public void setPixels(int x, int y, int width, int height, Pixel color) {
        for (int i = 0; i <y+height && i <grid.size();i++) {
            for (int j = 0; j<x+width&& j <grid.get(0).size();j++) {
                grid.get(i).set(j, color);
            }
        }
    }
    public int getBlueIdx() {
        return blueIdx;
    }
    public BufferedImage getBluestImg() {return bluestImg;}
    public BufferedImage getRedImg() {return redImg;}
    public BufferedImage undo() {
        if(history.isEmpty()) {
            System.out.println("Stack is empty");
            throw new EmptyStackException();
        }
        return history.peek();
    }
    public void popHistory() {
        if (history.isEmpty()) {
            System.out.println("Stack is empty");
        }
        if (history.size() == 1) {
            return;
        }
            if (history.size() != 2) {
                history.pop();
                history.pop();
                return;
            }
            history.pop();
    }

    public void historyAdd(BufferedImage img) {this.history.add(img);}
    public void removeAtIdx(int idx) {
        for (int i = 0; i<this.getHeight(); i++) {
            for (int j = 0; j<this.getWidth(); j++) {
                if (j==idx) {
                    grid.get(i).remove(j);
                }
            }
        }
    }

    public ProcessImg(BufferedImage oldImg) {
        ArrayList<ArrayList<Pixel>> newGrid = new ArrayList<>();
        this.width = oldImg.getWidth();
        this.height = oldImg.getHeight();
        for (int y = 0; y < oldImg.getHeight(); y++) {
            ArrayList<Pixel> inner = new ArrayList<>();
            for (int x = 0; x < oldImg.getWidth(); x++) {

                int pixel = oldImg.getRGB(x, y);
                Color originalColor = new Color(pixel);

                int red = originalColor.getRed();
                int green = originalColor.getGreen();
                int blue = originalColor.getBlue();
                inner.add(x,new Pixel(red,green,blue));
            }
            newGrid.add(y,inner);
        }
        this.grid = newGrid;
    }

    public int getHeight() {
        return grid.size();
    }

    public int getWidth() {
        return grid.get(0).size();
    }
    public BufferedImage gridToImg() {
            BufferedImage returnBuffImg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < this.getHeight(); i++) {
                for (int j = 0; j < this.getWidth(); j++) {
                    int red = grid.get(i).get(j).getRed();
                    int green = grid.get(i).get(j).getGreen();
                    int blue = grid.get(i).get(j).getBlue();
                    Color inputColor = new Color(red, green, blue);
                    returnBuffImg.setRGB(j, i, inputColor.getRGB());
                }
            }
            return returnBuffImg;
    }
//    public BufferedImage removeCol(BufferedImage givenImg) {
////        BufferedImage removedColImg = new BufferedImage(givenImg.getWidth()
////                , givenImg.getHeight()
////                , BufferedImage.TYPE_INT_RGB);
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                int pixel = givenImg.getRGB(j, i);
//                Color originalColor = new Color(pixel);
//
//                int red   = originalColor.getRed();
//                int green = originalColor.getGreen();
//                int blue  = originalColor.getBlue();
//
//                // Alter the color and then store in new image buffer
//                Color newColor = new Color(red, green , blue);
//                givenImg.setRGB(j,i,newColor.getRGB());
//            }
//
//        }
//        return givenImg;
//    }
    public void bluest(BufferedImage oldImg) {
        int[] topBlue = new int[this.getWidth()];
        int bluest = 0;
        this.blueIdx = 0;
        Color blue = new Color(0,0,255);
        for (int y = 0; y < oldImg.getHeight(); y++) {
            for (int x = 0; x < oldImg.getWidth(); x++) {
                topBlue[x] += grid.get(y).get(x).getBlue();
                }
            }
        for (int i = 0; i<topBlue.length;i++){
            if (topBlue[i]>bluest) {
                blueIdx = i;
                bluest = topBlue[i];
            }
        }
        for (int y = 0; y < oldImg.getHeight(); y++) {
            for (int x = 0; x < oldImg.getWidth(); x++) {
                if (x==blueIdx) {
                    oldImg.setRGB(x,y,blue.getRGB());
                    }
                }
            }
        this.bluestImg=oldImg;
    }

    public void highlightRed(BufferedImage oldImg, int idx) {
        Color red = new Color(255,0,0);
        for (int y = 0; y < oldImg.getHeight(); y++) {
            for (int x = 0; x <oldImg.getWidth();x++) {
                if (x==idx) {
                    oldImg.setRGB(x,y,red.getRGB());
                }
            }
        }
        this.redImg = oldImg;
    }
    public static void exportImg(BufferedImage finalImg, String fileName) throws Exception{
        File newFile = new File(fileName+".png");
        ImageIO.write(finalImg, "png", newFile);
        System.out.println(fileName+" saved successfully");
    }

//    public static void main(String args[]) throws Exception {
//
//        File originalFile = new File("bucharest.png");
//        BufferedImage oldImg = ImageIO.read(originalFile);
//
//        // new file to store altered image
//        File newFile = new File("newImg.png");
//
//        // new buffer for alteredImage
//        BufferedImage newImg = new BufferedImage(oldImg.getWidth()
//                , oldImg.getHeight()
//                , BufferedImage.TYPE_INT_RGB);
//    }
}

