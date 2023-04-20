package scripts.display.gui.groups;

import scripts.display.gui.components.DgText;
import scripts.display.gui.components.FurtiveText;
import scripts.display.gui.components.InfoText;

import java.awt.*;
import java.util.ArrayList;

public class FurtiveTextGroup {

    protected ArrayList<InfoText> groupInfoText = new ArrayList<>();
    protected ArrayList<DgText> groupDgText = new ArrayList<>();


    /**
     * Fonction d'affichage des éléments du groupe.
     * @param g Graphics2D
     */
    public void render(Graphics2D g)
    {
        // On affiche le premier texte info furtif //
        if(!groupInfoText.isEmpty()) {
            if(groupInfoText.get(0).getDelay() <= 0) {
                removeInfoText(0);
            } else {
                groupInfoText.get(0).render(g);
            }
        }

        // On affiche les texte de dg furtif //
        ArrayList<DgText> toRemove = new ArrayList<>();
        for(DgText t : groupDgText)
        {
            if(t.getDelay() <= 0) {
                toRemove.add(t);
            } else {
                t.render(g);
            }
        }
        for(DgText t : toRemove) {
            removeDgText(t);
        }
    }

    public void addInfoText(int delay, String text)
    {
        groupInfoText.add(new InfoText(delay, text));
    }

    public void addDgText(int delay, int dg, boolean isPlayer)
    {
        groupDgText.add(new DgText(delay, dg, isPlayer));
    }

    public void removeInfoText(FurtiveText l)
    {
        groupInfoText.remove(l);
    }

    public void removeInfoText(int index)
    {
        groupInfoText.remove(index);
    }

    public void removeDgText(FurtiveText l)
    {
        groupDgText.remove(l);
    }

    public void removeDgText(int index)
    {
        groupDgText.remove(index);
    }

    public void clearInfoText() {
        for(InfoText t : groupInfoText) {
            t.setDelay(0);
        }
    }

    public void clearDgText() {
        for(DgText t : groupDgText) {
            t.setDelay(0);
        }
    }

    public boolean isEmpty() {
        return (groupInfoText.isEmpty() && groupDgText.isEmpty());
    }


}
