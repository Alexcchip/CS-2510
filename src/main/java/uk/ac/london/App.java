package uk.ac.london;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.   Random;
import java.util.Scanner;
import static uk.ac.london.ProcessImg.exportImg;
import static uk.ac.london.UI.sendUI;
//import static uk.ac.london.ProcessImg.*;
public final class App {
//C:\Users\alexc\OneDrive\Desktop\Comp_Sci\Fundies 2\Project_1\Resources\beach.png
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome! Enter file path:\n");
        String path = sc.nextLine();
        File originalFile = new File(path);
        BufferedImage oldImg = ImageIO.read(originalFile);
        BufferedImage newImg = new BufferedImage(oldImg.getWidth()
                , oldImg.getHeight()
                , BufferedImage.TYPE_INT_RGB);
        String response = "b";
        int count = 0;
        ProcessImg inputImg = new ProcessImg(oldImg);
        while ((response.toLowerCase().equals("b"))||(response.toLowerCase().equals("u"))||(response.toLowerCase().equals("r"))) {
            sendUI();
            response = sc.nextLine();
            if ((response.toLowerCase().equals("b"))||(response.toLowerCase().equals("u"))||(response.toLowerCase().equals("r"))) {
                System.out.println("Are you sure you want to continue (y/n)?");
                String continueResponse = sc.nextLine();
                if (response.equalsIgnoreCase("b")) {
                    inputImg.historyAdd(inputImg.gridToImg());
                    inputImg.bluest(inputImg.gridToImg());
                    exportImg(inputImg.getBluestImg(),"Exported_"+count);
                    count++;
                    if (continueResponse.toLowerCase().equals("y")) {
                        inputImg.historyAdd(inputImg.getBluestImg());
                        inputImg.removeAtIdx(inputImg.getBlueIdx());
                        inputImg.gridToImg();
                        inputImg.historyAdd(inputImg.gridToImg());
                        exportImg(inputImg.gridToImg(), "Exported_" + count);
                        count++;
                        } else {
                        inputImg.popHistory();
                        exportImg(inputImg.undo(),"Exported_"+count);
                    }
                    } else if (response.toLowerCase().equals("u")) {
                        inputImg.popHistory();
                        exportImg(inputImg.undo(),"Exported_"+count);
                        count++;
                    } else if (response.toLowerCase().equals("r")) {
                        Random rand = new Random();
                        int randRemovedIdx = rand.nextInt(inputImg.getWidth());
                        inputImg.historyAdd(inputImg.gridToImg());
                        inputImg.highlightRed(inputImg.gridToImg(),randRemovedIdx);
                        exportImg(inputImg.getRedImg(),"Exported_"+count);
                        count++;
                        inputImg.historyAdd(inputImg.getRedImg());
                    if (continueResponse.toLowerCase().equals("y")) {
                        inputImg.removeAtIdx(randRemovedIdx);
                        inputImg.gridToImg();
                        inputImg.historyAdd((inputImg.gridToImg()));
                        exportImg(inputImg.gridToImg(), "Exported_" + count);
                        System.out.println("removed idx: " + randRemovedIdx);
                        count++;
                        } else {
                            inputImg.popHistory();
                            exportImg(inputImg.undo(),"Exported_"+count);
                        }
                    }
                } else {
                    break;
                }
                if (response.toLowerCase().equals("q")) {
                    break;
                }
            }
        exportImg(inputImg.gridToImg(),"FinalImg");
        sc.close();
        }
}
