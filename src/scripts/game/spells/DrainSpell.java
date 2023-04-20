package scripts.game.spells;

import scripts.game.entities.Character;

public class DrainSpell extends Spell {

    public DrainSpell() {
        super("Drain", 100, 25, "offensif");
    }

    public void action(Character attaquant, Character cible) {
        super.action(attaquant, cible);

        int dg = Character.dg(attaquant, cible, this);
        if(dg > attaquant.getPvMax()-attaquant.getPv()) {
            dg = attaquant.getPvMax()-attaquant.getPv();
        }
        attaquant.takeDamage(-dg, attaquant);
    }
}
