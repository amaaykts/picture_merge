package ru.github.merge;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class PictureMerge {
    private int pictureHeight = 220;
    private int pictureWidth = 400;
    private int columnCount = 3;
    private PictureFormat format = PictureFormat.PNG;

    public PictureMerge setPictureHeight(int pictureHeight) {
        this.pictureHeight = pictureHeight;
        return this;
    }

    public PictureMerge setPictureWidth(int pictureWidth) {
        this.pictureWidth = pictureWidth;
        return this;
    }

    public PictureMerge setColumnCount(int columnCount) {
        this.columnCount = columnCount;
        return this;
    }

    public PictureMerge setFormat(PictureFormat format) {
        this.format = format;
        return this;
    }

    public List<BufferedImage> convertInputStreamToBufferedImage(List<InputStream> inputStreams) throws IOException {
        List<BufferedImage> images = new ArrayList<>();
        for (InputStream is : inputStreams) {
            images.add(ImageIO.read(is));
        }
        return images;
    }

    public BufferedImage merge(Collection<BufferedImage> imageCollection) throws IOException {
        List<BufferedImage> images = resizePicture(imageCollection, pictureHeight, pictureWidth);  //resize picture
        int rows = rowsCount(images);

        BufferedImage finalImage = new BufferedImage(pictureWidth * columnCount, pictureHeight * rows, BufferedImage.TYPE_INT_ARGB);
        int num = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (num != images.size()) {
                    finalImage.createGraphics().drawImage(images.get(num++), pictureWidth * j, pictureHeight * i, null);
                }
            }
        }
//        ImageIO.write(finalImage, formatName, outputStream);
        return finalImage;
    }

    public int pictureByteLength(BufferedImage image) {
        int bytes = 0;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, format.getName(), baos);
            bytes = baos.toByteArray().length;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    private int rowsCount(List images) {
        int rowsTemp = images.size() / columnCount;
        return images.size() % columnCount != 0 ? (rowsTemp + 1) : rowsTemp;
    }

    private List<BufferedImage> resizePicture(Collection<BufferedImage> imageCollection, int pictureHeight, int pictureWidth) {
        List<BufferedImage> images = new ArrayList<>();
        for (BufferedImage image : imageCollection) {
            Image instance = image.getScaledInstance(pictureWidth, pictureHeight, Image.SCALE_SMOOTH);
            BufferedImage bufferedImage = new BufferedImage(pictureWidth, pictureHeight, BufferedImage.TYPE_INT_ARGB);
            bufferedImage.createGraphics().drawImage(instance, 0, 0, null);
            images.add(bufferedImage);
        }
        imageCollection.clear();
        return images;
    }
}
