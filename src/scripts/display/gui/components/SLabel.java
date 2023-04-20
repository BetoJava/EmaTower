package scripts.display.gui.components;

import scripts.display.utils.Shape;
import scripts.display.utils.DisplayFunctions;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SLabel extends Component {

    protected BufferedImage image;
    protected Color color;
    protected Color fontColor;
    protected String text;
    protected Font font;
    protected float size;

    protected boolean isCentered = true;

    protected Shape shape;

    public SLabel(Color color, String text, Font font, Color fontColor, float size, int x, int y, int height, int width, boolean isCentered)
    {
        this.isCentered = isCentered;
        this.color = color;
        this.fontColor = fontColor;
        this.text = text;
        this.font = font;
        this.size = size;
        init(x,y,height,width);
    }

    public SLabel(Color color, int x, int y, int height, int width)
    {
        this.color = color;
        init(x,y,height,width);
    }

    public SLabel(BufferedImage image, String text, Font font, Color fontColor, float size, int x, int y, int height, int width)
    {
        this.image = image;
        this.fontColor = fontColor;
        this.text = text;
        this.font = font;
        this.size = size;
        init(x,y,height,width);
    }

    private void init(int x, int y, int height, int width) {
        shape = new Shape(x,y,width,height);
        if(image != null)
        {
            resizeImage();
        }
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

        if(image != null)
        {
            g.drawImage(image, shape.getX(), shape.getY(), null);

        }
        else
        {
            g.setColor(color);
            g.fillRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
        }


        if(text != null)
        {
            g.setColor(fontColor);
            g.setFont(font.deriveFont(size));

            if(isCentered) {
                DisplayFunctions.drawCenteredString(g,text,shape);
            }
            else {
                g.drawString(text, shape.getX(), shape.getY());
            }
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Font getFont() {
        return font;
    }

    @Override
    public void setFont(Font font) {
        this.font = font;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
