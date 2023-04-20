package scripts.display.gui.components;

import scripts.display.utils.Shape;
import scripts.display.utils.DisplayFunctions;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SImage extends Component {

    protected BufferedImage image;
    protected Shape shape;



    public SImage(BufferedImage image, int x, int y, int height, int width)
    {
        this.image = image;
        init(x,y,height,width);
    }

    public SImage(BufferedImage image, int x, int y, float multi)
    {
        this.image = image;
        init(x,y,(int)(image.getHeight()*multi),(int)(image.getWidth()*multi));
    }

    private void init(int x, int y, int height, int width) {
        shape = new Shape(x,y,width,height);
        resizeImage();

    }

    private void resizeImage() {
        image = DisplayFunctions.fillWithImage(image,shape.getWidth(),shape.getHeight());
    }

    /**
     * Fonction d'affichage de l'élément.
     * @param g Graphics2D
     */
    public void render(Graphics2D g)
    {
        g.drawImage(image, shape.getX(), shape.getY(), null);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
