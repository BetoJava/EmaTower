package scripts.display.gui.groups;

import scripts.display.gui.components.SButton;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ButtonGroup {

    protected ArrayList<SButton> group = new ArrayList<>();

    /**
     * Fonction d'affichage des éléments du groupe.
     * @param g Graphics2D
     */
    public void render(Graphics2D g)
    {
        for(SButton b : group)
        {
            b.render(g);
        }
    }

    /**
     * Fonction qui actionne les boutons qui sont au dessous du click de la souris.
     * @param e MouseEvent
     */
    public void pressed(MouseEvent e)
    {
        for(SButton b : group)
        {
            if(b.getShape().isIn(e.getPoint()))
            {
                b.setPressed(true);
            }
        }
    }

    /**
     * Fonction qui désactionne les boutons qui sont relâchés.
     * @param e MouseEvent
     */
    public void released(MouseEvent e)
    {
        for(SButton b : group)
        {
            if(b.isPressed())
            {
                if(b.getShape().isIn(e.getPoint()))
                {
                    if(!b.isDisable()) {
                        b.action();
                    }
                }
                b.setPressed(false);
            }
        }
    }

    public void add(SButton b)
    {
        group.add(b);
    }

    public void remove(SButton b)
    {
        group.remove(b);
    }

    public void remove(int index)
    {
        group.remove(index);
    }

    public void clear() {
        group = null;
    }

    public void disableButtons(boolean bool) {
        for(SButton b : group) {
            b.disableButton(bool);
        }
    }
}
