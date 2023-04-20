package scripts.display.gui.components;


import scripts.display.resources.Resources;
import scripts.display.utils.Shape;
import scripts.game.entities.Character;

import java.awt.*;

public class StatInterface {

    private int width = 350;
    private int height = 100;

    private Character character;

    private SButton panel;
    private SLabel nameLabel;
    private SLabel pvLabel;
    private SLabel enduranceLabel;

    private ProgressBar pvBar;
    private ProgressBar enduranceBar;

    private Shape shape;

    public StatInterface(Character character, int x, int y) {
        this.character = character;
        shape = new Shape(x,y,width,height);

        panel = new SButton(Resources.TEXTURES.get("UISpritePopup"),(String)null,
                (Font)null, new Color(140,140,140,100), new Color(255,255,255,100), new Color(255,255,255,100), 0.35f, 0f,
                x,y,height,width,4,4);

        nameLabel = new SLabel(new Color(0,0,0,0), character.getPseudo() + "    niv." + character.getNiveau(), Resources.FONTS.get("Miriam"),Color.black, 15f, x+20, y+34, 0,0, false);
        pvLabel = new SLabel(new Color(0,0,0,0), "PV : ", Resources.FONTS.get("Miriam"), Color.black, 15f, x+20, y+54, 0,0, false);
        enduranceLabel = new SLabel(new Color(0,0,0,0), "Endurance : ", Resources.FONTS.get("Miriam"),Color.black, 15f, x+20, y+74, 0,0, false);

        pvBar = new ProgressBar(0,character.getPvMax(),x+50,y+41,width-80, 14,2,Color.red);
        enduranceBar = new ProgressBar(0,character.getEnduranceMax(),x+105,y+61,width-135, 14,2,Color.orange);

    }

    /**
     * Fonction d'affichage de l'élément.
     * @param g Graphics2D
     */
    public void render(Graphics2D g) {
        panel.render(g);
        pvLabel.render(g);
        enduranceLabel.render(g);

        pvBar.update(character.getPv());
        enduranceBar.update(character.getEndurance());
        nameLabel.setText(character.getPseudo() + "    niv." + character.getNiveau());

        nameLabel.render(g);
        pvBar.render(g);
        enduranceBar.render(g);
    }


}
