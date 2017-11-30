package ru.github.amaaykts;

import ru.github.merge.PictureFormat;
import ru.github.merge.PictureMerge;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Example 1
 * <p>
 * PictureMerge pictureMerge = new PictureMerge()
 * .setPictureHeight(220)
 * .setPictureWidth(400)
 * .setFormat(PictureFormat.PNG)
 * .setColumnCount(3);
 * List<BufferedImage> bufferedImages = pictureMerge.convertInputStreamToBufferedImage(new ArrayList<InputStream>() {{
 * add(new FileInputStream(new File("C:\\img_0_0_52.png")));
 * add(new FileInputStream(new File("C:\\img_0_1_1.png")));
 * add(new FileInputStream(new File("C:\\img_0_1_0.png")));
 * add(new FileInputStream(new File("C:\\img_0_2_1.png")));
 * }});
 * BufferedImage image = pictureMerge.merge(bufferedImages);
 * ImageIO.write(image, "png", new File("big.png"));
 */
public class Main {
    public static void main(String[] args) throws IOException {
        PictureMerge pictureMerge = new PictureMerge()
                .setPictureHeight(220)
                .setPictureWidth(400)
                .setFormat(PictureFormat.PNG)
                .setColumnCount(3);
        List<BufferedImage> bufferedImages = pictureMerge.convertInputStreamToBufferedImage(new ArrayList<InputStream>() {{
            add(new FileInputStream(new File("C:\\img_0_0_52.png")));
            add(new FileInputStream(new File("C:\\img_0_1_1.png")));
            add(new FileInputStream(new File("C:\\img_0_1_0.png")));
            add(new FileInputStream(new File("C:\\img_0_2_1.png")));
        }});
        BufferedImage image = pictureMerge.merge(bufferedImages);
        ImageIO.write(image, "png", new File("big.png"));
    }
}
