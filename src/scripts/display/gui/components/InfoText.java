package scripts.display.gui.components;

import scripts.display.resources.Resources;

import java.awt.*;

public class InfoText extends FurtiveText {

    public InfoText(int delay, String text) {
        super(delay, new Color(0, 0, 0, 20), text, Resources.FONTS.get("Miriam"), Color.black, 20f, 0, 400, 100, 650, true);

    }
}
