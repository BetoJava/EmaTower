package scripts.display.gui.components;

import scripts.display.utils.Shape;
import scripts.display.utils.DisplayFunctions;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SButton extends Component {

    protected BufferedImage image;
    protected BufferedImage pressedImage;
    protected Color color;
    protected Color fontColor;
    protected String text;
    protected Font font;
    protected float size;
    protected double value;

    protected boolean isPressed = false;
    protected boolean isDisable = false;

    protected scripts.display.utils.Shape shape;

    public SButton(BufferedImage image, BufferedImage pressedImage, int x, int y, int height, int width)
    {
        this.image = image;
        this.pressedImage = pressedImage;
        init(x,y,height,width);
    }

    public SButton(Color color, String text, Font font, Color fontColor, float size, int x, int y, int height, int width)
    {
        this.color = color;
        this.fontColor = fontColor;
        this.text = text;
        this.font = font;
        this.size = size;
        init(x,y,height,width);
    }

    public SButton(BufferedImage image, BufferedImage pressedImage, String text, Font font, Color fontColor, float size, int x, int y, int height, int width)
    {
        this.image = image;
        this.pressedImage = pressedImage;
        this.fontColor = fontColor;
        this.text = text;
        this.font = font;
        this.size = size;
        init(x,y,height,width);
    }

    // SButton with UI Sprite //
    public SButton(BufferedImage image, String text, Font font, Color fontColor, Color pressedColor, float coeff, float size, int x, int y, int height, int width, int dx, int multi)
    {
        this.image = DisplayFunctions.fillWithUISprite(image,width,height,dx,multi);
        this.pressedImage = DisplayFunctions.fillWithUISprite(DisplayFunctions.changeColor(image, pressedColor, coeff),width,height,dx,multi);
        this.fontColor = fontColor;
        this.text = text;
        this.font = font;
        this.size = size;
        init(x,y,height,width);
    }

    // SButton with colored UI Sprite //
    public SButton(BufferedImage image, String text, Font font, Color colorFilter, Color fontColor, Color pressedColor, float coeff, float size, int x, int y, int height, int width, int dx, int multi)
    {
        this.image = DisplayFunctions.fillWithUISprite(image,width,height,dx,multi);
        this.image = DisplayFunctions.changeColor(this.image, colorFilter, colorFilter.getAlpha()/255f);
        this.pressedImage = DisplayFunctions.fillWithUISprite(DisplayFunctions.changeColor(image, pressedColor, coeff),width,height,dx,multi);
        this.fontColor = fontColor;
        this.text = text;
        this.font = font;
        this.size = size;
        init(x,y,height,width);
    }

    private void init(int x, int y, int height, int width) {
        shape = new scripts.display.utils.Shape(x,y,width,height);
        if(image != null)
        {
            resizeImages();
        }
    }

    private void resizeImages() {
        image = DisplayFunctions.fillWithImage(image,shape.getWidth(),shape.getHeight());
        pressedImage = DisplayFunctions.fillWithImage(pressedImage,shape.getWidth(),shape.getHeight());
    }

    /**
     * Fonction qui est appelée lorsque le bouton est pressé.
     */
    public void action() {}

    /**
     * Fonction d'affichage de l'élément.
     * @param g Graphics2D
     */
    public void render(Graphics2D g)
    {
        if(isPressed)
        {
            if(pressedImage != null)
            {
                g.drawImage(pressedImage,shape.getX(),shape.getY(),null);
            }
            else
            {
                g.setColor(color.darker());
                g.fillRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
            }
        }
        else
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
        }

        if(text != null)
        {
            g.setColor(fontColor);
            g.setFont(font.deriveFont(size));

            DisplayFunctions.drawCenteredString(g,text,shape);
        }
    }

    public void disableButton(boolean b) {
        isDisable = b;
    }

    public boolean isDisable() {
        return isDisable;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getPressedImage() {
        return pressedImage;
    }

    public void setPressedImage(BufferedImage pressedImage) {
        this.pressedImage = pressedImage;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
