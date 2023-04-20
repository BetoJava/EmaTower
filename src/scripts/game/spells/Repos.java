package scripts.game.spells;

import scripts.game.entities.Character;
import scripts.game.statut.Endormie;

public class Repos extends Spell {
    public Repos() {
        super("Repos", -15, 0, "boost");
    }

    public void action(Character attaquant, Character cible) {
        super.action(attaquant, cible);
        // Endort le lanceur
        attaquant.addStatut(new Endormie(attaquant));
        // Ajoute 50 endurance
        if(attaquant.getEndurance() + 50 > attaquant.getEnduranceMax()) {
            attaquant.setEndurance(attaquant.getEnduranceMax());
        } else {
            attaquant.addEndurance(50);
        }
    }
}
