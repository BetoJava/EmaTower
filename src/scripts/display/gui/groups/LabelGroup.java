package scripts.display.gui.groups;

import scripts.display.gui.components.SLabel;

import java.awt.*;
import java.util.ArrayList;

public class LabelGroup {

    protected ArrayList<SLabel> group = new ArrayList<>();

    /**
     * Fonction d'affichage des éléments du groupe.
     * @param g Graphics2D
     */
    public void render(Graphics2D g)
    {
        for(SLabel l : group)
        {
            l.render(g);
        }
    }

    public void add(SLabel l)
    {
        group.add(l);
    }

    public void remove(SLabel l)
    {
        group.remove(l);
    }

    public void remove(int index)
    {
        group.remove(index);
    }

    public void clear() {
        group = null;
    }
}
