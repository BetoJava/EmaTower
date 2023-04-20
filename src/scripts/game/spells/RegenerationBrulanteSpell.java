package scripts.game.spells;

import scripts.game.entities.Character;
import scripts.game.statut.Enflamme;

public class RegenerationBrulanteSpell extends Spell {
    
    public RegenerationBrulanteSpell(){
        super("Régénération Brulante",-20, 0, "soin");
    }


    public void action(Character attaquant, Character cible) {
        super.action(attaquant, cible);
        attaquant.addStatut(new Enflamme(attaquant));

        // Ajoute 30 endurance
        if(attaquant.getEndurance() + 20 > attaquant.getEnduranceMax()) {
            attaquant.setEndurance(attaquant.getEnduranceMax());
        } else {
            attaquant.addEndurance(20);
        }
    }
}
