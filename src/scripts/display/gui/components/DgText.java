package scripts.display.gui.components;

import scripts.display.resources.Resources;

import java.awt.*;

public class DgText extends FurtiveText {

    private float dy;

    public DgText(int delay, int dg, boolean isPlayer) {
        super(delay, new Color(0, 0, 0, 0), "", Resources.FONTS.get("Fira"), Color.red, 30f, 725, 145, 0, 0, true);
        dy = 60/((float)delay);
        if(dg == 0) {
            this.delay = 0;
        }

        if(dg < 0) {
            fontColor = new Color(128, 194, 0, 255);
            text = "+ " + String.valueOf(-dg);
        } else {
            text = "- " + String.valueOf(dg);
        }

        if(isPlayer) {
            shape.setX(300);
            shape.setY(250);
        }

    }

    @Override
    public void render(Graphics2D g)
    {
        // Text is moving up //
        shape.setY((int)(shape.getY()-dy));

        super.render(g);
    }
}
