package scripts.display.gui.components;

import scripts.display.utils.DisplayFunctions;

import java.awt.*;

public class FurtiveText extends SLabel{

    protected int delay = 0;

    public FurtiveText(int delay, Color color, String text, Font font, Color fontColor, float size, int x, int y, int height, int width, boolean isCentered) {
        super(color, text, font, fontColor, size, x, y, height, width, isCentered);
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    @Override
    public void render(Graphics2D g)
    {
        delay -= 1;
        // Background //
        g.setColor(color);
        g.fillRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());

        // Text //
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
