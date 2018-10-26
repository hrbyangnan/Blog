package Multimedia;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
* Generates a thumbnail image for use in the multimedia gallery when a user submits an image as part of an article.
* */

public class ThumbnailGenerator {

    public static void generateThumb (File fullSize, String fullSizeFileName, File targetFolder)throws IOException {

        //Tell Java that we want to treat the original file as an image file
        BufferedImage input = ImageIO.read(fullSize);

        //We need to decide how big the thumbnail should be based on the size of the input
        int originalWidth = input.getWidth();
        int originalHeight = input.getHeight();
        int thumbHeight = 100;
        int thumbWidth = (int) (thumbHeight * ((double) originalWidth / originalHeight));


        //Create a image file for the thumbnail to go in.
        BufferedImage thumbnail = new BufferedImage(thumbWidth, thumbHeight, input.getType());

        Graphics2D g2d = thumbnail.createGraphics();
        g2d.drawImage(input, 0, 0, thumbWidth, thumbHeight, null);
        g2d.dispose();

        String outputFileName = "thumb" + fullSizeFileName;
        //String formatName = outputFileName.substring(outputFileName.indexOf("." + 1));

        ImageIO.write(thumbnail, "jpg", new File( targetFolder, outputFileName));
    }




    }

