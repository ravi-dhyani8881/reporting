package com.mobicart.util;

/**
 * @(#) ImageUtils.java;
 * <p/>
 * Created on Apr 29, 2009
 * AUTHOR    ** Danil Glinenko
 * <p/>
 */

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.ImageIcon;

public final class ImageUtils {
    
	public static final float BEST_QUALITY = 1.0f;
    public static final float HIGH_QUALITY = 0.75f;
    public static final float MEDIUM_QUALITY = 0.5f;
    public static final float LOW_QUALITY = 0.25f;
    public static final float LEAST_QUALITY = 0.0f;

    private ImageUtils() {
    }

    /**
     * Loads image from file system to BufferedImage object.
     * @param imageFile Image file to be loaded.
     * @return BufferedImage object.
     * @throws IOException if file is not found or the image can't be loaded.
     */
    public static BufferedImage loadImage(final File imageFile) throws IOException {
        return ImageIO.read(imageFile);
    }

    /**
     * Loads image from file system to BufferedImage object.
     * @param imageFilePath file path to the image to be loaded.
     * @return BufferedImage object.
     * @throws IOException if file is not found or the image can't be loaded.
     */
    public static BufferedImage loadImage(final String imageFilePath) throws IOException {
        return loadImage(new File(imageFilePath));
    }

    /**
     * Saves given BufferedImage to a file. Supports "png" and "jpg" formats. The format is identified based on
     * the given file name for the picture. The default is "jpg".
     * @param image Image to be saved to file.
     * @param imageFile file the images will be saved in to.
     * @throws IOException if the image can't be saved or file is not found.
     */
    public static void saveImage(final BufferedImage image, final File imageFile) throws IOException {
        if (image == null || imageFile== null){throw new IllegalArgumentException("\"image\" or \"imageFile\" params cannot be null.");}
        final String format = (imageFile.getName().endsWith("png"))? "png": "jpg";
        ImageIO.write(image, format, imageFile);
    }

    /**
     * Saves given BufferedImage to a file. Supports "png" and "jpg" formats. The format is identified based on
     * the given file name for the picture. The default is "jpg".
     * @param image Image to be saved to file.
     * @param imageFilePath file path the images will be saved in to.
     * @throws IOException if the image can't be saved or file is not found.
     */
    public static void saveImage(final BufferedImage image, final String imageFilePath) throws IOException {
        saveImage(image, new File(imageFilePath));
    }

    /**
     * Save JPEG image with the compression specified.
     * @param image Image to be saved to file.
     * @param imageFile file to save image.
     * @param quality must be in 0..1 range. 0.0 - highest compression is important, 1.0 - high image quality is important.
     * @throws IOException if the image can't be saved or file is not found.
     */
    @SuppressWarnings("rawtypes")
	public static void saveImageJPGCompressed(final BufferedImage image, final File imageFile, final float quality) throws IOException {
        if (image == null || imageFile == null){throw new IllegalArgumentException("\"image\" or \"imageFile\" params cannot be null.");}
        if (quality < 0 || quality >1){throw new IllegalArgumentException("Quality parameter must be in from 0 to 1 range.");}
        Iterator writers = ImageIO.getImageWritersBySuffix("jpeg");
        if (!writers.hasNext()) {
            throw new IllegalStateException("No writers found.");
        }
        // get the writer
        ImageWriter writer = (ImageWriter) writers.next();
        ImageWriteParam wParams = writer.getDefaultWriteParam();
        wParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        wParams.setCompressionQuality(quality);

        //writing image
        FileImageOutputStream output = new FileImageOutputStream(imageFile);
        writer.setOutput(output);
        writer.write(null, new IIOImage(image, null, null), wParams);
        writer.dispose();
    }

    /**
     * Rotates given image to given angle.
     * @param image image to be rotated.
     * @param angle angle to rotate image to
     * @return rotated image.
     */
    public static BufferedImage rotate(final BufferedImage image, final int angle) {
        if (image == null){throw new IllegalArgumentException("\"image\" param cannot be null.");}
        final int imageWidth = image.getWidth();
        final int imageHeight = image.getHeight();
        final Map<String, Integer> boundingBoxDimensions = calculateRotatedDimensions(imageWidth, imageHeight, angle);

        final int newWidth = boundingBoxDimensions.get("width");
        final int newHeight = boundingBoxDimensions.get("height");

        final BufferedImage newImage = new BufferedImage(newWidth, newHeight, image.getType());
        final Graphics2D newImageGraphic = newImage.createGraphics();

        final AffineTransform transform = new AffineTransform();
        transform.setToTranslation((newWidth-imageWidth)/2, (newHeight-imageHeight)/2);
        transform.rotate(Math.toRadians(angle), imageWidth/2, imageHeight/2);
        newImageGraphic.drawImage(image, transform, null);
        newImageGraphic.dispose();

        return newImage;
    }

