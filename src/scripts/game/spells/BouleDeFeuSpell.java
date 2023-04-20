package scripts.game.spells;

import scripts.game.entities.Character;
import scripts.game.statut.Enflamme;

public class BouleDeFeuSpell extends Spell{
    
    public BouleDeFeuSpell(){
        super("Boule de Feu", 50, 25, "offensif");
    }


    public void action(Character attaquant, Character cible) {
        super.action(attaquant, cible);
        cible.addStatut(new Enflamme(attaquant));
    }
}
