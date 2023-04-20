package scripts.display.gui.components;

import scripts.display.utils.Shape;

import java.awt.*;

public class ProgressBar {

    private int val;
    private int max;
    private int min = 0;

    private SLabel borders;
    private SLabel fill;
    private Color fillColor;

    private Shape shape;
    private int width;

    public ProgressBar(int min, int max, int x, int y, int width, int height, int borderSize, Color fillColor) {
        this.max = max;
        this.min = min;
        val = max;
        this.fillColor = fillColor;
        this.width = width;

        shape = new Shape(x,y,width,height);
        borders = new SLabel(Color.black, x, y, height+2*borderSize, width+2*borderSize);
        fill = new SLabel(fillColor, x+borderSize, y+borderSize, height, (int)(width*progression()));
    }

    private float progression() {
        return Math.min(val/((float)(max-min)),1f);
    }

    public void update(int val) {
        this.val = val;
    }

    /**
     * Fonction d'affichage de l'élément.
     * @param g Graphics2D
     */
    public void render(Graphics2D g) {
        borders.render(g);

        Shape s = fill.getShape();
        s.setWidth((int)(progression()*width));
        fill.setShape(s);

        fill.render(g);
    }

}