    @SuppressWarnings("serial")
	private static Map<String, Integer> calculateRotatedDimensions(final int imageWidth, final int imageHeight, final int angle) {
        final Map<String, Integer> dimensions = new HashMap<String, Integer>();
        // coordinates of our given image
        final int[][] points = {
                {0, 0},
                {imageWidth, 0},
                {0, imageHeight},
                {imageWidth, imageHeight}
        };

        final Map<String, Integer> boundBox = new HashMap<String, Integer>(){{
            put("left", 0);
            put("right", 0);
            put("top", 0);
            put("bottom", 0);
        }};

        final double theta = Math.toRadians(angle);

        for (final int[] point : points) {
            final int x = point[0];
            final int y = point[1];
            final int newX = (int) (x * Math.cos(theta) + y * Math.sin(theta));
            final int newY = (int) (x * Math.sin(theta) + y * Math.cos(theta));

            //assign the bounds
            boundBox.put("left", Math.min(boundBox.get("left"), newX));
            boundBox.put("right", Math.max(boundBox.get("right"), newX));
            boundBox.put("top", Math.min(boundBox.get("top"), newY));
            boundBox.put("bottom", Math.max(boundBox.get("bottom"), newY));
        }

        // now get the dimensions of the new box.
        dimensions.put("width", Math.abs(boundBox.get("right") - boundBox.get("left")));
        dimensions.put("height", Math.abs(boundBox.get("bottom") - boundBox.get("top")));
        return dimensions;
    }

    /**
     * Resizes given image to the given dimensions.
     * @param image original image to resize.
     * @param newWidth new width of the image.
     * @param newHeight new height of the image.
     * @param maintainAspectRatio if true the aspect ratio will be maintained.
     * @return resized image.
     */
    public static BufferedImage resize(BufferedImage image, int newWidth, int newHeight, final boolean maintainAspectRatio) {
       
    //return MagicalPower.resize(image, newWidth, newHeight);
    	
//    	
    	if (image == null){throw new IllegalArgumentException("\"image\" param cannot be null.");}
        final int imageWidth = image.getWidth();
        final int imageHeight = image.getHeight();
        // if we want to maintain aspect ratio.
        if (maintainAspectRatio) {
            final double newRatio = (double) newWidth / (double) newHeight;
            final double imageRatio = (double) imageWidth / (double) imageHeight;
            if (newRatio < imageRatio) {
                newHeight = (int)(newWidth / imageRatio);
            } else {
                newWidth = (int)(newHeight * imageRatio);
            }
        }

        final BufferedImage dimg;
        if(image.getType() == 0){
        dimg=	new BufferedImage(newWidth, newHeight,5);
        }else{
         dimg = new BufferedImage(newWidth, newHeight, image.getType());
        }
        final Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(image, 0, 0, newWidth, newHeight, 0, 0, imageWidth, imageHeight, null);
        g.dispose();
        return dimg;
    }

    /**
     * Watermarks the given image with given text.
     * @param image Image to be watermarked
     * @param text text to be used as a watermark.
     * @return new watermarked image.
     */
    public static BufferedImage waterMarkImage(final BufferedImage image, final String text) {
        if (image == null){throw new IllegalArgumentException("\"image\" param cannot be null.");}
        // create a new image
        final BufferedImage waterMarked = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        final Graphics2D imageG = waterMarked.createGraphics();
        // draw original image.
        imageG.drawImage(image, null, 0, 0);
        imageG.dispose();

        final Graphics2D g  = waterMarked.createGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); // 50% transparency
        g.setColor(Color.white);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        final FontMetrics fontMetrics = g.getFontMetrics();
        final Rectangle2D rect = fontMetrics.getStringBounds(text, g);
        final int centerX = (image.getWidth() - (int) rect.getWidth()) /2;
        final int centerY = (image.getHeight() - (int) rect.getHeight()) /2;
        g.drawString(text, centerX, centerY);
        g.dispose();
        return waterMarked;
    }
    
    
    
    
 // This method returns a buffered image with the contents of an image
    	
    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage)image;
        }

        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();

        // Determine if the image has transparent pixels; for this method's
        // implementation, see Determining If an Image Has Transparent Pixels
        boolean hasAlpha = hasAlpha(image);

        // Create a buffered image with a format that's compatible with the screen
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            // Determine the type of transparency of the new buffered image
            int transparency = Transparency.OPAQUE;
            if (hasAlpha) {
                transparency = Transparency.BITMASK;
            }

            // Create the buffered image
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(
                image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }

        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            if (hasAlpha) {
                type = BufferedImage.TYPE_INT_ARGB;
            }
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }

        // Copy image to buffered image
        Graphics g = bimage.createGraphics();

        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bimage;
    }

    
    
 // This method returns true if the specified image has transparent pixels
    public static boolean hasAlpha(Image image) {
        // If buffered image, the color model is readily available
        if (image instanceof BufferedImage) {
            BufferedImage bimage = (BufferedImage)image;
            return bimage.getColorModel().hasAlpha();
        }

        // Use a pixel grabber to retrieve the image's color model;
        // grabbing a single pixel is usually sufficient
         PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
        }

        // Get the image's color model
        ColorModel cm = pg.getColorModel();
        return cm.hasAlpha();
    }
}
