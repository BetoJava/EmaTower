package scripts.display.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 * Classe regroupant des méthodes statiques utiles pour l'affichage.
 */
public class DisplayFunctions {

    /**
     * Convertie une Image en BufferedImage.
     * @param img
     * @return BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    /**
     * Redimensionne une image de façon "smooth".
     * @param originalImage
     * @param w
     * @param h
     * @return
     */
    public static BufferedImage smoothResize(BufferedImage originalImage, int w, int h) {
        return toBufferedImage(originalImage.getScaledInstance(w,h,BufferedImage.SCALE_SMOOTH));
    }

    /**
     * Redimensionne l'image selon un coefficient Double.
     * @param originalImage
     * @param multi Coefficient multiplicateur
     * @return
     */
    public static BufferedImage resizeImage(BufferedImage originalImage, double multi) {
        int targetWidth = (int)((double)originalImage.getWidth() * multi);
        int targetHeight = (int)((double)originalImage.getHeight() * multi);

        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    /**
     * Redimensionne l'image selon un coefficient Float.
     * @param originalImage
     * @param multi Coefficient multiplicateur
     * @return
     */
    public static BufferedImage resizeImage(BufferedImage originalImage, float multi) {
        int targetWidth = (int)((float)originalImage.getWidth() * multi);
        int targetHeight = (int)((float)originalImage.getHeight() * multi);

        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    /**
     * Crée une image à partir d'un sprite étiré.
     * @param originalImage
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage fillWithImage(BufferedImage originalImage, int width, int height) {
        int targetWidth = width;
        int targetHeight = height;

        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    /**
     * Crée une image à partir d'un sprite étiré.
     * @param image
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage fillWithUISprite(BufferedImage image, int width, int height, int dx, int multi) {
        int w = width;
        int h = height;
        float[] pixel = new float[4];

        // Image to return
        BufferedImage newImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        WritableRaster newRaster = newImage.getRaster();
        WritableRaster raster = image.getRaster();

        // Middle //
            // Getting the stretched middle image //
        BufferedImage middle = new BufferedImage(image.getWidth()-2*dx,image.getHeight()-2*dx,BufferedImage.TYPE_INT_ARGB);
        WritableRaster middleRaster = middle.getRaster();
        for(int i = dx; i < image.getWidth()-dx; i++) {
            for (int j = dx; j < image.getHeight()-dx; j++) {
                middleRaster.setPixel(i-dx,j-dx,raster.getPixel(i,j,pixel));
            }
        }
        BufferedImage middleImage = fillWithImage(middle,w-2*dx*multi, h-2*dx*multi);
        WritableRaster newMiddleRaster = middleImage.getRaster();

            // Setting middle newRaster //
        for(int i = dx*multi; i < w-dx*multi; i++) {
            for (int j = dx*multi; j < h - dx*multi; j++) {
                newRaster.setPixel(i,j,newMiddleRaster.getPixel(i-dx*multi,j-dx*multi,pixel));
            }
        }

        // Corners //
        for(int i = 0; i < dx*multi; i++) {
            for(int j = 0; j < dx*multi; j++) {
                newRaster.setPixel(i,j,raster.getPixel(i/multi,j/multi,pixel));
                newRaster.setPixel(w-i-1,j,raster.getPixel(image.getWidth()-i/multi-1,j/multi,pixel));
                newRaster.setPixel(w-i-1,h-j-1,raster.getPixel(image.getWidth()-i/multi-1,image.getHeight()-j/multi-1,pixel));
                newRaster.setPixel(i,h-j-1,raster.getPixel(i/multi,image.getHeight()-j/multi-1,pixel));
            }
        }

        // Edges //
        for(int i = dx*multi; i < w-dx*multi; i++) {
            for(int j = 0; j < dx*multi; j++) {
                newRaster.setPixel(i,j,raster.getPixel(dx,j/multi,pixel));
                newRaster.setPixel(i,h-j-1,raster.getPixel(dx,image.getHeight()-j/multi-1,pixel));
            }
        }
        for(int j = dx*multi; j < h-dx*multi; j++) {
            for(int i = 0; i < dx*multi; i++) {
                newRaster.setPixel(i,j,raster.getPixel(i/multi,dx,pixel));
                newRaster.setPixel(w-i-1,j,raster.getPixel(image.getWidth()-i/multi-1,dx,pixel));
            }
        }

        return newImage;
    }

    /**
     * Affiche du texte de façon centré.
     * @param g
     * @param text
     * @param rect
     */
    public static final void drawCenteredString(Graphics g, String text, Shape rect) {
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int x = rect.getX() + (rect.getWidth() - metrics.stringWidth(text)) / 2;
        int y = rect.getY() + ((rect.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(text, x, y);
    }

    /**
     * Ajoute un filtre de couleur sur l'image.
     * @param image
     * @param color
     * @param c
     * @return
     */
    public static BufferedImage changeColor(BufferedImage image, Color color, float c) {
        WritableRaster data = image.getRaster();

        BufferedImage copy = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        copy.setData(data);
        WritableRaster raster = copy.getRaster();

        float[] pixel = new float[4];
        for(int i = 0; i < image.getWidth(); i++) {
            for(int j = 0; j < image.getHeight(); j++) {
                pixel = raster.getPixel(i,j,pixel);
                pixel[0] = pixel[0]*(1-c) + color.getRed()*c;
                pixel[1] = pixel[1]*(1-c) + color.getGreen()*c;
                pixel[2] = pixel[2]*(1-c) + color.getBlue()*c;
                raster.setPixel(i,j,pixel);
            }
        }

        return copy;
    }

}
