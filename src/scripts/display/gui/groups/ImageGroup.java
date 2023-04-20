package scripts.display.gui.groups;

import scripts.display.gui.components.SImage;

import java.awt.*;
import java.util.ArrayList;

public class ImageGroup {

    protected ArrayList<SImage> group = new ArrayList<>();

    /**
     * Fonction d'affichage des éléments du groupe.
     * @param g Graphics2D
     */
    public void render(Graphics2D g)
    {
        for(SImage i : group)
        {
            i.render(g);
        }
    }


    public void add(SImage i)
    {
        group.add(i);
    }

    public void remove(SImage i)
    {
        group.remove(i);
    }

    public void remove(int index)
    {
        group.remove(index);
    }

    public void clear() {
        group = null;
    }
}
