package scripts.display.utils;

import java.awt.*;

/**
 * Classe pour créer des objet shape pour mieux gérer l'espace.
 */
public class Shape {

    private int x;
    private int y;
    private int width;
    private int height;

    public Shape(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Vérifie si le point p est dans l'objet.
     * @param p
     * @return Boolean
     */
    public boolean isIn(Point p)
    {
        if(p.getX() >= x && p.getX() <= x+width && p.getY() >= y && p.getY() <= y+height)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
